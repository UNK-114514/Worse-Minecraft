package com.unk.wmc.menu;

import com.unk.wmc.Wmc;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class WmcMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(BuiltInRegistries.MENU, Wmc.MOD_ID);

    public static final Supplier<MenuType<AssemblyTableMenu>> BLUEPRINT_DRAFTER_MENU =
            MENU_TYPES.register("blueprint_drafter_menu",
                    () -> new MenuType<>(AssemblyTableMenu::new, FeatureFlags.DEFAULT_FLAGS));
}
