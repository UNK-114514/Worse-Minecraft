package com.unk.wmc.block;

import com.unk.wmc.Wmc;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class WmcBlockTags {
    public static final TagKey<Block> EMPTY = create("empty");

    private static TagKey<Block> create(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Wmc.MOD_ID, path));
    }
}
