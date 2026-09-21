package com.unk.wmc.item.custom.ability;

import com.unk.wmc.component.WmcDataComponentTypes;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Deprecated()
@SuppressWarnings("UnusedReturnValue")
public abstract class MultiAbilityItem extends Item {
    private final List<IItemAbility> abilities = new ArrayList<>();

    public MultiAbilityItem(Properties properties) {
        super(properties);
        initializeAbilities();
    }

    public abstract void initializeAbilities();

    public void addAbility(IItemAbility ability) {
        abilities.add(ability);
    }

    public List<IItemAbility> getAbilities() {
        return abilities;
    }

    public int getSelectedIndex(ItemStack stack) {
        return stack.getOrDefault(WmcDataComponentTypes.ABILITY, 0);
    }

    public boolean switchAbility(ItemStack stack, int index, Player player) {
        if (index < 0 || index >= abilities.size()) {
            player.sendSystemMessage(Component.literal("Debug/Selected: ").append(getSelectedName(stack)));
            return false;
        }

        stack.set(WmcDataComponentTypes.ABILITY, index);
        player.sendSystemMessage(Component.literal("Debug/Selected: ").append(getSelectedName(stack)));

        return true;
    }

    public boolean selectNext(ItemStack stack, int offset, Player player) {
        int selected = getSelectedIndex(stack);

        if (selected + offset >= abilities.size()) {
            return switchAbility(stack, 0, player);
        } else if (selected + offset < 0) {
            return switchAbility(stack, abilities.size() - 1, player);
        } else {
            return switchAbility(stack, selected + offset, player);
        }
    }

    public @Nullable IItemAbility getSelectedAbility(ItemStack stack) {
        int idx = getSelectedIndex(stack);
        return idx < abilities.size() ? abilities.get(idx) : null;
    }

    public @Nullable Component getSelectedDescription(ItemStack stack) {
        IItemAbility ability = getSelectedAbility(stack);
        if (ability != null) {
            return ability.getAbilityDescription();
        }
        return Component.empty();
    }

    public @NotNull Component getSelectedName(ItemStack stack) {
        IItemAbility ability = getSelectedAbility(stack);
        if (ability != null) {
            return ability.getAbilityName();
        }
        return Component.literal("Null");
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack stack, @NotNull TooltipContext context,
            @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        components.add(Component.literal("Selected Ability: ").append(getSelectedName(stack)));
        if (Screen.hasShiftDown()) {
            components.add(CommonComponents.EMPTY);
            components.add(getSelectedDescription(stack));
        }
    }
}
