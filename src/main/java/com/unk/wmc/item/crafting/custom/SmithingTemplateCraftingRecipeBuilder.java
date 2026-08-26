package com.unk.wmc.item.crafting.custom;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class SmithingTemplateCraftingRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final ItemLike baseTemplate;
    private final ItemLike templateCore;
    private final ItemStack result;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;

    private SmithingTemplateCraftingRecipeBuilder(RecipeCategory category, ItemLike baseTemplate, ItemLike templateCore, ItemStack result) {
        this.category = category;
        this.baseTemplate = baseTemplate;
        this.templateCore = templateCore;
        this.result = result;
    }

    public static SmithingTemplateCraftingRecipeBuilder of(RecipeCategory category, ItemLike baseTemplate, ItemLike templateCore, ItemStack result) {
        return new SmithingTemplateCraftingRecipeBuilder(category, baseTemplate, templateCore, result);
    }

    @Override
    public @NotNull SmithingTemplateCraftingRecipeBuilder unlockedBy(@NotNull String name, @NotNull Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public @NotNull SmithingTemplateCraftingRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result.getItem();
    }

    @Override
    public void save(@NotNull RecipeOutput output, @NotNull ResourceLocation recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }

        Advancement.Builder advancement = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);

        this.criteria.forEach(advancement::addCriterion);

        SmithingTemplateCraftingRecipeImpl recipe = new SmithingTemplateCraftingRecipeImpl(
                Ingredient.of(this.baseTemplate),
                Ingredient.of(this.templateCore),
                this.result
        );

        output.accept(
                recipeId,
                recipe,
                advancement.build(recipeId.withPrefix("recipes/" + this.category.getFolderName() + "/"))
        );
    }

    @SuppressWarnings("unused")
    @Nullable
    public String getGroup() {
        return group;
    }
}