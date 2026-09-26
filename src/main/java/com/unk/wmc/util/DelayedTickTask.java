package com.unk.wmc.util;

import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;
import java.util.List;

public class DelayedTickTask {
    public record Task(long executeAt, Runnable action) {}

    public static final List<Task> TASKS = new ArrayList<>();

    public static void schedule(ServerLevel level, int delayTicks, Runnable action) {
        long startTick = level.getGameTime() + delayTicks;
        TASKS.add(new Task(startTick, action));
    }
}
