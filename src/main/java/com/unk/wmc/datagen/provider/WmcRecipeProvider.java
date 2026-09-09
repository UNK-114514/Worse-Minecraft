package com.unk.wmc.datagen.provider;

import com.unk.wmc.Wmc;
import com.unk.wmc.helper.ItemStackHelper;
import com.unk.wmc.item.WmcItemTags;
import com.unk.wmc.item.WmcItems;
import com.unk.wmc.item.crafting.custom.AltarCraftingRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
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
        AltarCraftingRecipeBuilder.of(RecipeCategory.MISC, Items.ECHO_SHARD, Items.EXPERIENCE_BOTTLE, ItemStackHelper.getDustOf(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, false), ItemStackHelper.getDustOf(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, true)).save(output, ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "active_silence_armor_trim_smithing_template"));

//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.NETHERITE_UPGRADE_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.NETHERITE_UPGRADE_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_netherite_upgrade_smithing_template_fragment", has(WmcItems.NETHERITE_UPGRADE_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.SENTRY_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_sentry_armor_trim_smithing_template_fragment", has(WmcItems.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.DUNE_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_dune_armor_trim_smithing_template_fragment", has(WmcItems.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.COAST_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.COAST_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_coast_armor_trim_smithing_template_fragment", has(WmcItems.COAST_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.WILD_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.WILD_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_wild_armor_trim_smithing_template_fragment", has(WmcItems.WILD_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.WARD_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.WARD_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_ward_armor_trim_smithing_template_fragment", has(WmcItems.WARD_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.EYE_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.EYE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_eye_armor_trim_smithing_template_fragment", has(WmcItems.EYE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.VEX_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.VEX_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_vex_armor_trim_smithing_template_fragment", has(WmcItems.VEX_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.TIDE_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_tide_armor_trim_smithing_template_fragment", has(WmcItems.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.SNOUT_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_snout_armor_trim_smithing_template_fragment", has(WmcItems.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.RIB_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.RIB_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_rib_armor_trim_smithing_template_fragment", has(WmcItems.RIB_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.SPIRE_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_spire_armor_trim_smithing_template_fragment", has(WmcItems.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.WAYFINDER_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_wayfinder_armor_trim_smithing_template_fragment", has(WmcItems.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.SHAPER_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_shaper_armor_trim_smithing_template_fragment", has(WmcItems.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.SILENCE_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_silence_armor_trim_smithing_template_fragment", has(WmcItems.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.RAISER_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_raiser_armor_trim_smithing_template_fragment", has(WmcItems.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.HOST_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.HOST_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_host_armor_trim_smithing_template_fragment", has(WmcItems.HOST_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.FLOW_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_flow_armor_trim_smithing_template_fragment", has(WmcItems.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WmcItems.BOLT_ARMOR_TRIM_SMITHING_CORE, 1).pattern("xxx").pattern("xxx").pattern("xxx").define('x', WmcItems.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT).unlockedBy("has_item_bolt_armor_trim_smithing_template_fragment", has(WmcItems.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT)).save(output);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WmcItems.THE_NETHER_SMITHING_STAR, 1).requires(WmcItems.NETHERITE_UPGRADE_SMITHING_CORE).requires(WmcItems.SNOUT_ARMOR_TRIM_SMITHING_CORE).requires(WmcItems.RIB_ARMOR_TRIM_SMITHING_CORE).unlockedBy("has_item_the_nether_smithing_cores", has(WmcItemTags.THE_NETHER_SMITHING_CORES)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WmcItems.ILLAGER_SMITHING_STAR, 1).requires(WmcItems.SENTRY_ARMOR_TRIM_SMITHING_CORE).requires(WmcItems.VEX_ARMOR_TRIM_SMITHING_CORE).unlockedBy("has_item_illager_smithing_cores", has(WmcItemTags.ILLAGER_SMITHING_CORES)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WmcItems.DESERT_SMITHING_STAR, 1).requires(WmcItems.DUNE_ARMOR_TRIM_SMITHING_CORE).unlockedBy("has_item_desert_smithing_cores", has(WmcItemTags.DESERT_SMITHING_CORES)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WmcItems.OCEAN_SMITHING_STAR, 1).requires(WmcItems.COAST_ARMOR_TRIM_SMITHING_CORE).requires(WmcItems.TIDE_ARMOR_TRIM_SMITHING_CORE).unlockedBy("has_item_ocean_smithing_cores", has(WmcItemTags.OCEAN_SMITHING_CORES)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WmcItems.JUNGLE_SMITHING_STAR, 1).requires(WmcItems.WILD_ARMOR_TRIM_SMITHING_CORE).unlockedBy("has_item_jungle_smithing_cores", has(WmcItemTags.JUNGLE_SMITHING_CORES)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WmcItems.SCULK_SMITHING_STAR, 1).requires(WmcItems.WARD_ARMOR_TRIM_SMITHING_CORE).requires(WmcItems.SILENCE_ARMOR_TRIM_SMITHING_CORE).unlockedBy("has_item_sculk_smithing_cores", has(WmcItemTags.SCULK_SMITHING_CORES)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WmcItems.THE_END_SMITHING_STAR, 1).requires(WmcItems.EYE_ARMOR_TRIM_SMITHING_CORE).requires(WmcItems.SPIRE_ARMOR_TRIM_SMITHING_CORE).unlockedBy("has_item_the_end_smithing_cores", has(WmcItemTags.THE_END_SMITHING_CORES)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WmcItems.RUINS_SMITHING_STAR, 1).requires(WmcItems.WAYFINDER_ARMOR_TRIM_SMITHING_CORE).requires(WmcItems.SHAPER_ARMOR_TRIM_SMITHING_CORE).requires(WmcItems.RAISER_ARMOR_TRIM_SMITHING_CORE).requires(WmcItems.HOST_ARMOR_TRIM_SMITHING_CORE).unlockedBy("has_item_ruins_smithing_cores", has(WmcItemTags.RUINS_SMITHING_CORES)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, WmcItems.TRIAL_SMITHING_STAR, 1).requires(WmcItems.FLOW_ARMOR_TRIM_SMITHING_CORE).requires(WmcItems.BOLT_ARMOR_TRIM_SMITHING_CORE).unlockedBy("has_item_trial_smithing_cores", has(WmcItemTags.TRIAL_SMITHING_CORES)).save(output);
    }
}
