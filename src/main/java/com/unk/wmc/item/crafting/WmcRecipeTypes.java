package com.unk.wmc.item.crafting;

import com.unk.wmc.Wmc;
import com.unk.wmc.item.crafting.custom.AltarCraftingRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WmcRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, Wmc.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<AltarCraftingRecipe>> ALTAR_CRAFTING = RECIPE_TYPES.register("altar_crafting", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "altar_crafting_type")));
}
