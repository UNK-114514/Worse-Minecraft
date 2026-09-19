package com.unk.wmc.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record SimpleItemStackData(ItemStack stack) {
    public static final Codec<SimpleItemStackData> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            ItemStack.SIMPLE_ITEM_CODEC.fieldOf("item").forGetter(SimpleItemStackData::stack)
    ).apply(inst, SimpleItemStackData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SimpleItemStackData> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, SimpleItemStackData::stack,
            SimpleItemStackData::new
    );

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SimpleItemStackData(ItemStack objContains))) return false;
        return ItemStack.matches(stack, objContains);
    }

    @Override
    public int hashCode() {
        return ItemStack.hashItemAndComponents(stack);
    }
}
