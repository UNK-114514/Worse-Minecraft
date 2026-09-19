package com.unk.wmc.item.custom;

import com.unk.wmc.item.custom.ability.MultiAbilityItem;
import com.unk.wmc.item.custom.ability.RangeKillAbilityBuilder;
import com.unk.wmc.item.custom.ability.SimpleAbility;
import com.unk.wmc.util.RainbowComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TerminusSwordItem extends MultiAbilityItem {
    private static final String NAME = "Terminus Sword";

    public TerminusSwordItem(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeAbilities() {
        addAbility(
                RangeKillAbilityBuilder.of(
                        128,
                        100,
                        Component.literal("Range Kill (All Entity)"),
                        Component.literal("Kill every entity in 128 blocks").withStyle(ChatFormatting.GRAY),
                        Entity.class
                ).build()
        );

        addAbility(
                RangeKillAbilityBuilder.of(
                        128,
                        100,
                        Component.literal("Range Kill (All Living)"),
                        Component.literal("Kill every living entity in 128 blocks").withStyle(ChatFormatting.GRAY),
                        LivingEntity.class
                ).build()
        );

        addAbility(
                RangeKillAbilityBuilder.of(
                        128,
                        100,
                        Component.literal("Range Kill (All Monster)"),
                        Component.literal("Kill every monster in 128 blocks").withStyle(ChatFormatting.GRAY),
                        Monster.class
                ).build()
        );

        addAbility(
                RangeKillAbilityBuilder.of(
                        128,
                        100,
                        Component.literal("Range Kill (All Item)"),
                        Component.literal("Kill every item entity in 128 blocks").withStyle(ChatFormatting.GRAY),
                        ItemEntity.class
                )
                .build()
        );

        addAbility(SimpleAbility.EMPTY);
    }

    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity entity) {
        if (!(entity instanceof LivingEntity living)) return false;

        living.hurt(player.damageSources().genericKill(), Float.MAX_VALUE);

        if (living.isAlive()) {
            living.setHealth(0F);
            living.die(player.damageSources().genericKill());
        }
        return true;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return RainbowComponent.of(NAME, 0.75F, 1F, 0.01F);
    }
}
