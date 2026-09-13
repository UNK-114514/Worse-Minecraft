package com.unk.wmc.datagen.provider;

import com.unk.wmc.Wmc;
import com.unk.wmc.block.WmcBlocks;
import com.unk.wmc.item.WmcItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class WmcEnUsProvider extends LanguageProvider {
    public WmcEnUsProvider(PackOutput output) {
        super(output, Wmc.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(WmcItems.SMITHING_TEMPLATE_DUST.get(), "Smiting Template Dust");
        add(WmcItems.EMPTY_SMITING_TEMPLATE.get(), "Empty Smiting Template");
        add(WmcItems.BLUEPRINT.get(), "Blueprint");
        add(WmcItems.RANDOM_BLUEPRINT.get(), "Random Blueprint");

        add(WmcItems.RAW_TERMINUS_INGOT.get(), "Raw Terminus Ingot");
        add(WmcBlocks.TERMINUS_BLOCK.get(), "Terminus Block");
        add(WmcItems.TERMINUS_INGOT.get(), "Terminus Ingot");
        add(WmcItems.TERMINUS_NUGGET.get(), "Terminus Nugget");
        add(WmcItems.TERMINUS_UPGRADE_SMITHING_TEMPLATE.get(), "Smithing Template");

        add(WmcItems.COPPER_COGWHEEL.get(), "Copper Cogwheel");
        add(WmcItems.IRON_COGWHEEL.get(), "Iron Cogwheel");
        add(WmcItems.GOLD_COGWHEEL.get(), "Gold Cogwheel");
        add(WmcItems.DIAMOND_COGWHEEL.get(), "Diamond Cogwheel");
        add(WmcItems.NETHERITE_COGWHEEL.get(), "Netherite Cogwheel");

        add(WmcBlocks.ACTIVATE_ALTAR.get(), "Activate Altar");
        add(WmcBlocks.ACTIVATE_PEDESTAL.get(), "Activate Pedestal");

        add(WmcBlocks.ASSEMBLY_TABLE.get(), "Assembly Table");


        add("item.wmc.smithing_template.terminus_upgrade.applies_to", "Netherite Equipment");
        add("item.wmc.smithing_template.terminus_upgrade.ingredients", "Terminus Ingot");
        add("upgrade.wmc.terminus_upgrade", "Terminus Upgrade");
        add("item.wmc.smithing_template.terminus_upgrade.base_slot_description", "Add netherite armor, weapon, or tool");
        add("item.wmc.smithing_template.terminus_upgrade.additions_slot_description", "Add Terminus Ingot");

        add("item.wmc.blueprint.description.progress", "Progress: ");
        add("item.wmc.blueprint.description.result", "Result: ");
        add("item.wmc.blueprint.description.next_step", "Next Step: ");

        add("item.wmc.general.unknown_data_components", "Unknown Or Broken Data Components");
        add("item.wmc.general.unknown_item", "Unknown Item");
        add("item.wmc.general.activated", "Activated");
        add("item.wmc.general.unactivated", "Unactivated");

//        add(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "smithing_template.terminus_upgrade.applies_to")), "Netherite Equipment");
//        add(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "smithing_template.terminus_upgrade.ingredients")), "Terminus Ingot");
//        add(Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "terminus_upgrade")), "Terminus Upgrade");
//        add(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "smithing_template.terminus_upgrade.base_slot_description")), "Add netherite armor, weapon, or tool");
//        add(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "smithing_template.terminus_upgrade.additions_slot_description")), "Add Terminus Ingot");
//
//        add(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "blueprint.description.progress")), "Progress: ");
//        add(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "blueprint.description.result")), "Result: ");
//        add(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "blueprint.description.next_step")), "Next Step: ");
//
//        add(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "general.unknown_data_components")), "Unknown Or Broken Data Components");
//        add(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "general.unknown_item")), "Unknown Item");
//        add(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "general.activated")), "Activated");
//        add(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "general.unactivated")), "Unactivated");
    }
}
