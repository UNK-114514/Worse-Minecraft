package com.unk.wmc.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import com.unk.wmc.Wmc;

public class WmcItemTags {
    public static final TagKey<Item> THE_NETHER_SMITHING_CORES = create("the_nether_smithing_cores");
    public static final TagKey<Item> ILLAGER_SMITHING_CORES = create("illager_smithing_cores");
    public static final TagKey<Item> DESERT_SMITHING_CORES = create("desert_smithing_cores");
    public static final TagKey<Item> OCEAN_SMITHING_CORES = create("ocean_smithing_cores");
    public static final TagKey<Item> JUNGLE_SMITHING_CORES = create("jungle_smithing_cores");
    public static final TagKey<Item> SCULK_SMITHING_CORES = create("sculk_smithing_cores");
    public static final TagKey<Item> THE_END_SMITHING_CORES = create("the_end_smithing_cores");
    public static final TagKey<Item> RUINS_SMITHING_CORES = create("ruins_smithing_cores");
    public static final TagKey<Item> TRIAL_SMITHING_CORES = create("trial_smithing_cores");

    public static final TagKey<Item> SMITHING_CORES = create("smithing_cores");

    private static TagKey<Item> create(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, path));
    }
}
