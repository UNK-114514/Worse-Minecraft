package com.unk.wmc.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public record SimpleItemData(ResourceLocation itemId) {
    public static final Codec<SimpleItemData> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            ResourceLocation.CODEC.fieldOf("item").forGetter(SimpleItemData::itemId)
    ).apply(inst, SimpleItemData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SimpleItemData> STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC, SimpleItemData::itemId,
            SimpleItemData::new
    );
}
