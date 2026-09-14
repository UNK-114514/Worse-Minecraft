package com.unk.wmc.compat.jei;

import com.unk.wmc.Wmc;
import com.unk.wmc.item.crafting.custom.AltarCraftingRecipe;
import mezz.jei.api.recipe.RecipeType;

public class WmcJeiRecipeTypes {
    public static final RecipeType<AltarCraftingRecipe> ALTAR_CRAFTING =
            RecipeType.create(Wmc.MOD_ID, "altar_crafting", AltarCraftingRecipe.class);
}
