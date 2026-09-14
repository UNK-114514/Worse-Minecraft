package com.unk.wmc.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public record BlueprintData(
        ItemStack stack, List<ItemStack> remainingSteps,
        List<ItemStack> completedSteps) {
    public static final Codec<BlueprintData> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            ItemStack.SIMPLE_ITEM_CODEC.fieldOf("item").forGetter(BlueprintData::stack),
            ItemStack.SIMPLE_ITEM_CODEC.listOf().fieldOf("remaining_steps").forGetter(BlueprintData::remainingSteps),
            ItemStack.SIMPLE_ITEM_CODEC.listOf().fieldOf("completed_steps").forGetter(BlueprintData::completedSteps)
    ).apply(inst, BlueprintData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BlueprintData> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, BlueprintData::stack,
            ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()), BlueprintData::remainingSteps,
            ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()), BlueprintData::completedSteps,
            BlueprintData::new
    );

    public static class BlueprintComponentBuilder {
        private final LinkedList<ItemStack> remainingSteps = new LinkedList<>();
        private final LinkedList<ItemStack> completedSteps = new LinkedList<>();
        private final ItemStack stack;

        public BlueprintComponentBuilder(ItemStack stack) {
            this.stack = stack;
        }

        public BlueprintComponentBuilder(Item item) {
            this(item.getDefaultInstance());
        }

        public BlueprintComponentBuilder addStep(Item item) {
            remainingSteps.add(item.getDefaultInstance());
            return this;
        }

        public BlueprintComponentBuilder completeStep(Item item) {
            completedSteps.add(item.getDefaultInstance());
            return this;
        }

        public BlueprintData build() {
            return new BlueprintData(stack, remainingSteps, completedSteps);
        }

        public BlueprintData getRandom(int length) {
            int maxLength = Math.min(length, BuiltInRegistries.ITEM.size());

            BlueprintComponentBuilder builder =
                    new BlueprintComponentBuilder(stack);

            List<Item> allItems = BuiltInRegistries.ITEM.stream().collect(Collectors.toList());

            Collections.shuffle(allItems);

            for (Item item : allItems.subList(0, maxLength)) {
                builder.addStep(item);
            }

            return builder.build();
        }
    }
}
