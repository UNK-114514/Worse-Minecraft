package com.unk.wmc.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public record BlueprintData(
        ItemStack result, List<ItemStack> remainingSteps,
        List<ItemStack> completedSteps) {
    public static final Codec<BlueprintData> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            ItemStack.SIMPLE_ITEM_CODEC.fieldOf("item").forGetter(BlueprintData::result),
            ItemStack.SIMPLE_ITEM_CODEC.listOf().fieldOf("remaining_steps").forGetter(BlueprintData::remainingSteps),
            ItemStack.SIMPLE_ITEM_CODEC.listOf().fieldOf("completed_steps").forGetter(BlueprintData::completedSteps)
    ).apply(inst, BlueprintData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BlueprintData> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, BlueprintData::result,
            ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()), BlueprintData::remainingSteps,
            ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()), BlueprintData::completedSteps,
            BlueprintData::new
    );

    private static boolean isSameItemList(List<ItemStack> a, List<ItemStack> b) {
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getItem() != b.get(i).getItem()) return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof
                BlueprintData(
                        ItemStack dataResult,
                        List<ItemStack> dataRemaining,
                        List<ItemStack> dataCompleted
                )
        )) return false;

        return ItemStack.isSameItemSameComponents(dataResult, result)
                && isSameItemList(dataRemaining, remainingSteps)
                && isSameItemList(dataCompleted, completedSteps);
    }

    public static class BlueprintComponentBuilder {
        private final LinkedList<ItemStack> remainingSteps = new LinkedList<>();
        private final LinkedList<ItemStack> completedSteps = new LinkedList<>();
        private final ItemStack result;

        public BlueprintComponentBuilder(ItemStack result) {
            this.result = result;
        }

        public BlueprintComponentBuilder(Item item) {
            this(item.getDefaultInstance());
        }

        public BlueprintComponentBuilder addStep(ItemStack stack) {
            remainingSteps.add(stack);
            return this;
        }

        public BlueprintComponentBuilder completeStep(Item item) {
            completedSteps.add(item.getDefaultInstance());
            return this;
        }

        public BlueprintData build() {
            return new BlueprintData(result, remainingSteps, completedSteps);
        }

        public BlueprintData getRandom(int randomItemCount) {
            int maxLength = Math.min(randomItemCount, BuiltInRegistries.ITEM.size());

            BlueprintComponentBuilder builder =
                    new BlueprintComponentBuilder(result);

            List<Item> allItems = BuiltInRegistries.ITEM.stream().filter(item -> item != Items.AIR).collect(Collectors.toList());

            Collections.shuffle(allItems);

            for (Item randomItem : allItems.subList(0, maxLength)) {
                builder.addStep(randomItem.getDefaultInstance());
            }

            return builder.build();
        }
    }
}
