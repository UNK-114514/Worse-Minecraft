package com.unk.wmc.item.custom;

import com.unk.wmc.component.ActivatableData;
import com.unk.wmc.component.SimpleItemStackData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.helper.ComponentHelper;
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
    public void appendHoverText(ItemStack stack, @NotNull TooltipContext tooltipContext, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        ActivatableData activatableData = stack.get(WmcDataComponentTypes.ACTIVATABLE);
        SimpleItemStackData simpleItemStackData = stack.get(WmcDataComponentTypes.SIMPLE_ITEM_STACK);

        if (activatableData == null || simpleItemStackData == null) return;

        ItemStack containedStack = simpleItemStackData.stack();

        if (!(containedStack.getItem() instanceof SmithingTemplateItem template)) {
            ComponentHelper.addTooltip(containedStack, tooltipContext, components, tooltipFlag);
            return;
        }

        components.add(template.upgradeDescription);
        components.add(activatableData.isActivated() ? ComponentHelper.ACTIVATED : ComponentHelper.UNACTIVATED);
    }
}
