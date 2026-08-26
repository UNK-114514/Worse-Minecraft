package com.unk.wmc.item.custom;

import com.unk.wmc.component.ActivatableData;
import com.unk.wmc.component.WmcDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SmithingTemplateDustItem extends Item {
    public SmithingTemplateDustItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @NotNull TooltipContext context, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        ActivatableData activatableData = itemStack.get(WmcDataComponents.ACTIVATABLE);

        Component activatedTip = Component.literal("Activated").withStyle(ChatFormatting.GREEN);
        Component unactivatedTip = Component.literal("Unactivated").withStyle(ChatFormatting.RED);

        Component unknown = Component.literal("Unknown Item").withStyle(ChatFormatting.RED);

        if (activatableData != null) {
            if (!BuiltInRegistries.ITEM.containsKey(activatableData.itemId())) {
                components.add(unknown);
            }

            Item item = BuiltInRegistries.ITEM.get(activatableData.itemId());

            Component itemName = item.getName(item.getDefaultInstance()).copy();
            Component isActivated = activatableData.isActivated() ? activatedTip : unactivatedTip;

            components.add(itemName.copy().withStyle(ChatFormatting.GRAY));

            if (item instanceof SmithingTemplateItem smithingTemplate) {
                Component templateName = smithingTemplate.upgradeDescription.copy()
                        .withStyle(ChatFormatting.RESET);
                components.add(Component.literal(" ").append(templateName).withStyle(ChatFormatting.BLUE));
            }

            components.add(isActivated);
        }
    }
}
