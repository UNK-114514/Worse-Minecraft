package com.unk.wmc.block.custom;

import com.unk.wmc.block.entity.custom.SimpleDisplayBlockEntity;
import com.unk.wmc.helper.ItemStackHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class SimpleDisplayBlock extends Block implements EntityBlock {
    public SimpleDisplayBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(
            @NotNull ItemStack stackInHand, @NotNull BlockState state, Level level, @NotNull BlockPos pos,
            @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) return ItemInteractionResult.SUCCESS;

        if (!(level.getBlockEntity(pos) instanceof SimpleDisplayBlockEntity be)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (player.isCrouching()) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        ItemStack stackInSlot = be.inventory.getStackInSlot(0);

        if (stackInHand.isEmpty() && stackInSlot.isEmpty()) return ItemInteractionResult.SUCCESS;

        if (!stackInHand.isEmpty() && stackInSlot.isEmpty()) {  // Insert item
            ItemStack toSlot0 = stackInHand.copy();
            toSlot0.setCount(1);

            stackInHand.consume(1, player);
            be.inventory.setStackInSlot(0, toSlot0);
        } else if (ItemStack.isSameItemSameComponents(stackInHand, stackInSlot) // Merge item
                && stackInHand.getCount() + stackInSlot.getCount() <= stackInHand.getMaxStackSize()) {
            if (!player.hasInfiniteMaterials()) {
                player.setItemInHand(hand, ItemStackHelper.mergeStackForcibly(stackInHand, stackInSlot));
            }

            be.inventory.setStackInSlot(0, ItemStack.EMPTY);
        } else {    // Swap item
            be.inventory.setStackInSlot(0, ItemStackHelper.setStackCount(stackInHand, 1));
            if (!player.hasInfiniteMaterials()){
                ItemStackHelper.givePlayerStack(player, ItemStackHelper.setStackCount(stackInSlot, 1));
            }

            stackInHand.consume(1, player);
        }

        return ItemInteractionResult.SUCCESS;
    }

    @Override
    protected void onRemove(
            BlockState state, @NotNull Level level, @NotNull BlockPos pos,
            BlockState newState, boolean isMovedByPiston) {
        if (state.getBlock() != newState.getBlock() && !isMovedByPiston) {
            if (level.getBlockEntity(pos) instanceof SimpleDisplayBlockEntity be) {
                SimpleContainer inv = new SimpleContainer(be.inventory.getSlots());

                inv.addItem(be.inventory.getStackInSlot(0));

                Containers.dropContents(level, pos, inv);
            }
        }

        super.onRemove(state, level, pos, newState, isMovedByPiston);
    }

    @Override
    public abstract @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState);
}
