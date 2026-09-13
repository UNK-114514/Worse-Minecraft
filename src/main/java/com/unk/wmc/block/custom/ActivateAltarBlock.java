package com.unk.wmc.block.custom;

import com.unk.wmc.block.entity.custom.ActivateAltarBlockEntity;
import com.unk.wmc.item.crafting.WmcRecipeTypes;
import com.unk.wmc.item.crafting.custom.AltarCraftingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
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

        be.shrinkAll();

        be.inventory.setStackInSlot(0, recipe.result());

        applyItemCraftedEffects(level, pos);
    }

    public void applyItemCraftedEffects(Level level, BlockPos pos) {
        level.explode(
                null,
                null,
                new SimpleExplosionDamageCalculator(
                        false,
                        false,
                        Optional.of(0F),
                        Optional.empty()
                ),
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                15,
                false,
                Level.ExplosionInteraction.TRIGGER);

        level.playSound(null, pos, SoundEvents.TOTEM_USE, SoundSource.BLOCKS);

        if (!(level instanceof ServerLevel serverLevel)) return;

        for (int i = 0; i < 500; i++) {
            serverLevel.sendParticles(
                    ParticleTypes.TOTEM_OF_UNDYING,
                    pos.getX() + 0.5,
                    pos.getY() + 1,
                    pos.getZ() + 0.5,
                    1,
                    level.random.nextFloat() * 2 - 1,
                    level.random.nextFloat() * 2 - 1,
                    level.random.nextFloat() * 2 - 1,
                    1
            );
        }
    }

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
