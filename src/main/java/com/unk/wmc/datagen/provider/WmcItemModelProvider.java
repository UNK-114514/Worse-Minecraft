package com.unk.wmc.datagen.provider;

import com.unk.wmc.Wmc;
import com.unk.wmc.block.WmcBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class WmcItemModelProvider extends ItemModelProvider {
    public WmcItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Wmc.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        basicItem(WmcItems.NETHERITE_UPGRADE_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.COAST_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.WILD_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.WARD_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.EYE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.VEX_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.RIB_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.HOST_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT.get());
//        basicItem(WmcItems.NETHERITE_UPGRADE_SMITHING_CORE.get());
//        basicItem(WmcItems.SENTRY_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.DUNE_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.COAST_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.WILD_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.WARD_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.EYE_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.VEX_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.TIDE_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.SNOUT_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.RIB_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.SPIRE_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.WAYFINDER_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.SHAPER_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.SILENCE_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.RAISER_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.HOST_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.FLOW_ARMOR_TRIM_SMITHING_CORE.get());
//        basicItem(WmcItems.BOLT_ARMOR_TRIM_SMITHING_CORE.get());

        simpleBlockItem(WmcBlocks.ACTIVATE_ALTAR.get());
        simpleBlockItem(WmcBlocks.ACTIVATE_PEDESTAL.get());

//        basicItem(WmcItems.THE_NETHER_SMITHING_STAR.get());
//        basicItem(WmcItems.ILLAGER_SMITHING_STAR.get());
//        basicItem(WmcItems.TEMPLE_SMITHING_STAR.get());
//        basicItem(WmcItems.OCEAN_SMITHING_STAR.get());
//        basicItem(WmcItems.SCULK_SMITHING_STAR.get());
//        basicItem(WmcItems.THE_END_SMITHING_STAR.get());
//        basicItem(WmcItems.RUINS_SMITHING_STAR.get());
//        basicItem(WmcItems.TRIAL_SMITHING_STAR.get());
//        basicItem(WmcItems.NETHERRACK_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.COBBLESTONE_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.SANDSTONE_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.MOSSY_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.COBBLED_DEEPSLATE_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.END_STONE_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.PRISMARINE_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.BLACKSTONE_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.NETHER_BRICKS_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.PURPUR_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.TERRACOTTA_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.SCULK_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.BREEZE_BASE_SMITHING_TEMPLATE.get());
//        basicItem(WmcItems.COPPER_BASE_SMITHING_TEMPLATE.get());
    }
}
