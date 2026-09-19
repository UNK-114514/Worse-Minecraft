package com.unk.wmc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.unk.wmc.component.BlueprintData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.helper.ItemStackHelper;
import com.unk.wmc.item.WmcItemTags;
import com.unk.wmc.item.WmcItems;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Optional;

public class WmcDebugCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {
        dispatcher.register(Commands.literal("wmc:debug")
                .requires((stack) -> stack.hasPermission(4))
                .then(Commands.literal("template_dust")
                        .then(Commands.argument("item", ItemArgument.item(buildContext))
                                .then(Commands.argument("is_activated", BoolArgumentType.bool())
                                        .executes((context -> {
                                            CommandSourceStack source = context.getSource();

                                            if (source.getPlayer() == null) return 0;

                                            ItemStackHelper.givePlayerStack(
                                                    source.getPlayer(),
                                                    ItemStackHelper.getDustOf(
                                                            ItemArgument.getItem(context, "item").getItem(),
                                                            BoolArgumentType.getBool(context, "is_activated")
                                                    )
                                            );

                                            return 1;
                                        }))
                                )
                        )
                )
                .then(Commands.literal("blueprint")
                        .then(Commands.literal("next")
                                .executes(context -> {
                                    CommandSourceStack source = context.getSource();
                                    Player player = source.getPlayer();

                                    if (player == null) return 0;

                                    ItemStack stack = player.getMainHandItem();

                                    if (!stack.has(WmcDataComponentTypes.BLUEPRINT)) return 0;

                                    ItemStack result = ItemStackHelper.doNextStep(stack);

                                    player.setItemInHand(InteractionHand.MAIN_HAND, result);
                                    return 1;
                                })
                        )
                        .then(Commands.literal("all_template")
                                .executes(context -> {
                                    CommandSourceStack source = context.getSource();
                                    Player player = source.getPlayer();

                                    if (player == null) return 0;

                                    BlueprintData.BlueprintComponentBuilder builder =
                                            new BlueprintData.BlueprintComponentBuilder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);

                                    Optional<HolderSet.Named<Item>> optionalTag =
                                            BuiltInRegistries.ITEM.getTag(WmcItemTags.TERMINUS_UPGRADE_SMITHING_TEMPLATE_REQUIRES);

                                    optionalTag.ifPresent(holders -> {
                                        for (var holder : holders) {
                                            builder.addStep(holder.value().getDefaultInstance());
                                        }
                                    });

                                    ItemStack result = WmcItems.BLUEPRINT.get().getDefaultInstance();
                                    result.set(WmcDataComponentTypes.BLUEPRINT.get(), builder.build());

                                    ItemStackHelper.givePlayerStack(player, result);

                                    return 1;
                                })
                        )
                        .then(Commands.literal("random")
                                .then(Commands.argument("count", IntegerArgumentType.integer(1, 2147483647))
                                        .executes(context -> {
                                            CommandSourceStack source = context.getSource();
                                            Player player = source.getPlayer();

                                            if (player == null) return 0;

                                            BlueprintData.BlueprintComponentBuilder builder =
                                                    new BlueprintData.BlueprintComponentBuilder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);

                                            ItemStack result = WmcItems.RANDOM_BLUEPRINT.get().getDefaultInstance();
                                            result.set(WmcDataComponentTypes.BLUEPRINT.get(),
                                                    builder.getRandom(IntegerArgumentType.getInteger(context, "count")));

                                            ItemStackHelper.givePlayerStack(player, result);

                                            return 1;
                                        })
                                )
                        )
                )
        );
    }
}
