package com.unk.wmc.item.custom.ability;

import com.unk.wmc.helper.EntityHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record RangeKillAbilityBuilder(
        int range, int cooldown, Component name,
        Component description, Class<? extends Entity> entityClazz) {
    public static RangeKillAbilityBuilder of(
            int range, int cooldown, Component name,
            Component description, Class<? extends Entity> entityClazz) {
        return new RangeKillAbilityBuilder(range, cooldown, name, description, entityClazz);
    }

    public IItemAbility build() {
        return new SimpleAbility() {
            @Override
            public void applyEffects(ItemStack stack, Player player) {
                super.applyEffects(stack, player);

                Level level = player.level();

                List<? extends Entity> targets = level.getEntitiesOfClass(
                        entityClazz,
                        player.getBoundingBox().inflate(range),
                        e -> e != player
                );

                EntityHelper.killAll(targets, player);
                if (cooldown >= 0) {
                    player.getCooldowns().addCooldown(stack.getItem(), cooldown);
                }
            }

            @Override
            public boolean canTriggerByHotKey() {
                return true;
            }

            @Override
            public @NotNull Component getAbilityName() {
                return name;
            }

            @Override
            public @Nullable Component getAbilityDescription() {
                return description;
            }
        };
    }
}
