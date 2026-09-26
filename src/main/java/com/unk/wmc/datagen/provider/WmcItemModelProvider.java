package com.unk.wmc.datagen.provider;

import com.unk.wmc.Wmc;
import com.unk.wmc.block.WmcBlocks;
import com.unk.wmc.item.WmcItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class WmcItemModelProvider extends ItemModelProvider {
    public WmcItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Wmc.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(WmcBlocks.ACTIVATE_ALTAR.get());
        simpleBlockItem(WmcBlocks.ACTIVATE_PEDESTAL.get());

        basicItem(WmcItems.BLUEPRINT.get());
        basicItem(WmcItems.RANDOM_BLUEPRINT.get());

        basicItem(WmcItems.TERMINUS_INGOT.get());
        handheldItem(WmcItems.TERMINUS_SWORD.get());
        handheldItem(WmcItems.TERMINUS_PICKAXE.get());
        handheldItem(WmcItems.TERMINUS_AXE.get());
        handheldItem(WmcItems.TERMINUS_SHOVEL.get());
        handheldItem(WmcItems.TERMINUS_HOE.get());

        basicItem(WmcItems.COPPER_COGWHEEL.get());
        basicItem(WmcItems.IRON_COGWHEEL.get());
        basicItem(WmcItems.GOLD_COGWHEEL.get());
        basicItem(WmcItems.DIAMOND_COGWHEEL.get());
        basicItem(WmcItems.NETHERITE_COGWHEEL.get());
    }
}
