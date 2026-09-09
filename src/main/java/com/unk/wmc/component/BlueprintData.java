package com.unk.wmc.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record BlueprintData(
        ResourceLocation itemId, List<ResourceLocation> remainingSteps,
        List<ResourceLocation> completedSteps) {
    public static final Codec<BlueprintData> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            ResourceLocation.CODEC.fieldOf("item").forGetter(BlueprintData::itemId),
            ResourceLocation.CODEC.listOf().fieldOf("remaining_steps").forGetter(BlueprintData::remainingSteps),
            ResourceLocation.CODEC.listOf().fieldOf("completed_steps").forGetter(BlueprintData::completedSteps)
    ).apply(inst, BlueprintData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BlueprintData> STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC, BlueprintData::itemId,
            ResourceLocation.STREAM_CODEC.apply(ByteBufCodecs.list()), BlueprintData::remainingSteps,
            ResourceLocation.STREAM_CODEC.apply(ByteBufCodecs.list()), BlueprintData::completedSteps,
            BlueprintData::new
    );
}
