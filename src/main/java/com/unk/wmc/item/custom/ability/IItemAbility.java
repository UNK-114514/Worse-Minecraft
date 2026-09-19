package com.unk.wmc.item.custom.ability;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IItemAbility {
    default void trigger(ItemStack stack, Player player) {
        if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
            applyEffects(stack, player);
        }
    }

    void applyEffects(ItemStack stack, Player player);

    boolean canTriggerByHotKey();

    @NotNull Component getAbilityName();

    @Nullable Component getAbilityDescription();
}
