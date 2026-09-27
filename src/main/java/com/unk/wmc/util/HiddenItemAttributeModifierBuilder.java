package com.unk.wmc.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class HiddenItemAttributeModifierBuilder {
    public static final ItemAttributeModifiers GENERAL_TERMINUS_ATTR =
            new HiddenItemAttributeModifierBuilder()
                    .add(
                            Attributes.ATTACK_DAMAGE,
                            new AttributeModifier(
                                    Item.BASE_ATTACK_DAMAGE_ID,
                                    Float.POSITIVE_INFINITY,
                                    AttributeModifier.Operation.ADD_VALUE
                            ),
                            EquipmentSlotGroup.MAINHAND
                    ).add(
                            Attributes.ATTACK_SPEED,
                            new AttributeModifier(
                                    Item.BASE_ATTACK_SPEED_ID,
                                    Float.POSITIVE_INFINITY,
                                    AttributeModifier.Operation.ADD_VALUE
                            ),
                            EquipmentSlotGroup.MAINHAND
                    ).add(
                            Attributes.ENTITY_INTERACTION_RANGE,
                            new AttributeModifier(
                                    ResourceLocation.withDefaultNamespace("base_entity_interaction_range"),
                                    Float.POSITIVE_INFINITY,
                                    AttributeModifier.Operation.ADD_VALUE
                            ),
                            EquipmentSlotGroup.MAINHAND
                    ).build();

    private final ImmutableList.Builder<ItemAttributeModifiers.Entry> entries = ImmutableList.builder();

    public HiddenItemAttributeModifierBuilder add(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot) {
        this.entries.add(new ItemAttributeModifiers.Entry(attribute, modifier, slot));
        return this;
    }

    public ItemAttributeModifiers build() {
        return new ItemAttributeModifiers(this.entries.build(), false);
    }
}
