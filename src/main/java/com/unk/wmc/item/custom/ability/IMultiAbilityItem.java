package com.unk.wmc.item.custom.ability;

import com.unk.wmc.component.WmcDataComponentTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IMultiAbilityItem {
    List<IItemAbility> getAbilities();

    void initializeAbilities();

    default void addAbility(IItemAbility ability) {
        getAbilities().add(ability);
    }

    default int getSelectedIndex(ItemStack stack) {
        return stack.getOrDefault(WmcDataComponentTypes.ABILITY, 0);
    }

    default boolean switchAbility(ItemStack stack, int index, Player player) {
        if (index < 0 || index >= getAbilities().size()) {
            player.sendSystemMessage(Component.literal("Debug/Selected: ").append(getSelectedName(stack)));
            return false;
        }

        stack.set(WmcDataComponentTypes.ABILITY, index);
        player.sendSystemMessage(Component.literal("Debug/Selected: ").append(getSelectedName(stack)));

        return true;
    }

    default boolean selectNext(ItemStack stack, int offset, Player player) {
        int selected = getSelectedIndex(stack);

        if (selected + offset >= getAbilities().size()) {
            return switchAbility(stack, 0, player);
        } else if (selected + offset < 0) {
            return switchAbility(stack, getAbilities().size() - 1, player);
        } else {
            return switchAbility(stack, selected + offset, player);
        }
    }

    default @Nullable IItemAbility getSelectedAbility(ItemStack stack) {
        int idx = getSelectedIndex(stack);
        return idx < getAbilities().size() ? getAbilities().get(idx) : null;
    }

    default @Nullable Component getSelectedDescription(ItemStack stack) {
        IItemAbility ability = getSelectedAbility(stack);
        if (ability != null) {
            return ability.getAbilityDescription();
        }
        return null;
    }

    default @NotNull Component getSelectedName(ItemStack stack) {
        IItemAbility ability = getSelectedAbility(stack);
        if (ability != null) {
            return ability.getAbilityName();
        }
        return Component.literal("Null");
    }
}
