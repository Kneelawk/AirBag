package com.kneelawk.airbag.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;

// Hopefully this won't be needed, as it is quite cursed.
@Mixin(EntityTrackerUpdateS2CPacket.class)
public class EntityTrackerUpdateS2CPacketMixin {
//    @Unique
//    private static final ThreadLocal<Integer> entityId = new ThreadLocal<>();
//
//    @SuppressWarnings("unused")
//    @ModifyExpressionValue(method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V",
//        at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketByteBuf;readVarInt()I"))
//    private static int captureEntityId(int id) {
//        entityId.set(id);
//        return id;
//    }
//
//    @SuppressWarnings("unused")
//    @WrapOperation(method = "read", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketByteBuf;readUnsignedByte()S"))
//    private static short interceptGetId(PacketByteBuf buf, Operation<Short> operation) {
//        try {
//            return operation.call(buf);
//        } catch (Exception e) {
//            return EntityTrackerUpdateS2CPacket.END_MARKER;
//        }
//    }
//
//    @SuppressWarnings("unused")
//    @WrapOperation(method = "read", at = @At(value = "INVOKE",
//        target = "Lnet/minecraft/entity/data/DataTracker$SerializedEntry;read(Lnet/minecraft/network/PacketByteBuf;I)Lnet/minecraft/entity/data/DataTracker$SerializedEntry;"))
//    private static DataTracker.SerializedEntry<?> interceptRead(PacketByteBuf buf, int i,
//                                                                Operation<DataTracker.SerializedEntry<?>> operation) {
//        try {
//            return operation.call(buf, i);
//        } catch (Exception e) {
//            ClientWorld world = MinecraftClient.getInstance().world;
//            if (world == null) {
//                AirbagLog.LOG.error("Attempted to decode tracked data before the client has loaded the world");
//                return null;
//            }
//
//            Entity erroringEntity = world.getEntityById(entityId.get());
//
//            AirbagLog.LOG.error("Error decoding entity {}", erroringEntity, e);
//        }
//
//        // we'll take care of this later
//        return null;
//    }
//
//    @SuppressWarnings("unused")
//    @WrapWithCondition(method = "read", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
//    private static boolean dontAddNulls(List<DataTracker.SerializedEntry<?>> list, Object serializedEntry) {
//        return serializedEntry != null;
//    }
}
