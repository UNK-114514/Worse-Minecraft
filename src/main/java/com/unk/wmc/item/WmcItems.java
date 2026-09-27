package com.unk.wmc.item;

import com.unk.wmc.Wmc;
import com.unk.wmc.item.custom.*;
import com.unk.wmc.item.custom.tier.WmcTiers;
import com.unk.wmc.util.HiddenItemAttributeModifierBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Unbreakable;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class WmcItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Wmc.MOD_ID);

    public static final DeferredItem<Item> SMITHING_TEMPLATE_DUST = ITEMS.register("smithing_template_dust", () -> new SmithingTemplateDustItem(new Item.Properties()));
    public static final DeferredItem<Item> SMITHING_TEMPLATE_CORE = ITEMS.register("smithing_template_core", () -> new SmithingTemplateCoreItem(new Item.Properties()));
    public static final DeferredItem<Item> EMPTY_SMITING_TEMPLATE = ITEMS.register("empty_smithing_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLUEPRINT = ITEMS.register("blueprint", () -> new BlueprintItem(new Item.Properties()));
    public static final DeferredItem<Item> RANDOM_BLUEPRINT = ITEMS.register("random_blueprint", () -> new BlueprintItem(new Item.Properties()));
    public static final DeferredItem<Item> RANDOM_CORE = ITEMS.register("random_core", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_TERMINUS_INGOT = ITEMS.register("raw_terminus_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TERMINUS_INGOT = ITEMS.register("terminus_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TERMINUS_NUGGET = ITEMS.register("terminus_nugget", () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> COPPER_COGWHEEL = ITEMS.register("copper_cogwheel", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_COGWHEEL = ITEMS.register("iron_cogwheel", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_COGWHEEL = ITEMS.register("gold_cogwheel", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIAMOND_COGWHEEL = ITEMS.register("diamond_cogwheel", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_COGWHEEL = ITEMS.register("netherite_cogwheel", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TERMINUS_SWORD = ITEMS.register("terminus_sword", () -> new TerminusSwordItem(WmcTiers.TERMUNIUS_TIER, new Item.Properties().attributes(HiddenItemAttributeModifierBuilder.GENERAL_TERMINUS_ATTR).component(DataComponents.UNBREAKABLE, new Unbreakable(true)).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> TERMINUS_PICKAXE = ITEMS.register("terminus_pickaxe", () -> new TerminusPickaxeItem(WmcTiers.TERMUNIUS_TIER, new Item.Properties().attributes(HiddenItemAttributeModifierBuilder.GENERAL_TERMINUS_ATTR).component(DataComponents.UNBREAKABLE, new Unbreakable(true)).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> TERMINUS_AXE = ITEMS.register("terminus_axe", () -> new TerminusAxeItem(WmcTiers.TERMUNIUS_TIER, new Item.Properties().attributes(HiddenItemAttributeModifierBuilder.GENERAL_TERMINUS_ATTR).component(DataComponents.UNBREAKABLE, new Unbreakable(true)).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> TERMINUS_SHOVEL = ITEMS.register("terminus_shovel", () -> new TerminusShovelItem(WmcTiers.TERMUNIUS_TIER, new Item.Properties().attributes(HiddenItemAttributeModifierBuilder.GENERAL_TERMINUS_ATTR).component(DataComponents.UNBREAKABLE, new Unbreakable(true)).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> TERMINUS_HOE = ITEMS.register("terminus_hoe", () -> new TerminusHoeItem(WmcTiers.TERMUNIUS_TIER, new Item.Properties().attributes(HiddenItemAttributeModifierBuilder.GENERAL_TERMINUS_ATTR).component(DataComponents.UNBREAKABLE, new Unbreakable(true)).fireResistant().rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> TERMINUS_UPGRADE_SMITHING_TEMPLATE =
            ITEMS.register("terminus_upgrade_smithing_template", () -> new SmithingTemplateItem(
                    Component.translatable("item.wmc.smithing_template.terminus_upgrade.applies_to").withStyle(ChatFormatting.BLUE),
                    Component.translatable("item.wmc.smithing_template.terminus_upgrade.ingredients").withStyle(ChatFormatting.BLUE),
                    Component.translatable("upgrade.wmc.terminus_upgrade").withStyle(ChatFormatting.GRAY),
                    Component.translatable("item.wmc.smithing_template.terminus_upgrade.base_slot_description"),
                    Component.translatable("item.wmc.smithing_template.terminus_upgrade.additions_slot_description"),
                    List.of(
                            ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet"),
                            ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate"),
                            ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings"),
                            ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots"),
                            ResourceLocation.withDefaultNamespace("item/empty_armor_slot_sword")
                    ),
                    List.of(ResourceLocation.withDefaultNamespace("item/empty_slot_ingot"))
            ));
}
