package com.unk.wmc.datagen.provider;

import com.unk.wmc.Wmc;
import com.unk.wmc.helper.ItemStackHelper;
import com.unk.wmc.item.crafting.custom.AltarCraftingRecipeBuilder;
import com.unk.wmc.item.crafting.custom.SmithingTemplateRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class WmcRecipeProvider extends RecipeProvider {
    public WmcRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        AltarCraftingRecipeBuilder.of(RecipeCategory.MISC, Items.ECHO_SHARD, Items.EXPERIENCE_BOTTLE, ItemStackHelper.getDustOf(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, false), ItemStackHelper.getDustOf(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, true)).save(output, ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "activate_silence_armor_trim_smithing_template"));

        SpecialRecipeBuilder.special(SmithingTemplateRecipe::new).save(output, ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "template_from_dust"));
    }
}
