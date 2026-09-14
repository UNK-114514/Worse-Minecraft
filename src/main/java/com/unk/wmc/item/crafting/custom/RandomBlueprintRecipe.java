package com.unk.wmc.item.crafting.custom;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record RandomBlueprintRecipe(
        ShapedRecipePattern pattern, ItemStack result,
        Item resultContains, CraftingBookCategory category, int count) implements CraftingRecipe {
    @Override
    public @NotNull CraftingBookCategory category() {
        return category;
    }

    @Override
    public boolean matches(@NotNull CraftingInput craftingInput, @NotNull Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(@NotNull CraftingInput craftingInput, HolderLookup.@NotNull Provider provider) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

//    public static class RandomBlueprintRecipeSerializer implements RecipeSerializer<RandomBlueprintRecipe> {
//        Codec<RandomBlueprintRecipe> CODEC = RecordCodecBuilder.create(inst -> {
//            inst.group(
//                    ShapedRecipePattern.MAP_CODEC.forGetter(RandomBlueprintRecipe::pattern),
//                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(RandomBlueprintRecipe::result),
//                    ItemStack.SIMPLE_ITEM_CODEC.fieldOf("result_contains").forGetter((recipe) -> recipe.resultContains.getDefaultInstance())
//            )
//        })
//
//
//        @Override
//        public MapCodec<RandomBlueprintRecipe> codec() {
//            return null;
//        }
//
//        @Override
//        public StreamCodec<RegistryFriendlyByteBuf, RandomBlueprintRecipe> streamCodec() {
//            return null;
//        }
//    }
}
