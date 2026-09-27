package com.unk.wmc.registry;

import com.unk.wmc.Wmc;
import com.unk.wmc.item.custom.ability.IItemAbility;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class WmcRegistries {
    public static final ResourceKey<Registry<IItemAbility>> ITEM_ABILITY_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, "item_abilities"));
    public static final Registry<IItemAbility> ITEM_ABILITIES = new RegistryBuilder<>(ITEM_ABILITY_REGISTRY_KEY).create();
}
