package com.unk.wmc.item.custom;

import com.unk.wmc.helper.EntityHelper;
import com.unk.wmc.helper.VeinMiningHelper;
import com.unk.wmc.item.custom.ability.IItemAbility;
import com.unk.wmc.item.custom.ability.IMultiAbilityItem;
import com.unk.wmc.item.custom.ability.MarkingAbilityBuilder;
import com.unk.wmc.item.custom.ability.SimpleAbility;
import com.unk.wmc.util.DelayedTickTask;
import com.unk.wmc.util.RainbowComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TerminusHoeItem extends HoeItem implements IMultiAbilityItem, TerminusItem {
    private static final String NAME = "Terminus Hoe";
    private static final List<IItemAbility> abilities = new ArrayList<>();

    public TerminusHoeItem(Tier tier, Item.Properties properties) {
        super(tier, properties);
        initializeAbilities();
    }

    @Override
    public List<IItemAbility> getAbilities() {
        return abilities;
    }

    @Override
    public void initializeAbilities() {
        addAbility(
                MarkingAbilityBuilder.of(
                        Component.literal("Harvest"),
                        Component.literal("Auto Plant & Super Bone Meal & Auto Harvest").withStyle(ChatFormatting.GRAY)
                ).build()
        );

        addAbility(
                new SimpleAbility() {
                    @Override
                    public void applyEffects(ItemStack stack, Player player) {
                        super.applyEffects(stack, player);

                        Level level = player.level();
                        ServerLevel serverLevel = (ServerLevel) level;

                        List<? extends LivingEntity> targets = level.getEntitiesOfClass(
                                LivingEntity.class,
                                player.getBoundingBox().inflate(128),
                                e -> e != player
                        );

                        targets.forEach((target) -> {
                            target.setPos(new Vec3(player.getX(), player.getY() + 3, player.getZ()));
                            target.setDeltaMovement(0, 2, 0);
                        });

                        DelayedTickTask.schedule(
                                serverLevel,
                                38,
                                () -> targets.forEach((target) -> serverLevel.sendParticles(
                                        ParticleTypes.SWEEP_ATTACK,
                                        target.getX(),
                                        target.getY(),
                                        target.getZ(),
                                        1,
                                        0,
                                        0,
                                        0,
                                        0
                                ))
                        );

                        DelayedTickTask.schedule(
                                serverLevel,
                                40,
                                () -> EntityHelper.killAll(targets, player)
                        );

                        player.getCooldowns().addCooldown(stack.getItem(), 200);
                        SimpleAbility.applyUsedEffect(stack, player);
                    }

                    @Override
                    public boolean canTriggerByHotKey() {
                        return true;
                    }

                    @Override
                    public @NotNull Component getAbilityName() {
                        return Component.literal("Grim Reaper");
                    }

                    @Override
                    public @Nullable Component getAbilityDescription() {
                        return Component.literal("Kill Everything");
                    }
                }
        );

        addAbility(SimpleAbility.EMPTY);
    }

    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity entity) {
        EntityHelper.kill(entity, player);
        return true;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        int index = getSelectedIndex(context.getItemInHand());

        if (index != 0) {
            return super.useOn(context);
        }

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (level.isClientSide) return InteractionResult.CONSUME;
        if (context.getPlayer() == null) return InteractionResult.CONSUME;

        boolean isUsed = false;
        boolean isSuccess = false;

        if (state.getBlock() instanceof FarmBlock) {
            isSuccess = VeinMiningHelper.plantSeeds(level, pos, context.getPlayer());
            isUsed = true;
        } else if (state.getBlock() instanceof CropBlock crop && crop.getAge(state) < crop.getMaxAge()) {
            isSuccess = VeinMiningHelper.boneMeal(level, pos);
            isUsed = true;
        } else if (state.getBlock() instanceof CropBlock crop && crop.getAge(state) == crop.getMaxAge()) {
            isSuccess = VeinMiningHelper.harvest(level, pos, context.getPlayer());
            isUsed = true;
        }

        if (isUsed) {
            SimpleAbility.applyUsedEffect(context.getItemInHand(), context.getPlayer());
            context.getPlayer().getCooldowns().addCooldown(context.getItemInHand().getItem(), 100);
        }

        return isSuccess ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return RainbowComponent.of(NAME);
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack stack, @NotNull Item.TooltipContext context,
            @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        addTooltip(stack, components);
    }
}
