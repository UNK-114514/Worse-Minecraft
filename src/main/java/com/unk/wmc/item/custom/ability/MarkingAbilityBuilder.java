package com.unk.wmc.item.custom.ability;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record MarkingAbilityBuilder(@NotNull Component name, @Nullable Component description) {
    public static MarkingAbilityBuilder of(Component name, Component description) {
        return new MarkingAbilityBuilder(name, description);
    }

    public IItemAbility build() {
        return new SimpleAbility() {
            @Override
            public void applyEffects(ItemStack stack, Player player) {
                super.applyEffects(stack, player);
            }

            @Override
            public boolean canTriggerByHotKey() {
                return false;
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
