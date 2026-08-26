package com.unk.wmc.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.unk.wmc.block.entity.custom.SimpleDisplayBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.NotNull;

public class SimpleDisplayBlockEntityRenderer implements BlockEntityRenderer<SimpleDisplayBlockEntity> {
    private final ItemRenderer itemRenderer;

    public SimpleDisplayBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(
            SimpleDisplayBlockEntity be, float v, @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource multiBufferSource, int i, int i1) {
        Level level = be.getLevel();
        if (level == null) return;

        float time = (float) level.getGameTime() + v;

        float displayDY = 0.1f * (float) Math.sin(time * 0.1);

        float rotation = time * 0.05F;

        poseStack.pushPose();
        poseStack.translate(0.5f, displayDY + be.baseModelHeight, 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotation(rotation));

        BakedModel model = itemRenderer.getModel(be.inventory.getStackInSlot(0), level, null, 0);
        this.itemRenderer.render(
                be.inventory.getStackInSlot(0),
                ItemDisplayContext.NONE,
                false,
                poseStack,
                multiBufferSource,
                getLightLevel(level, be.getBlockPos()),
                OverlayTexture.NO_OVERLAY,
                model
        );
        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
