package com.unk.wmc.block.entity.custom;

import com.unk.wmc.block.entity.WmcBlockEntityTypes;
import com.unk.wmc.item.crafting.input.AltarCraftingRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ActivateAltarBlockEntity extends SimpleDisplayBlockEntity {
    public ActivateAltarBlockEntity(BlockPos pos, BlockState blockState) {
        super(WmcBlockEntityTypes.ACTIVATE_ALTAR_BE.get(), pos, blockState, 1.0F);
    }

    public AltarCraftingRecipeInput getInput() {
        if (level == null) return AltarCraftingRecipeInput.EMPTY;

        BlockPos pos = this.getBlockPos();

        return new AltarCraftingRecipeInput(
                getItemInPedestal(level, pos.north(3)),
                getItemInPedestal(level, pos.east(2).north(2)),
                getItemInPedestal(level, pos.east(3)),
                getItemInPedestal(level, pos.east(2).south(2)),
                getItemInPedestal(level, pos.south(3)),
                getItemInPedestal(level, pos.west(2).south(2)),
                getItemInPedestal(level, pos.west(3)),
                getItemInPedestal(level, pos.west(2).north(2)),
                inventory.getStackInSlot(0)
        );
    }

    public void shrinkAll() {
        if (level == null) return;

        BlockPos pos = this.getBlockPos();

        shrinkPedestal(level, pos.north(3));
        shrinkPedestal(level, pos.east(2).north(2));
        shrinkPedestal(level, pos.east(3));
        shrinkPedestal(level, pos.east(2).south(2));
        shrinkPedestal(level, pos.south(3));
        shrinkPedestal(level, pos.west(2).south(2));
        shrinkPedestal(level, pos.west(3));
        shrinkPedestal(level, pos.west(2).north(2));

        inventory.getStackInSlot(0).shrink(1);
        this.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    private ItemStack getItemInPedestal(Level level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof ActivatePedestalBlockEntity be) {
            return be.inventory.getStackInSlot(0);
        }
        return ItemStack.EMPTY;
    }

    private static void shrinkPedestal(Level level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof ActivatePedestalBlockEntity be) {
            be.inventory.getStackInSlot(0).shrink(1);
            be.setChanged();
            level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
        }
    }
}
