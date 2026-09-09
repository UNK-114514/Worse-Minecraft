package com.unk.wmc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.helper.ItemStackHelper;
import com.unk.wmc.item.WmcItemTags;
import com.unk.wmc.item.custom.BlueprintItem;
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

import java.util.*;

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

                                    BlueprintItem.BlueprintBuilder builder =
                                            new BlueprintItem.BlueprintBuilder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);

                                    Optional<HolderSet.Named<Item>> optionalTag =
                                            BuiltInRegistries.ITEM.getTag(WmcItemTags.FINAL_SMITING_TEMPLATE_REQUIRES);

                                    optionalTag.ifPresent(holders -> {
                                        for (var holder : holders) {
                                            builder.addStep(holder.value());
                                        }
                                    });

                                    ItemStackHelper.givePlayerStack(player, builder.build());

                                    return 1;
                                })
                        )
                        .then(Commands.literal("random_100")
                                .executes(context -> {
                                    CommandSourceStack source = context.getSource();
                                    Player player = source.getPlayer();

                                    if (player == null) return 0;

                                    BlueprintItem.BlueprintBuilder builder =
                                            new BlueprintItem.BlueprintBuilder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);

                                    List<Integer> indexes = new ArrayList<>();

                                    for (int i = 0; i < BuiltInRegistries.ITEM.size(); i++) {
                                        indexes.add(i);
                                    }

                                    Collections.shuffle(indexes);

                                    for (int i : indexes.subList(0, 100)) {
                                        builder.addStep(BuiltInRegistries.ITEM.byId(i));
                                    }

                                    ItemStackHelper.givePlayerStack(player, builder.build());

                                    return 1;
                                })
                        )
                )
        );
    }
}
