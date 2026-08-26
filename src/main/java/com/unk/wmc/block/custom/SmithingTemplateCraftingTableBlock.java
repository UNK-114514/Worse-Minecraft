package com.unk.wmc.block.custom;

import com.mojang.serialization.MapCodec;
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
import com.unk.wmc.menu.custom.SmithingTemplateCraftingMenu;
import org.jetbrains.annotations.NotNull;

public class SmithingTemplateCraftingTableBlock extends CraftingTableBlock {
    private static final MapCodec<SmithingTemplateCraftingTableBlock> CODEC = simpleCodec(SmithingTemplateCraftingTableBlock::new);

    public @NotNull MapCodec<SmithingTemplateCraftingTableBlock> codec() {
        return CODEC;
    }

    public SmithingTemplateCraftingTableBlock(Properties properties) {
        super(properties);
    }

    protected @NotNull MenuProvider getMenuProvider(
            @NotNull BlockState state,
            @NotNull Level level, @NotNull BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new SmithingTemplateCraftingMenu(id, inventory, ContainerLevelAccess.create(level, pos)), Component.literal("TEST"));
    }

    protected @NotNull InteractionResult useWithoutItem(
            @NotNull BlockState state, Level level, @NotNull BlockPos pos,
            @NotNull Player player, @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(level, pos));
            return InteractionResult.CONSUME;
        }
    }
}
