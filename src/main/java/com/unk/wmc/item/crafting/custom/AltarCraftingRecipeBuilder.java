package com.unk.wmc.item.crafting.custom;

import com.unk.wmc.Wmc;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AltarCraftingRecipeBuilder implements RecipeBuilder {
    private final ItemStack n, en, e, es, s, ws, w, wn, mid;
    private final ItemStack result;

    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    @Nullable
    private String group;
    private final RecipeCategory category;

    public AltarCraftingRecipeBuilder(RecipeCategory category, ItemStack n, ItemStack en, ItemStack e, ItemStack es,
                                      ItemStack s, ItemStack ws, ItemStack w, ItemStack wn, ItemStack mid, ItemStack result) {
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

    public static AltarCraftingRecipeBuilder of(
            RecipeCategory category, ItemStack n, ItemStack en, ItemStack e, ItemStack es,
            ItemStack s, ItemStack ws, ItemStack w, ItemStack wn, ItemStack mid, ItemStack result) {
        return new AltarCraftingRecipeBuilder(category, n, en, e, es, s, ws, w, wn, mid, result);
    }

    public static AltarCraftingRecipeBuilder of(
            RecipeCategory category, ItemLike n, ItemLike en, ItemLike e, ItemLike es,
            ItemLike s, ItemLike ws, ItemLike w, ItemLike wn, ItemLike mid, ItemStack result) {
        return of(
                category,
                n.asItem().getDefaultInstance(),
                en.asItem().getDefaultInstance(),
                e.asItem().getDefaultInstance(),
                es.asItem().getDefaultInstance(),
                s.asItem().getDefaultInstance(),
                ws.asItem().getDefaultInstance(),
                w.asItem().getDefaultInstance(),
                wn.asItem().getDefaultInstance(),
                mid.asItem().getDefaultInstance(),
                result
        );
    }

    public static AltarCraftingRecipeBuilder of(
            RecipeCategory category, ItemStack a, ItemStack b,
            ItemStack mid, ItemStack result) {
        return of(category, a, b, a, b, a, b, a, b, mid, result);
    }

    public static AltarCraftingRecipeBuilder of(
            RecipeCategory category, ItemLike a, ItemLike b,
            ItemLike mid, ItemStack result) {
        return of(
                category,
                a.asItem().getDefaultInstance(),
                b.asItem().getDefaultInstance(),
                mid.asItem().getDefaultInstance(),
                result
        );
    }

    public static AltarCraftingRecipeBuilder of(
            RecipeCategory category, ItemLike a, ItemLike b,
            ItemStack mid, ItemStack result) {
        return of(
                category,
                a.asItem().getDefaultInstance(),
                b.asItem().getDefaultInstance(),
                a.asItem().getDefaultInstance(),
                b.asItem().getDefaultInstance(),
                a.asItem().getDefaultInstance(),
                b.asItem().getDefaultInstance(),
                a.asItem().getDefaultInstance(),
                b.asItem().getDefaultInstance(),
                mid,
                result
        );
    }

    @Override
    public @NotNull RecipeBuilder unlockedBy(@NotNull String s, @NotNull Criterion<?> criterion) {
        criteria.put(s, criterion);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String s) {
        this.group = s;
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return result.getItem();
    }

    @Override
    public void save(RecipeOutput recipeOutput, @NotNull ResourceLocation resourceLocation) {
        Advancement.Builder builder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation))
                .rewards(AdvancementRewards.Builder.recipe(resourceLocation))
                .requirements(AdvancementRequirements.Strategy.OR);

        Objects.requireNonNull(builder);
        criteria.forEach(builder::addCriterion);

        AltarCraftingRecipe recipe = new AltarCraftingRecipe(n, en, e, es, s, ws, w, wn, mid, result);

        recipeOutput.accept(resourceLocation, recipe, builder.build(resourceLocation.withPrefix("recipes/")));
    }

    public void save(@NotNull RecipeOutput output) {
        save(output, ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID,
                "activate_" + BuiltInRegistries.ITEM.getKey(mid.getItem()).getPath()));
    }

    public @Nullable String getGroup() {
        return group;
    }

    public RecipeCategory getCategory() {
        return category;
    }
}
