package com.unk.wmc.item;

import com.unk.wmc.Wmc;
import com.unk.wmc.block.WmcBlocks;
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
                    .title(Component.literal("Worse Minecraft"))
                    .icon(() -> {
                        ItemStack stack = new ItemStack(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE);
                        stack.applyComponents(
                                DataComponentMap.builder().set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).build()
                        );
                        return stack;
                    })
                    .displayItems(
                            (itemDisplayParameters, output) -> {
                                output.accept(WmcItems.SMITHING_TEMPLATE_DUST);
                                output.accept(WmcItems.BLUEPRINT);
                                output.accept(WmcItems.RANDOM_BLUEPRINT);

                                output.accept(WmcItems.RAW_TERMINUS_INGOT);
                                output.accept(WmcBlocks.TERMINUS_BLOCK);
                                output.accept(WmcItems.TERMINUS_INGOT);
                                output.accept(WmcItems.TERMINUS_NUGGET);
                                output.accept(WmcItems.TERMINUS_UPGRADE_SMITHING_TEMPLATE);

                                output.accept(WmcItems.COPPER_COGWHEEL);
                                output.accept(WmcItems.IRON_COGWHEEL);
                                output.accept(WmcItems.GOLD_COGWHEEL);
                                output.accept(WmcItems.DIAMOND_COGWHEEL);
                                output.accept(WmcItems.NETHERITE_COGWHEEL);

                                output.accept(WmcBlocks.ACTIVATE_ALTAR);
                                output.accept(WmcBlocks.ACTIVATE_PEDESTAL);

                                output.accept(WmcBlocks.ASSEMBLY_TABLE);
                            }
                    )
                    .build()
    );
}
