package com.unk.wmc.loot.glm;

import com.mojang.serialization.MapCodec;
import com.unk.wmc.Wmc;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class WmcGlobalLootModifierSerializers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(
            NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
            Wmc.MOD_ID
    );

    public static final Supplier<MapCodec<SmithingTemplateLootModifier>> SIMPLE_REPLACING_MODIFIER =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("simple_replacing_modifier", () -> SmithingTemplateLootModifier.CODEC);
}
