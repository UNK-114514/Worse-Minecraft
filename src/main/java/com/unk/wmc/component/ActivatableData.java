package com.unk.wmc.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("unused")
public record ActivatableData(ResourceLocation itemId, boolean isActivated) {
    public static final ActivatableData EMPTY = new ActivatableData(null, false);

    public static final Codec<ActivatableData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(ActivatableData::itemId),
            Codec.BOOL.fieldOf("is_activated").forGetter(ActivatableData::isActivated)
    ).apply(instance, ActivatableData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ActivatableData> STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC, ActivatableData::itemId,
            ByteBufCodecs.BOOL, ActivatableData::isActivated,
            ActivatableData::new
    );
}
