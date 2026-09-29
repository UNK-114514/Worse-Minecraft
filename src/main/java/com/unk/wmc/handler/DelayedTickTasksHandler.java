package com.unk.wmc.handler;

import com.unk.wmc.Wmc;
import com.unk.wmc.util.DelayedTickTask;
import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Wmc.MOD_ID)
public class DelayedTickTasksHandler {
    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        MinecraftServer server = event.getServer();

        long now = server.overworld().getGameTime();
        List<DelayedTickTask.Task> toRun = new ArrayList<>();

        DelayedTickTask.TASKS.removeIf(task -> {
            if (now >= task.executeAt()) {
                toRun.add(task);
                return true;
            }
            return false;
        });

        for (DelayedTickTask.Task task : toRun) {
            try {
                task.action().run();
            } catch (Throwable throwable) {
                Wmc.LOGGER.error("Fail to run delayed task: ", throwable);
            }
        }
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        DelayedTickTask.TASKS.clear();
    }

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        DelayedTickTask.TASKS.clear();
    }
}
