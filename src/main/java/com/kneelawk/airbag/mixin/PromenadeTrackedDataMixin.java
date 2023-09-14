package com.kneelawk.airbag.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fr.hugman.promenade.entity.CapybaraState;
import fr.hugman.promenade.entity.CapybaraVariant;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;

import org.quiltmc.qsl.entity.networking.api.tracked_data.QuiltTrackedDataHandlerRegistry;

import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.util.Identifier;

/**
 * Mixin into Promenade to make it register tracked data in a non-load-order-dependent way.
 */
@Mixin(value = PromenadeTrackedData.class, remap = false)
public class PromenadeTrackedDataMixin {
    @Shadow
    @Final
    public static TrackedDataHandler<CapybaraVariant> CAPYBARA_VARIANT;
    @Shadow
    @Final
    public static TrackedDataHandler<CapybaraState> CAPYBARA_STATE;

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private static void onInit(CallbackInfo ci) {
        QuiltTrackedDataHandlerRegistry.register(new Identifier("promenade", "capybara_variant"), CAPYBARA_VARIANT);
        QuiltTrackedDataHandlerRegistry.register(new Identifier("promenade", "capybara_state"), CAPYBARA_STATE);
        ci.cancel();
    }
}
