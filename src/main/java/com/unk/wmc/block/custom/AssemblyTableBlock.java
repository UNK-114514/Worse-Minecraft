package com.unk.wmc.block.custom;

import com.unk.wmc.menu.AssemblyTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class AssemblyTableBlock extends CraftingTableBlock {
    public AssemblyTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return new SimpleMenuProvider((containerId, playerInventory, player) ->
                new AssemblyTableMenu(containerId, playerInventory, ContainerLevelAccess.create(level, pos)),
                Component.literal("Assembly Table")
        );
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(
            @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
            @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        player.openMenu(state.getMenuProvider(level, pos));
        return InteractionResult.CONSUME;
    }
}
