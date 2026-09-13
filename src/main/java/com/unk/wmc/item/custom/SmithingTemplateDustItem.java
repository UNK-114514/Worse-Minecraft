package com.unk.wmc.item.custom;

import com.unk.wmc.component.ActivatableData;
import com.unk.wmc.component.SimpleItemData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.helper.ComponentHelper;
import com.unk.wmc.helper.ItemStackHelper;
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
        SimpleItemData simpleItemData = stack.get(WmcDataComponentTypes.SIMPLE_ITEM);

        if (activatableData == null || simpleItemData == null) return;

//        if (!BuiltInRegistries.ITEM.containsKey(simpleItemData.itemId())) {
//            components.add(ComponentHelper.UNKNOWN_DATA_COMPONENTS);
//            return;
//        }
//
//        Item item = BuiltInRegistries.ITEM.get(simpleItemData.itemId());
//        ComponentHelper.addItemTooltip(item.getDefaultInstance(), tooltipContext, components, tooltipFlag);

        Item item = ItemStackHelper.fromResourceLocation(simpleItemData.itemId());

        if (item == null) return;
        if (!(item instanceof SmithingTemplateItem template)) {
            ComponentHelper.addItemTooltip(item.getDefaultInstance(), tooltipContext, components, tooltipFlag);
            return;
        }

        components.add(template.upgradeDescription);
        components.add(activatableData.isActivated() ? ComponentHelper.ACTIVATED : ComponentHelper.UNACTIVATED);
    }
}
