package com.unk.wmc.datagen.provider;

import com.unk.wmc.Wmc;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class WmcBlockStateProvider extends BlockStateProvider {
    public WmcBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Wmc.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }
}
