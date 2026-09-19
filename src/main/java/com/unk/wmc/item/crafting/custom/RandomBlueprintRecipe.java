package com.unk.wmc.item.crafting.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.unk.wmc.component.BlueprintData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.item.WmcItems;
import com.unk.wmc.item.crafting.WmcRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record RandomBlueprintRecipe(
        ShapedRecipePattern pattern, ItemStack resultContains,
        CraftingBookCategory craftingBookCategory, int randomItemCount) implements CraftingRecipe {
    @Override
    public @NotNull CraftingBookCategory category() {
        return craftingBookCategory;
    }

    @Override
    public boolean matches(@NotNull CraftingInput craftingInput, @NotNull Level level) {
        return this.pattern.matches(craftingInput);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput craftingInput, HolderLookup.@NotNull Provider provider) {
        ItemStack resultStack = WmcItems.RANDOM_BLUEPRINT.get().getDefaultInstance();
        BlueprintData.BlueprintComponentBuilder builder =
                new BlueprintData.BlueprintComponentBuilder(resultContains);

        resultStack.set(WmcDataComponentTypes.BLUEPRINT, builder.getRandom(randomItemCount));
        return resultStack;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
        return WmcItems.RANDOM_BLUEPRINT.get().getDefaultInstance();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return WmcRecipeSerializers.RANDOM_BLUEPRINT.get();
    }

    public static class Serializer implements RecipeSerializer<RandomBlueprintRecipe> {
        public static final MapCodec<RandomBlueprintRecipe> CODEC =
                RecordCodecBuilder.mapCodec(
                        inst -> inst.group(
                                ShapedRecipePattern.MAP_CODEC.forGetter(RandomBlueprintRecipe::pattern),
                                ItemStack.SIMPLE_ITEM_CODEC.fieldOf("result_contains").forGetter(RandomBlueprintRecipe::resultContains),
                                CraftingBookCategory.CODEC.fieldOf("category").forGetter(RandomBlueprintRecipe::category),
                                Codec.INT.fieldOf("random_item_count").forGetter(RandomBlueprintRecipe::randomItemCount)
                        ).apply(inst, RandomBlueprintRecipe::new)
                );

        public static StreamCodec<RegistryFriendlyByteBuf, RandomBlueprintRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        ShapedRecipePattern.STREAM_CODEC, RandomBlueprintRecipe::pattern,
                        ItemStack.STREAM_CODEC, RandomBlueprintRecipe::resultContains,
                        CraftingBookCategory.STREAM_CODEC, RandomBlueprintRecipe::craftingBookCategory,
                        ByteBufCodecs.INT, RandomBlueprintRecipe::randomItemCount,
                        RandomBlueprintRecipe::new
                );

        @Override
        public @NotNull MapCodec<RandomBlueprintRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, RandomBlueprintRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
