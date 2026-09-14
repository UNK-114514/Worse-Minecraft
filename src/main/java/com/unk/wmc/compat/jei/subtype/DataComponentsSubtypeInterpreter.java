package com.unk.wmc.compat.jei.subtype;

import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataComponentsSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    private final List<DataComponentType<?>> types;

    public DataComponentsSubtypeInterpreter(DataComponentType<?>... types) {
        this.types = List.of(types);
    }

    @Override
    public @Nullable Object getSubtypeData(@NotNull ItemStack ingredient, @NotNull UidContext context) {
        List<Object> values = new ArrayList<>(types.size());
        boolean allNull = false;

        for (DataComponentType<?> type : types) {
            Object value = ingredient.get(type);
            if (value != null) {
                allNull = true;
            }
            values.add(value);
        }

        return allNull ? values : null;
    }

    @Override
    public @NotNull String getLegacyStringSubtypeInfo(@NotNull ItemStack ingredient, @NotNull UidContext context) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < types.size(); i++) {
            if (i > 0) builder.append('&');
            Object value = ingredient.get(types.get(i));
            builder.append(value == null ? "" : value);
        }
        return builder.toString();
    }
}
