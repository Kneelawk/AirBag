package com.kneelawk.airbag.mixin;

import java.util.Optional;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.impl.TerraformBoatTrackedData;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import org.quiltmc.qsl.entity.networking.api.tracked_data.QuiltTrackedDataHandlerRegistry;

import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

/**
 * Mixin into Terraform-Wood-API to make it register tracked data in a non-load-order-dependent way.
 */
@Mixin(value = TerraformBoatTrackedData.class, remap = false)
public class TerraformBoatTrackedDataMixin {
    @Shadow
    @Final
    public static TrackedDataHandler<Optional<TerraformBoatType>> HANDLER;

    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void onRegister(CallbackInfo ci) {
        QuiltTrackedDataHandlerRegistry.register(new Identifier("terraform-wood-api-v1", "boat_type"), HANDLER);
        ci.cancel();
    }

    @Inject(method = "write", at = @At("HEAD"), cancellable = true)
    private static void onWrite(PacketByteBuf buf, TerraformBoatType boat, CallbackInfo ci) {
        RegistryKey<TerraformBoatType> key = TerraformBoatTypeRegistry.INSTANCE.getKey(boat).orElseThrow(() -> new IllegalStateException("Encountered unregistered terraform-boat-type: " + boat));
        buf.writeRegistryKey(key);
        ci.cancel();
    }

    @Inject(method = "read", at = @At("HEAD"), cancellable = true)
    private static void onRead(PacketByteBuf buf, CallbackInfoReturnable<TerraformBoatType> cir) {
        RegistryKey<TerraformBoatType> key = buf.readRegistryKey(TerraformBoatTypeRegistry.INSTANCE.getKey());
        cir.setReturnValue(TerraformBoatTypeRegistry.INSTANCE.get(key));
    }
}
