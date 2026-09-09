package com.unk.wmc.component;

import com.unk.wmc.Wmc;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class WmcDataComponentTypes {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Wmc.MOD_ID);

    public static final Supplier<DataComponentType<ActivatableData>> ACTIVATABLE =
            DATA_COMPONENT_TYPES.registerComponentType("activatable", builder -> builder
                    .persistent(ActivatableData.CODEC)
                    .networkSynchronized(ActivatableData.STREAM_CODEC)
            );

    public static final Supplier<DataComponentType<BlueprintData>> BLUEPRINT =
            DATA_COMPONENT_TYPES.registerComponentType("blueprint", builder -> builder
                    .persistent(BlueprintData.CODEC)
                    .networkSynchronized(BlueprintData.STREAM_CODEC)
            );

    public static final Supplier<DataComponentType<SimpleItemData>> SIMPLE_ITEM =
            DATA_COMPONENT_TYPES.registerComponentType("simple_item", builder -> builder
                    .persistent(SimpleItemData.CODEC)
                    .networkSynchronized(SimpleItemData.STREAM_CODEC)
            );
}
