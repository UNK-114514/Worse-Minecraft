package com.unk.wmc.block;

import com.unk.wmc.Wmc;
import com.unk.wmc.block.custom.ActivateAltarBlock;
import com.unk.wmc.block.custom.ActivatePedestalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.unk.wmc.block.custom.SmithingTemplateCraftingTableBlock;
import com.unk.wmc.item.WmcItems;

import java.util.function.Supplier;

public class WmcBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.Blocks.createBlocks(Wmc.MOD_ID);

    public static final DeferredBlock<Block> SMITHING_TEMPLATE_CRAFTING_TABLE = registerBlock("smithing_template_crafting_table", () -> new SmithingTemplateCraftingTableBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> ACTIVATE_ALTAR = registerBlock("activate_altar", () -> new ActivateAltarBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> ACTIVATE_PEDESTAL = registerBlock("activate_pedestal", () -> new ActivatePedestalBlock(BlockBehaviour.Properties.of()));

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        WmcItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> blockSupplier) {
        DeferredBlock<T> block = BLOCKS.register(name, blockSupplier);
        registerBlockItem(name, block);

        return block;
    }
}
