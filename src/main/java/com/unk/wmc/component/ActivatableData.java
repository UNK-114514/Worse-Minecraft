package com.unk.wmc.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ActivatableData(boolean isActivated) {
    public static final ActivatableData ACTIVATED = new ActivatableData(true);
    public static final ActivatableData UNACTIVATED = new ActivatableData(false);

    public static final Codec<ActivatableData> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.BOOL.fieldOf("is_activated").forGetter(ActivatableData::isActivated)
    ).apply(inst, ActivatableData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ActivatableData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, ActivatableData::isActivated,
            ActivatableData::new
    );
}
