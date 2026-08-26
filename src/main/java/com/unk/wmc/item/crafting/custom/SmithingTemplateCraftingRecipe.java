package com.unk.wmc.item.crafting.custom;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import com.unk.wmc.block.WmcBlocks;
import com.unk.wmc.item.crafting.input.SmithingTemplateCraftingRecipeInput;
import com.unk.wmc.item.crafting.WmcRecipeTypes;
import org.jetbrains.annotations.NotNull;

public interface SmithingTemplateCraftingRecipe extends Recipe<SmithingTemplateCraftingRecipeInput> {
    default @NotNull RecipeType<?> getType() {
        return WmcRecipeTypes.SMITHING_TEMPLATE_CRAFTING_RECIPE_TYPE.get();
    }

    default boolean canCraftInDimensions(int var1, int var2) {
        return true;
    }

    default @NotNull ItemStack getToastSymbol() {
        return new ItemStack(WmcBlocks.SMITHING_TEMPLATE_CRAFTING_TABLE.get().asItem());
    }

    boolean isBaseTemplate(ItemStack var1);

    boolean isTemplateCore(ItemStack var1);
}
