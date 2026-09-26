package com.unk.wmc.item.custom.ability;

import com.unk.wmc.helper.ComponentHelper;
import com.unk.wmc.helper.SoundHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class SimpleAbility implements IItemAbility {
    public static final SimpleAbility EMPTY = new SimpleAbility() {
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
    };

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
}
