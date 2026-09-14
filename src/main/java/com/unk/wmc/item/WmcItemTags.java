package com.unk.wmc.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import com.unk.wmc.Wmc;

public class WmcItemTags {
    public static final TagKey<Item> SMITHING_TEMPLATES = create("smithing_templates");
    public static final TagKey<Item> TERMINUS_UPGRADE_SMITHING_TEMPLATE_REQUIRES = create("terminus_upgrade_smithing_template_requires");

    private static TagKey<Item> create(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, path));
    }
}
