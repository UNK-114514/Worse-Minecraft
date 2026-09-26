package com.unk.wmc.item.custom.tier;

import com.unk.wmc.block.WmcBlockTags;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.SimpleTier;

public class WmcTiers {
    public static final Tier TERMUNIUS_TIER = new SimpleTier(
            WmcBlockTags.EMPTY,
            Integer.MAX_VALUE,
            Float.POSITIVE_INFINITY,
            Float.POSITIVE_INFINITY,
            Integer.MAX_VALUE,
            () -> null
    );
}
