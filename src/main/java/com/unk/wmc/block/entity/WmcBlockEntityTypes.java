package com.unk.wmc.block.entity;

import com.unk.wmc.Wmc;
import com.unk.wmc.block.WmcBlocks;
import com.unk.wmc.block.entity.custom.ActivateAltarBlockEntity;
import com.unk.wmc.block.entity.custom.ActivatePedestalBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@SuppressWarnings("DataFlowIssue")
public class WmcBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Wmc.MOD_ID);

    public static final Supplier<BlockEntityType<ActivateAltarBlockEntity>> ACTIVATE_ALTAR_BE =
            BLOCK_ENTITY_TYPES.register(
                    "activate_altar_be",
                    () -> BlockEntityType.Builder.of(
                            ActivateAltarBlockEntity::new,
                            WmcBlocks.ACTIVATE_ALTAR.get()
                    ).build(null)
            );

    public static final Supplier<BlockEntityType<ActivatePedestalBlockEntity>> ACTIVATE_PEDESTAL_BE =
            BLOCK_ENTITY_TYPES.register(
                    "activate_pedestal_be",
                    () -> BlockEntityType.Builder.of(
                            ActivatePedestalBlockEntity::new,
                            WmcBlocks.ACTIVATE_PEDESTAL.get()
                    ).build(null)
            );
}
