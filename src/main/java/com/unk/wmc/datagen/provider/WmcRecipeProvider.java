package com.unk.wmc.datagen.provider;

import com.unk.wmc.Wmc;
import com.unk.wmc.helper.ItemStackHelper;
import com.unk.wmc.item.WmcItems;
import com.unk.wmc.item.crafting.custom.AltarCraftingRecipeBuilder;
import com.unk.wmc.item.crafting.custom.RandomBlueprintRecipeBuilder;
import com.unk.wmc.item.crafting.custom.SmithingTemplateRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class WmcRecipeProvider extends RecipeProvider {
    public WmcRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        AltarCraftingRecipeBuilder.of(RecipeCategory.MISC, Items.ECHO_SHARD, Items.EXPERIENCE_BOTTLE, ItemStackHelper.getDustOf(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, false), ItemStackHelper.getDustOf(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, true)).save(output, ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "activate_silence_armor_trim_smithing_template_dust"));

        RandomBlueprintRecipeBuilder.of(RecipeCategory.MISC, 128, WmcItems.RANDOM_CORE.get()).pattern("xxx").pattern("xnx").pattern("xxx").define('x', Items.PAPER).define('n', Blocks.NETHERITE_BLOCK).unlockedBy("has_item_netherite_block", has(Blocks.NETHERITE_BLOCK)).save(output, ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "random_128_contains_random_core"));

        SpecialRecipeBuilder.special(SmithingTemplateRecipe::new).save(output, ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "template_from_dust"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.COPPER_COGWHEEL).pattern(" x ").pattern("x x").pattern(" x ").define('x', Items.COPPER_INGOT).unlockedBy("has_item_copper_ingot", has(Items.COPPER_INGOT)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.IRON_COGWHEEL).pattern("xxx").pattern("xcx").pattern("xxx").define('x', Items.IRON_INGOT).define('c', WmcItems.COPPER_COGWHEEL).unlockedBy("has_item_copper_cogwheel", has(WmcItems.COPPER_COGWHEEL)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.GOLD_COGWHEEL).pattern("xxx").pattern("xcx").pattern("xxx").define('x', Items.GOLD_INGOT).define('c', WmcItems.IRON_COGWHEEL).unlockedBy("has_item_iron_cogwheel", has(WmcItems.IRON_COGWHEEL)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.DIAMOND_COGWHEEL).pattern("xxx").pattern("xcx").pattern("xxx").define('x', Items.DIAMOND).define('c', WmcItems.GOLD_COGWHEEL).unlockedBy("has_item_gold_cogwheel", has(WmcItems.GOLD_COGWHEEL)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.NETHERITE_COGWHEEL).pattern("xxx").pattern("xcx").pattern("xxx").define('x', Items.NETHERITE_INGOT).define('c', WmcItems.DIAMOND_COGWHEEL).unlockedBy("has_item_diamond_cogwheel", has(WmcItems.DIAMOND_COGWHEEL)).save(output);
    }
}
