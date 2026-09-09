package com.unk.wmc.item;

import com.unk.wmc.Wmc;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class WmcCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Wmc.MOD_ID);

    public static final Supplier<CreativeModeTab> WST_CREATIVE_TAB = CREATIVE_MODE_TABS.register("wst_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.literal("Wmc Tab"))
                    .icon(() -> {
                        ItemStack stack = new ItemStack(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE);
                        stack.applyComponents(
                                DataComponentMap.builder().set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).build()
                        );
                        return stack;
                    })
                    .displayItems(
                            (itemDisplayParameters, output) -> {
//                                output.accept(WmcItems.NETHERITE_UPGRADE_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.COAST_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.WILD_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.WARD_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.EYE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.VEX_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.RIB_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.HOST_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
//                                output.accept(WmcItems.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE_FRAGMENT);
                                output.accept(WmcItems.NETHERITE_UPGRADE_SMITHING_CORE);
                                output.accept(WmcItems.SENTRY_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.DUNE_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.COAST_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.WILD_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.WARD_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.EYE_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.VEX_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.TIDE_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.SNOUT_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.RIB_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.SPIRE_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.WAYFINDER_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.SHAPER_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.SILENCE_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.RAISER_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.HOST_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.FLOW_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.BOLT_ARMOR_TRIM_SMITHING_CORE);
                                output.accept(WmcItems.THE_NETHER_SMITHING_STAR);
                                output.accept(WmcItems.ILLAGER_SMITHING_STAR);
                                output.accept(WmcItems.DESERT_SMITHING_STAR);
                                output.accept(WmcItems.OCEAN_SMITHING_STAR);
                                output.accept(WmcItems.TRIAL_SMITHING_STAR);
                                output.accept(WmcItems.SCULK_SMITHING_STAR);
                                output.accept(WmcItems.THE_END_SMITHING_STAR);
                                output.accept(WmcItems.RUINS_SMITHING_STAR);
                                output.accept(WmcItems.NETHERRACK_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.COBBLESTONE_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.SANDSTONE_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.MOSSY_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.COBBLED_DEEPSLATE_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.END_STONE_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.PRISMARINE_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.BLACKSTONE_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.NETHER_BRICKS_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.PURPUR_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.TERRACOTTA_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.SCULK_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.BREEZE_BASE_SMITHING_TEMPLATE);
                                output.accept(WmcItems.COPPER_BASE_SMITHING_TEMPLATE);
                            }
                    )
                    .build()
    );
}
