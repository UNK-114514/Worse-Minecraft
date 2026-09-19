package com.unk.wmc.network;

import com.unk.wmc.Wmc;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Wmc.MOD_ID)
public class WmcPayloads {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(C2SAbilitySwitchingPayload.TYPE, C2SAbilitySwitchingPayload.STREAM_CODEC, C2SAbilitySwitchingPayload::handle);
    }
}
