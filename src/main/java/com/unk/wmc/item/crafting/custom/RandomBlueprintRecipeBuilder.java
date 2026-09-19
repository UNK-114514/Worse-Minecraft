package com.unk.wmc.item.crafting.custom;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RandomBlueprintRecipeBuilder {
    private final RecipeCategory category;
    private final ItemStack resultContains;
    private final int randomItemCount;
    private final List<String> rows;
    private final Map<Character, Ingredient> key;
    private final Map<String, Criterion<?>> criteria;

    @Nullable
    private String group;

    public RandomBlueprintRecipeBuilder(RecipeCategory category, int randomItemCount, ItemStack resultContains) {
        this.rows = Lists.newArrayList();
        this.key = Maps.newLinkedHashMap();
        this.criteria = new LinkedHashMap<>();
        this.category = category;
        this.resultContains = resultContains;
        this.randomItemCount = randomItemCount;
    }

    public static RandomBlueprintRecipeBuilder of(RecipeCategory category, int randomItemCount, ItemStack resultContains) {
        return new RandomBlueprintRecipeBuilder(category, randomItemCount, resultContains);
    }

    public static RandomBlueprintRecipeBuilder of(RecipeCategory category, int randomItemCount, Item resultContains) {
        return new RandomBlueprintRecipeBuilder(category, randomItemCount, resultContains.getDefaultInstance());
    }

    public RandomBlueprintRecipeBuilder define(Character symbol, Ingredient ingredient) {
        if (this.key.containsKey(symbol)) {
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
        } else if (symbol == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.key.put(symbol, ingredient);
            return this;
        }
    }

    public RandomBlueprintRecipeBuilder define(Character symbol, ItemLike item) {
        define(symbol, Ingredient.of(item));
        return this;
    }

    public RandomBlueprintRecipeBuilder pattern(String pattern) {
        if (!this.rows.isEmpty() && pattern.length() != this.rows.getFirst().length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.rows.add(pattern);
            return this;
        }
    }

    public RandomBlueprintRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public void save(RecipeOutput output, ResourceLocation location) {
        ShapedRecipePattern pattern = this.ensureValid(location);

        Advancement.Builder advancementBuilder =
                output.advancement()
                        .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location))
                        .rewards(AdvancementRewards.Builder.recipe(location))
                        .requirements(AdvancementRequirements.Strategy.OR);

        Objects.requireNonNull(advancementBuilder);

        this.criteria.forEach(advancementBuilder::addCriterion);

        RandomBlueprintRecipe recipe = new RandomBlueprintRecipe(
                pattern,
                resultContains,
                RecipeBuilder.determineBookCategory(this.category),
                randomItemCount
        );
        output.accept(location, recipe, advancementBuilder.build(
                location.withPrefix("recipes/" + this.category.getFolderName() + "/")
        ));
    }

    private ShapedRecipePattern ensureValid(ResourceLocation location) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
        } else {
            return ShapedRecipePattern.of(this.key, this.rows);
        }
    }
}
