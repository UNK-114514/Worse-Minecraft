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
        if (this.level == null) return AltarCraftingRecipeInput.EMPTY;

        BlockPos pos = this.getBlockPos();

        BlockPos n = pos.north(3);
        BlockPos en = pos.east(2).north(2);
        BlockPos e = pos.east(3);
        BlockPos es = pos.east(2).south(2);
        BlockPos s = pos.south(3);
        BlockPos ws = pos.west(2).south(2);
        BlockPos w = pos.west(3);
        BlockPos wn = pos.west(2).north(2);

        return new AltarCraftingRecipeInput(
                getItemInPedestal(this.level, n),
                getItemInPedestal(this.level, en),
                getItemInPedestal(this.level, e),
                getItemInPedestal(this.level, es),
                getItemInPedestal(this.level, s),
                getItemInPedestal(this.level, ws),
                getItemInPedestal(this.level, w),
                getItemInPedestal(this.level, wn),
                this.inventory.getStackInSlot(0)
        );
    }

    public void shrinkAll() {
        if (this.level == null) return;

        BlockPos pos = this.getBlockPos();

        BlockPos n = pos.north(3);
        BlockPos en = pos.east(2).north(2);
        BlockPos e = pos.east(3);
        BlockPos es = pos.east(2).south(2);
        BlockPos s = pos.south(3);
        BlockPos ws = pos.west(2).south(2);
        BlockPos w = pos.west(3);
        BlockPos wn = pos.west(2).north(2);

        shrinkPedestal(this.level, n);
        shrinkPedestal(this.level, en);
        shrinkPedestal(this.level, e);
        shrinkPedestal(this.level, es);
        shrinkPedestal(this.level, s);
        shrinkPedestal(this.level, ws);
        shrinkPedestal(this.level, w);
        shrinkPedestal(this.level, wn);
    }

    private ItemStack getItemInPedestal(Level level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof ActivatePedestalBlockEntity be) {
            return be.inventory.getStackInSlot(0);
        }
        return ItemStack.EMPTY;
    }

    private void shrinkPedestal(Level level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof ActivatePedestalBlockEntity be) {
            ItemStack result = be.inventory.getStackInSlot(0).copy();
            result.shrink(1);

            be.inventory.setStackInSlot(0, result);
        }
    }
}
