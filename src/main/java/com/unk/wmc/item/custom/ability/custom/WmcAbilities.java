package com.unk.wmc.item.custom.ability.custom;

import com.unk.wmc.Wmc;
import com.unk.wmc.helper.EntityHelper;
import com.unk.wmc.item.custom.ability.IItemAbility;
import com.unk.wmc.item.custom.ability.MarkingAbilityBuilder;
import com.unk.wmc.item.custom.ability.RangeKillAbilityBuilder;
import com.unk.wmc.item.custom.ability.SimpleAbility;
import com.unk.wmc.registry.WmcRegistries;
import com.unk.wmc.util.DelayedTickTask;
import com.unk.wmc.util.VeinMiner;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class WmcAbilities {
    public static final DeferredRegister<IItemAbility> ITEM_ABILITIES = DeferredRegister.create(WmcRegistries.ITEM_ABILITIES, Wmc.MOD_ID);

    public static final Supplier<IItemAbility> EMPTY =
            ITEM_ABILITIES.register("empty",
                    () -> new SimpleAbility() {
                        @Override
                        public void applyEffects(ItemStack stack, Player player) {

                        }

                        @Override
                        public boolean canTriggerByHotKey() {
                            return false;
                        }

                        @Override
                        public @NotNull Component getAbilityName() {
                            return Component.literal("Empty");
                        }

                        @Override
                        public @Nullable Component getAbilityDescription() {
                            return null;
                        }
                    });

    public static final Supplier<IItemAbility> RANGE_KILL_ALL_ENTITY =
            ITEM_ABILITIES.register("range_kill_all_entity",
                    () -> RangeKillAbilityBuilder.of(
                        128,
                        10,
                        Component.literal("Range Kill (All Entity)"),
                        Component.literal("Kill every entity in 128 blocks").withStyle(ChatFormatting.GRAY),
                        Entity.class
                    ).build()
            );

    public static final Supplier<IItemAbility> RANGE_KILL_ALL_LIVING =
            ITEM_ABILITIES.register("range_kill_all_living",
                    () -> RangeKillAbilityBuilder.of(
                            128,
                            10,
                            Component.literal("Range Kill (All Living)"),
                            Component.literal("Kill every living entity in 128 blocks").withStyle(ChatFormatting.GRAY),
                            LivingEntity.class
                    ).build()
            );

    public static final Supplier<IItemAbility> RANGE_KILL_ALL_MONSTER =
            ITEM_ABILITIES.register("range_kill_all_monster",
                    () -> RangeKillAbilityBuilder.of(
                            128,
                            10,
                            Component.literal("Range Kill (All Monster)"),
                            Component.literal("Kill every monster in 128 blocks").withStyle(ChatFormatting.GRAY),
                            Monster.class
                    ).build()
            );

    public static final Supplier<IItemAbility> RANGE_KILL_ALL_ITEM =
            ITEM_ABILITIES.register("range_kill_all_item",
                    () -> RangeKillAbilityBuilder.of(
                            128,
                            -1,
                            Component.literal("Range Kill (All Item)"),
                            Component.literal("Kill every item entity in 128 blocks").withStyle(ChatFormatting.GRAY),
                            ItemEntity.class
                    ).build()
            );

    public static final Supplier<IItemAbility> RANGE_KILL_ATTACKED_ENTITY =
            ITEM_ABILITIES.register("range_kill_attacked_entity",
                    () -> MarkingAbilityBuilder.of(
                            Component.literal("Range Kill (Attacked Entity)"),
                            Component.literal("Kill all the entity of the same type as the ones you attacked").withStyle(ChatFormatting.GRAY)
                    ).build()
            );

    public static final Supplier<IItemAbility> TREE_BREAKER =
            ITEM_ABILITIES.register("tree_breaker",
                    () -> MarkingAbilityBuilder.of(
                            Component.literal("Tree Breaker"),
                            Component.literal("Destroy All The Trees").withStyle(ChatFormatting.GRAY)
                    ).build()
            );

    public static final Supplier<IItemAbility> VEIN_MINER =
            ITEM_ABILITIES.register("vein_miner",
                    () -> MarkingAbilityBuilder.of(
                            Component.literal("Vein Miner"),
                            Component.literal("Max: 1024").withStyle(ChatFormatting.GRAY)
                    ).build()
            );

    public static final Supplier<IItemAbility> SUPER_SILK_TOUCH =
            ITEM_ABILITIES.register("super_silk_touch",
                    () -> MarkingAbilityBuilder.of(
                            Component.literal("Super Silk Touch"),
                            Component.literal("Every block will drop itself").withStyle(ChatFormatting.GRAY)
                    ).build()
            );
    public static final Supplier<IItemAbility> HARVEST =
            ITEM_ABILITIES.register("harvest",
                    () -> MarkingAbilityBuilder.of(
                            Component.literal("Harvest"),
                            Component.literal("When use on block: Auto plant & Super bone meal & Auto harvest").withStyle(ChatFormatting.GRAY)
                    ).build());

    public static final Supplier<IItemAbility> GRIM_REAPER =
            ITEM_ABILITIES.register("grim_reaper",
                    () -> new SimpleAbility() {
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
                            return Component.literal("Kill everything").withStyle(ChatFormatting.GRAY);
                        }
                    });

    public static final Supplier<IItemAbility> TERRAIN_CLEANER =
            ITEM_ABILITIES.register("terrain_cleaner",
                    () -> new SimpleAbility() {
                        @Override
                        public void applyEffects (ItemStack stack, Player player) {
                            super.applyEffects(stack, player);

                            List<BlockPos> targets = VeinMiner.veinMineBFS(
                                    player.level(),
                                    player.blockPosition(),
                                    16384,
                                    (target) -> target.getY() >= player.blockPosition().getY()
                            );
                            VeinMiner.mineAll(targets, player.level(), 128, player, 0);
                            SimpleAbility.applyUsedEffect(stack, player);
                            player.getCooldowns().addCooldown(stack.getItem(), 200);
                        }

                        @Override
                        public boolean canTriggerByHotKey() {
                            return true;
                        }

                        @Override
                        public @Nullable Component getAbilityDescription() {
                            return Component.literal("Destroy blocks that higher than you").withStyle(ChatFormatting.GRAY);
                        }

                        @Override
                        public @NotNull Component getAbilityName() {
                            return Component.literal("Terrain Cleaner");
                        }
                    });

    public static final Supplier<IItemAbility> GRAVITY_CHANGER =
            ITEM_ABILITIES.register("gravity_changer",
                    () -> new MarkingAbilityBuilder(
                            Component.literal("Gravity Changer"),
                            Component.literal("Change entity's gravity").withStyle(ChatFormatting.GRAY)
                    ).build()
            );
}
