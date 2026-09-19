package com.unk.wmc.network;

import com.unk.wmc.Wmc;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record C2SSimpleKeyPressedPayload(int keyCode) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<C2SSimpleKeyPressedPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "simple_key_pressed"));

    public static final StreamCodec<RegistryFriendlyByteBuf, C2SSimpleKeyPressedPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT, C2SSimpleKeyPressedPayload::keyCode,
                    C2SSimpleKeyPressedPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
