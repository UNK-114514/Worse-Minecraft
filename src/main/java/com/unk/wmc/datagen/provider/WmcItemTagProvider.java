package com.unk.wmc.datagen.provider;

import com.unk.wmc.Wmc;
import com.unk.wmc.item.WmcItemTags;
import com.unk.wmc.item.WmcItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class WmcItemTagProvider extends ItemTagsProvider {
    public WmcItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, CompletableFuture<TagLookup<Block>> blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookup, blockTagProvider, Wmc.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
//        tag(WmcItemTags.THE_NETHER_SMITHING_CORES)
//                .add(WmcItems.NETHERITE_UPGRADE_SMITHING_CORE.get())
//                .add(WmcItems.SNOUT_ARMOR_TRIM_SMITHING_CORE.get())
//                .add(WmcItems.RIB_ARMOR_TRIM_SMITHING_CORE.get());
//
//        tag(WmcItemTags.ILLAGER_SMITHING_CORES)
//                .add(WmcItems.SENTRY_ARMOR_TRIM_SMITHING_CORE.get())
//                .add(WmcItems.VEX_ARMOR_TRIM_SMITHING_CORE.get());
//
//        tag(WmcItemTags.DESERT_SMITHING_CORES)
//                .add(WmcItems.DUNE_ARMOR_TRIM_SMITHING_CORE.get());
//
//        tag(WmcItemTags.OCEAN_SMITHING_CORES)
//                .add(WmcItems.COAST_ARMOR_TRIM_SMITHING_CORE.get())
//                .add(WmcItems.TIDE_ARMOR_TRIM_SMITHING_CORE.get());
//
//        tag(WmcItemTags.JUNGLE_SMITHING_CORES)
//                .add(WmcItems.WILD_ARMOR_TRIM_SMITHING_CORE.get());
//
//        tag(WmcItemTags.SCULK_SMITHING_CORES)
//                .add(WmcItems.WARD_ARMOR_TRIM_SMITHING_CORE.get())
//                .add(WmcItems.SILENCE_ARMOR_TRIM_SMITHING_CORE.get());
//
//        tag(WmcItemTags.THE_END_SMITHING_CORES)
//                .add(WmcItems.EYE_ARMOR_TRIM_SMITHING_CORE.get())
//                .add(WmcItems.SPIRE_ARMOR_TRIM_SMITHING_CORE.get());
//
//        tag(WmcItemTags.RUINS_SMITHING_CORES)
//                .add(WmcItems.WAYFINDER_ARMOR_TRIM_SMITHING_CORE.get())
//                .add(WmcItems.SHAPER_ARMOR_TRIM_SMITHING_CORE.get())
//                .add(WmcItems.RAISER_ARMOR_TRIM_SMITHING_CORE.get())
//                .add(WmcItems.HOST_ARMOR_TRIM_SMITHING_CORE.get());
//
//        tag(WmcItemTags.TRIAL_SMITHING_CORES)
//                .add(WmcItems.FLOW_ARMOR_TRIM_SMITHING_CORE.get())
//                .add(WmcItems.BOLT_ARMOR_TRIM_SMITHING_CORE.get());
//
//        tag(WmcItemTags.SMITHING_CORES)
//                .addTag(WmcItemTags.THE_NETHER_SMITHING_CORES)
//                .addTag(WmcItemTags.ILLAGER_SMITHING_CORES)
//                .addTag(WmcItemTags.DESERT_SMITHING_CORES)
//                .addTag(WmcItemTags.OCEAN_SMITHING_CORES)
//                .addTag(WmcItemTags.JUNGLE_SMITHING_CORES)
//                .addTag(WmcItemTags.SCULK_SMITHING_CORES)
//                .addTag(WmcItemTags.THE_END_SMITHING_CORES)
//                .addTag(WmcItemTags.RUINS_SMITHING_CORES)
//                .addTag(WmcItemTags.TRIAL_SMITHING_CORES);

        tag(WmcItemTags.TERMINUS_UPGRADE_SMITHING_TEMPLATE_REQUIRES)
                .add(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .addTag(ItemTags.TRIM_TEMPLATES);

        tag(WmcItemTags.SMITHING_TEMPLATES)
                .add(WmcItems.TERMINUS_UPGRADE_SMITHING_TEMPLATE.get())
                .addTag(WmcItemTags.TERMINUS_UPGRADE_SMITHING_TEMPLATE_REQUIRES);
    }
}
