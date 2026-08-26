package com.unk.wmc.datagen.provider;

import com.unk.wmc.Wmc;
import com.unk.wmc.loot.glm.SmithingTemplateLootModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class WmcGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public WmcGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Wmc.MOD_ID);
    }

    @Override
    protected void start() {
        add("smithing_template_loot_modifier_instance", new SmithingTemplateLootModifier(
                new LootItemCondition[]{}
        ));
    }
}
