package com.unk.wmc.menu;

import com.unk.wmc.Wmc;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.unk.wmc.menu.custom.SmithingTemplateCraftingMenu;

import java.util.function.Supplier;

public class WmcMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, Wmc.MOD_ID);

    public static final Supplier<MenuType<SmithingTemplateCraftingMenu>> SMITHING_TEMPLATE_CRAFTING_MENU_TYPE = MENU_TYPES.register("smithing_template_crafting_menu_type", () -> new MenuType<>(SmithingTemplateCraftingMenu::new, FeatureFlags.DEFAULT_FLAGS));
}
