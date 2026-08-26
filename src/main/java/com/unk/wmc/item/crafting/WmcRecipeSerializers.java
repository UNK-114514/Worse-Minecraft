package com.unk.wmc.item.crafting;

import com.unk.wmc.Wmc;
import com.unk.wmc.item.crafting.custom.AltarCraftingRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.unk.wmc.item.crafting.custom.SmithingTemplateCraftingRecipeImpl;

public class WmcRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, Wmc.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SmithingTemplateCraftingRecipeImpl>> SMITHING_TEMPLATE_CRAFTING_RECIPE_SERIALIZER = SERIALIZERS.register("smithing_template_crafting_recipe_serializer", SmithingTemplateCraftingRecipeImpl.Serializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<AltarCraftingRecipe>> ALTAR_RECIPE_SERIALIZER = SERIALIZERS.register("altar_recipe_serializer", AltarCraftingRecipe.Serializer::new);
}
