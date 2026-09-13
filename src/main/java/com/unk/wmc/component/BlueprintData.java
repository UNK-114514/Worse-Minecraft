package com.unk.wmc.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static class BlueprintComponentBuilder {
        private final LinkedList<ResourceLocation> remainingSteps = new LinkedList<>();
        private final LinkedList<ResourceLocation> completedSteps = new LinkedList<>();
        private final ResourceLocation itemId;
        private final Item item;

        public BlueprintComponentBuilder(Item item) {
            this.itemId = BuiltInRegistries.ITEM.getKey(item);
            this.item = item;
        }

        public BlueprintComponentBuilder addStep(Item item) {
            ResourceLocation loc = BuiltInRegistries.ITEM.getKey(item);
            remainingSteps.add(loc);
            return this;
        }

        public BlueprintComponentBuilder completeStep(Item item) {
            ResourceLocation loc = BuiltInRegistries.ITEM.getKey(item);
            completedSteps.add(loc);
            return this;
        }

        public BlueprintData build() {
            return new BlueprintData(itemId, remainingSteps, completedSteps);
        }

        public BlueprintData getRandom(int length) {
            int maxLength = Math.min(length, BuiltInRegistries.ITEM.size());

            BlueprintComponentBuilder builder =
                    new BlueprintComponentBuilder(item);

            List<Item> allItems = BuiltInRegistries.ITEM.stream().collect(Collectors.toList());

            Collections.shuffle(allItems);

            for (Item item : allItems.subList(0, maxLength)) {
                builder.addStep(item);
            }

            return builder.build();
        }
    }
}
