package com.unk.wmc.block;

import com.unk.wmc.Wmc;
import com.unk.wmc.block.custom.ActivateAltarBlock;
import com.unk.wmc.block.custom.ActivatePedestalBlock;
import com.unk.wmc.block.custom.AssemblyTableBlock;
import com.unk.wmc.item.WmcItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class WmcBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.Blocks.createBlocks(Wmc.MOD_ID);

    public static final DeferredBlock<Block> TERMINUS_BLOCK = registerBlock("terminus_block", () -> new Block(BlockBehaviour.Properties.of().strength(100.0F, 2400.0F)));

    public static final DeferredBlock<Block> ACTIVATE_ALTAR = registerBlock("activate_altar", () -> new ActivateAltarBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final DeferredBlock<Block> ACTIVATE_PEDESTAL = registerBlock("activate_pedestal", () -> new ActivatePedestalBlock(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<Block> ASSEMBLY_TABLE = registerBlock("assembly_table", () -> new AssemblyTableBlock(BlockBehaviour.Properties.of()));

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        WmcItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> blockSupplier) {
        DeferredBlock<T> block = BLOCKS.register(name, blockSupplier);
        registerBlockItem(name, block);

        return block;
    }
}
