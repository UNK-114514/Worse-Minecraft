package com.unk.wmc.compat.jei.subtype;

import com.unk.wmc.component.ActivatableData;
import com.unk.wmc.component.SimpleItemStackData;
import com.unk.wmc.component.WmcDataComponentTypes;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SmithingTemplateDustSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    public static final SmithingTemplateDustSubtypeInterpreter INSTANCE = new SmithingTemplateDustSubtypeInterpreter();

    public record SubtypeKey(SimpleItemStackData simpleItemStackData, ActivatableData activatableData) {}

    @Override
    public @Nullable Object getSubtypeData(ItemStack ingredient, @NotNull UidContext context) {
        SimpleItemStackData simpleItemStackData = ingredient.get(WmcDataComponentTypes.SIMPLE_ITEM_STACK);
        ActivatableData activatableData = ingredient.get(WmcDataComponentTypes.ACTIVATABLE);
        if (simpleItemStackData == null && activatableData == null) return null;
        return new SubtypeKey(simpleItemStackData, activatableData);
    }

    @Override
    public @NotNull String getLegacyStringSubtypeInfo(ItemStack ingredient, @NotNull UidContext context) {
        SimpleItemStackData simpleItemStackData = ingredient.get(WmcDataComponentTypes.SIMPLE_ITEM_STACK);
        ActivatableData activatableData = ingredient.get(WmcDataComponentTypes.ACTIVATABLE);
        return (simpleItemStackData == null ? "" : simpleItemStackData.toString()) + "|" +
                (activatableData == null ? "" : activatableData.toString());
    }
}
