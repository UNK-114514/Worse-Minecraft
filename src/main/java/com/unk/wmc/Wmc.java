package com.unk.wmc;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.logging.LogUtils;
import com.unk.wmc.block.WmcBlocks;
import com.unk.wmc.block.entity.WmcBlockEntityTypes;
import com.unk.wmc.command.WmcDebugCommand;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.item.WmcCreativeTabs;
import com.unk.wmc.item.WmcItems;
import com.unk.wmc.item.crafting.WmcRecipeSerializers;
import com.unk.wmc.item.crafting.WmcRecipeTypes;
import com.unk.wmc.loot.glm.WmcGlobalLootModifierSerializers;
import com.unk.wmc.menu.WmcMenuTypes;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(Wmc.MOD_ID)
@SuppressWarnings("unused")
public class Wmc {
    public static final String MOD_ID = "wmc";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Wmc(IEventBus modEventBus, ModContainer modContainer) {
        WmcBlocks.BLOCKS.register(modEventBus);
        WmcBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        WmcItems.ITEMS.register(modEventBus);

        WmcRecipeTypes.RECIPE_TYPES.register(modEventBus);
        WmcRecipeSerializers.SERIALIZERS.register(modEventBus);

        WmcMenuTypes.MENU_TYPES.register(modEventBus);
        WmcCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        WmcGlobalLootModifierSerializers.GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        WmcDataComponentTypes.DATA_COMPONENT_TYPES.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        CommandBuildContext context = event.getBuildContext();

        WmcDebugCommand.register(dispatcher, context);
    }
}