package com.unk.wmc.datagen;

import com.unk.wmc.datagen.provider.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import com.unk.wmc.Wmc;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Wmc.MOD_ID)
public class WmcDataGenerator {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new WmcItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new WmcBlockStateProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeClient(), new WmcZhCnProvider(packOutput));
        generator.addProvider(event.includeClient(), new WmcEnUsProvider(packOutput));

        BlockTagsProvider blockTagsProvider = new WmcBlockTagProvider(packOutput, lookupProvider, existingFileHelper);

        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new WmcItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new WmcRecipeProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(), new WmcGlobalLootModifierProvider(packOutput, lookupProvider));
    }
}
