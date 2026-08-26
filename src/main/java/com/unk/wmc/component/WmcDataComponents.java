package com.unk.wmc.component;

import com.unk.wmc.Wmc;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class WmcDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Wmc.MOD_ID);

    public static final Supplier<DataComponentType<ActivatableData>> ACTIVATABLE =
            DATA_COMPONENT_TYPES.registerComponentType("activatable", builder -> builder
                    .persistent(ActivatableData.CODEC)
                    .networkSynchronized(ByteBufCodecs.fromCodec(ActivatableData.CODEC))
            );
}
