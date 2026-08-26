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
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AltarCraftingRecipeBuilder implements RecipeBuilder {
    private final ItemLike n, en, e, es, s, ws, w, wn, mid;
    private final ItemStack result;

    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    @Nullable
    private String group;
    private final RecipeCategory category;

    public AltarCraftingRecipeBuilder(RecipeCategory category, ItemLike n, ItemLike en, ItemLike e, ItemLike es,
                                      ItemLike s, ItemLike ws, ItemLike w, ItemLike wn, ItemLike mid, ItemStack result) {
        this.n = n;
        this.en = en;
        this.e = e;
        this.es = es;
        this.s = s;
        this.ws = ws;
        this.w = w;
        this.wn = wn;
        this.mid = mid;
        this.result = result;
        this.category = category;
    }

    public static AltarCraftingRecipeBuilder of(RecipeCategory category, ItemLike n, ItemLike en, ItemLike e, ItemLike es,
                                                ItemLike s, ItemLike ws, ItemLike w, ItemLike wn, ItemLike mid, ItemStack result) {
        return new AltarCraftingRecipeBuilder(category, n, en, e, es,
                s, ws, w, wn, mid, result);
    }

    public static AltarCraftingRecipeBuilder of(RecipeCategory category, ItemLike a, ItemLike b, ItemLike mid, ItemStack result) {
        return new AltarCraftingRecipeBuilder(category, a, b, a, b, a, b, a, b, mid, result);
    }

    @Override
    public @NotNull RecipeBuilder unlockedBy(@NotNull String s, @NotNull Criterion<?> criterion) {
        this.criteria.put(s, criterion);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String s) {
        this.group = s;
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result.getItem();
    }

    @Override
    public void save(RecipeOutput recipeOutput, @NotNull ResourceLocation resourceLocation) {
        Advancement.Builder builder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation))
                .rewards(AdvancementRewards.Builder.recipe(resourceLocation))
                .requirements(AdvancementRequirements.Strategy.OR);

        Objects.requireNonNull(builder);
        this.criteria.forEach(builder::addCriterion);

        AltarCraftingRecipe recipe = new AltarCraftingRecipe(
                Ingredient.of(n), Ingredient.of(en), Ingredient.of(e), Ingredient.of(es), Ingredient.of(s),
                Ingredient.of(ws), Ingredient.of(w), Ingredient.of(wn), Ingredient.of(mid), result
        );

        recipeOutput.accept(resourceLocation, recipe, builder.build(resourceLocation.withPrefix("recipes/")));
    }

    public @Nullable String getGroup() {
        return group;
    }

    public RecipeCategory getCategory() {
        return category;
    }
}
