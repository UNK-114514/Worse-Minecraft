package com.unk.wmc.block.entity.custom;

import com.unk.wmc.block.entity.WmcBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ActivatePedestalBlockEntity extends SimpleDisplayBlockEntity {
    public ActivatePedestalBlockEntity(BlockPos pos, BlockState blockState) {
        super(WmcBlockEntityTypes.ACTIVATE_PEDESTAL_BE.get(), pos, blockState, 1.75F);
    }
}
