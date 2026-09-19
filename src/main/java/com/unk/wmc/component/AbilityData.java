package com.unk.wmc.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record AbilityData(int abilityIndex) {
    public static final Codec<AbilityData> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.INT.fieldOf("index").forGetter(AbilityData::abilityIndex)
    ).apply(inst, AbilityData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, AbilityData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, AbilityData::abilityIndex,
            AbilityData::new
    );
}
