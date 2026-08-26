package com.unk.wmc.block.custom;

import com.unk.wmc.block.entity.custom.ActivateAltarBlockEntity;
import com.unk.wmc.item.crafting.WmcRecipeTypes;
import com.unk.wmc.item.crafting.custom.AltarCraftingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ActivateAltarBlock extends SimpleDisplayBlock implements EntityBlock {
    private static final VoxelShape VOXEL_SHAPE = Block.box(0, 0, 0, 16, 10, 16);

    public ActivateAltarBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull VoxelShape getShape(
            @NotNull BlockState state, @NotNull BlockGetter level,
            @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return VOXEL_SHAPE;
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new ActivateAltarBlockEntity(blockPos, blockState);
    }

    private void onCraft(Level level, BlockPos pos) {
        if (!(level.getBlockEntity(pos) instanceof ActivateAltarBlockEntity be)) return;

        Optional<RecipeHolder<AltarCraftingRecipe>> optional = level.getRecipeManager().getRecipeFor(
                WmcRecipeTypes.ALTAR_RECIPE_TYPE.get(),
                be.getInput(),
                level
        );

        if (optional.isEmpty()) return;

        AltarCraftingRecipe recipe = optional.get().value();

        be.inventory.setStackInSlot(0, recipe.result());

        be.shrinkAll();
    }

//    @Override
//    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
//        if (entity.getType() == EntityType.EXPERIENCE_BOTTLE) {
//            onCraft(level, pos);
//        }
//    }


//    @Override
//    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
//        if (player.isCrouching()) {
//            onCraft(level, pos);
//        }
//        Wmc.LOGGER.info("used");
//        return InteractionResult.SUCCESS;
//    }

    @Override
    public void onProjectileHit(
            Level level, @NotNull BlockState state,
            @NotNull BlockHitResult hitResult, @NotNull Projectile projectile) {
        if (level.isClientSide) return;
        if (!(projectile instanceof ThrownExperienceBottle)) return;

        BlockPos pos = hitResult.getBlockPos();

        onCraft(level, pos);
    }
}
