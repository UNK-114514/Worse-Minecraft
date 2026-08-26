package com.unk.wmc;

import com.unk.wmc.block.entity.WmcBlockEntityTypes;
import com.unk.wmc.block.entity.renderer.SimpleDisplayBlockEntityRenderer;
import com.unk.wmc.menu.WmcMenuTypes;
import com.unk.wmc.screen.custom.SmithingTemplateCraftingScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = Wmc.MOD_ID, value = Dist.CLIENT)
public class WmcClient {
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(
                WmcMenuTypes.SMITHING_TEMPLATE_CRAFTING_MENU_TYPE.get(),
                SmithingTemplateCraftingScreen::new
        );
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                WmcBlockEntityTypes.ACTIVATE_ALTAR_BE.get(),
                SimpleDisplayBlockEntityRenderer::new
        );

        event.registerBlockEntityRenderer(
                WmcBlockEntityTypes.ACTIVATE_PEDESTAL_BE.get(),
                SimpleDisplayBlockEntityRenderer::new
        );
    }
}
