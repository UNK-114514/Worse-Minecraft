package com.unk.wmc.item.crafting;

import com.unk.wmc.Wmc;
import com.unk.wmc.item.crafting.custom.AltarCraftingRecipe;
import com.unk.wmc.item.crafting.custom.RandomBlueprintRecipe;
import com.unk.wmc.item.crafting.custom.SmithingTemplateRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WmcRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, Wmc.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<AltarCraftingRecipe>> ALTAR_RECIPE =
            SERIALIZERS.register("crafting_altar", AltarCraftingRecipe.Serializer::new);

    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<SmithingTemplateRecipe>> SMITHING_TEMPLATE_FROM_CORE =
            SERIALIZERS.register("crafting_special_smithing_template", () -> new SimpleCraftingRecipeSerializer<>(SmithingTemplateRecipe::new));

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<RandomBlueprintRecipe>> RANDOM_BLUEPRINT =
            SERIALIZERS.register("random_blueprint", RandomBlueprintRecipe.Serializer::new);
}
