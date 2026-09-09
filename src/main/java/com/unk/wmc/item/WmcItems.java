package com.unk.wmc.item;

import com.unk.wmc.Wmc;
import com.unk.wmc.item.custom.BlueprintItem;
import com.unk.wmc.item.custom.SmithingTemplateDustItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WmcItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Wmc.MOD_ID);

    // All Smithing Template Fragments.
//    public static final DeferredItem<Item> NETHERITE_UPGRADE_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("netherite_upgrade_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("sentry_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> DUNE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("dune_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> COAST_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("coast_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> WILD_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("wild_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> WARD_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("ward_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> EYE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("eye_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> VEX_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("vex_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> TIDE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("tide_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("snout_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> RIB_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("rib_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("spire_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("wayfinder_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("shaper_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("silence_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> RAISER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("raiser_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> HOST_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("host_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> FLOW_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("flow_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));
//    public static final DeferredItem<Item> BOLT_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT = ITEMS.register("bolt_armor_trim_smithing_template_fragment", () -> new Item(new Item.Properties()));

    // All Smithing Cores.
    public static final DeferredItem<Item> NETHERITE_UPGRADE_SMITHING_CORE = ITEMS.register("netherite_upgrade_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SENTRY_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("sentry_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DUNE_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("dune_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COAST_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("coast_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WILD_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("wild_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WARD_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("ward_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EYE_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("eye_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VEX_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("vex_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIDE_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("tide_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SNOUT_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("snout_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RIB_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("rib_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPIRE_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("spire_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WAYFINDER_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("wayfinder_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SHAPER_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("shaper_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILENCE_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("silence_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAISER_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("raiser_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HOST_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("host_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLOW_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("flow_armor_trim_smithing_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BOLT_ARMOR_TRIM_SMITHING_CORE = ITEMS.register("bolt_armor_trim_smithing_core", () -> new Item(new Item.Properties()));

    // All Base Smithing Templates.
    public static final DeferredItem<Item> NETHERRACK_BASE_SMITHING_TEMPLATE = ITEMS.register("netherrack_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COBBLESTONE_BASE_SMITHING_TEMPLATE = ITEMS.register("cobblestone_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SANDSTONE_BASE_SMITHING_TEMPLATE = ITEMS.register("sandstone_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_BASE_SMITHING_TEMPLATE = ITEMS.register("mossy_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COBBLED_DEEPSLATE_BASE_SMITHING_TEMPLATE = ITEMS.register("cobbled_deepslate_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> END_STONE_BASE_SMITHING_TEMPLATE = ITEMS.register("end_stone_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PRISMARINE_BASE_SMITHING_TEMPLATE = ITEMS.register("prismarine_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLACKSTONE_BASE_SMITHING_TEMPLATE = ITEMS.register("blackstone_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHER_BRICKS_BASE_SMITHING_TEMPLATE = ITEMS.register("nether_bricks_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PURPUR_BASE_SMITHING_TEMPLATE = ITEMS.register("purpur_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TERRACOTTA_BASE_SMITHING_TEMPLATE = ITEMS.register("terracotta_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SCULK_BASE_SMITHING_TEMPLATE = ITEMS.register("sculk_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BREEZE_BASE_SMITHING_TEMPLATE = ITEMS.register("breeze_base_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_BASE_SMITHING_TEMPLATE = ITEMS.register("copper_base_smithing_template", () -> new Item(new Item.Properties()));

    // All Smithing Stars.
    public static final DeferredItem<Item> THE_NETHER_SMITHING_STAR = ITEMS.register("the_nether_smithing_star", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ILLAGER_SMITHING_STAR = ITEMS.register("illager_smithing_star", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DESERT_SMITHING_STAR = ITEMS.register("desert_smithing_star", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OCEAN_SMITHING_STAR = ITEMS.register("ocean_smithing_star", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> JUNGLE_SMITHING_STAR = ITEMS.register("jungle_smithing_star", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SCULK_SMITHING_STAR = ITEMS.register("sculk_smithing_star", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> THE_END_SMITHING_STAR = ITEMS.register("the_end_smithing_star", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUINS_SMITHING_STAR = ITEMS.register("ruins_smithing_star", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TRIAL_SMITHING_STAR = ITEMS.register("trial_smithing_star", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SMITHING_TEMPLATE_DUST = ITEMS.register("smithing_template_dust", () -> new SmithingTemplateDustItem(new Item.Properties()));
    public static final DeferredItem<Item> BLUEPRINT = ITEMS.register("blueprint", () -> new BlueprintItem(new Item.Properties()));
}
