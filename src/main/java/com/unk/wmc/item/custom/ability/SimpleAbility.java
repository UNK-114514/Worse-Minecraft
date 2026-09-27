package com.unk.wmc.item.custom.ability;

import com.unk.wmc.helper.ComponentHelper;
import com.unk.wmc.helper.SoundHelper;
import com.unk.wmc.registry.WmcRegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class SimpleAbility implements IItemAbility {
    @Override
    public void applyEffects(ItemStack stack, Player player) {
        applyUsedEffect(stack, player);
    }

    @Override
    public abstract boolean canTriggerByHotKey();

    @Override
    public abstract @Nullable Component getAbilityDescription();

    public static void applyUsedEffect(ItemStack stack, Player player) {
        if (!(stack.getItem() instanceof IMultiAbilityItem multi)) return;
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        SoundHelper.playSoundToPlayer(serverPlayer, SoundEvents.EXPERIENCE_ORB_PICKUP);

        ComponentHelper.showActionBarTitle(player, Component.literal("Triggered ")
                .withStyle(ChatFormatting.GREEN)
                .append(multi.getSelectedName(stack).copy().withStyle(ChatFormatting.GRAY)));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IItemAbility ability)) return false;

        var a = WmcRegistries.ITEM_ABILITIES.getKey(this);
        var b = WmcRegistries.ITEM_ABILITIES.getKey(ability);

        if (a == null || b == null) return false;
        return a.equals(b);
    }

    @Override
    public int hashCode() {
        var key = WmcRegistries.ITEM_ABILITIES.getKey(this);
        return key == null ? System.identityHashCode(this) : key.hashCode();
    }
}
