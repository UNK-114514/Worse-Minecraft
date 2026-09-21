package com.unk.wmc.item.custom.ability;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleMultiAbilityItem extends Item implements IMultiAbilityItem {
    private static final List<IItemAbility> abilities = new ArrayList<>();

    public SimpleMultiAbilityItem(Properties properties) {
        super(properties);
        initializeAbilities();
    }

    public List<IItemAbility> getAbilities() {
        return abilities;
    }

    public void addAbility(IItemAbility ability) {
        getAbilities().add(ability);
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack stack, @NotNull Item.TooltipContext context,
            @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        components.add(Component.literal("Selected Ability: ").append(getSelectedName(stack)));
        if (Screen.hasShiftDown()) {
            components.add(CommonComponents.EMPTY);
            components.add(getSelectedDescription(stack));
        }
    }
}
