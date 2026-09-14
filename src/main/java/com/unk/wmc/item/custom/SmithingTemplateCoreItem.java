package com.unk.wmc.item.custom;

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

public class SmithingTemplateCoreItem extends Item {
    public SmithingTemplateCoreItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @NotNull TooltipContext tooltipContext, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        SimpleItemData simpleItemData = stack.get(WmcDataComponentTypes.SIMPLE_ITEM);

        if (simpleItemData == null) return;

        Item item = ItemStackHelper.fromResourceLocation(simpleItemData.itemId());

        if (item == null) return;
        if (!(item instanceof SmithingTemplateItem template)) {
            ComponentHelper.addItemTooltip(item.getDefaultInstance(), tooltipContext, components, tooltipFlag);
            return;
        }

        components.add(template.upgradeDescription);
    }
}
