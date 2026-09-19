package com.unk.wmc.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class WmcKeyBinds {
    public static final KeyMapping FRONT_ABILITY_KEY = new KeyMapping(
            "key.wmc.front_ability",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT,
            "key.categories.wmc.wmc_category"
    );

    public static final KeyMapping NEXT_ABILITY_KEY = new KeyMapping(
            "key.wmc.next_ability",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT,
            "key.categories.wmc.wmc_category"
    );

    public static final KeyMapping TRIGGER_ABILITY_KEY = new KeyMapping(
            "key.wmc.trigger_ability",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_ENTER,
            "key.categories.wmc.wmc_category"
    );
}
