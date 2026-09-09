package com.unk.wmc.loot.glm;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.unk.wmc.component.ActivatableData;
import com.unk.wmc.component.SimpleItemData;
import com.unk.wmc.component.WmcDataComponentTypes;
import com.unk.wmc.item.WmcItems;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class SmithingTemplateLootModifier extends LootModifier {
    public static final MapCodec<SmithingTemplateLootModifier> CODEC = RecordCodecBuilder.mapCodec((inst) ->
            LootModifier.codecStart(inst).apply(inst, SmithingTemplateLootModifier::new)
    );

    public SmithingTemplateLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> objectArrayList, @NotNull LootContext lootContext) {
        for (int i = 0; i < objectArrayList.size(); i++) {
            ItemStack stack = objectArrayList.get(i);
            if (stack.getItem() instanceof SmithingTemplateItem) {
                ItemStack result = new ItemStack(WmcItems.SMITHING_TEMPLATE_DUST.get(), stack.getCount());

                ResourceLocation location = BuiltInRegistries.ITEM.getKey(stack.getItem());

                result.set(WmcDataComponentTypes.ACTIVATABLE, new ActivatableData(false));
                result.set(WmcDataComponentTypes.SIMPLE_ITEM, new SimpleItemData(location));
                objectArrayList.set(i, result);
            }
        }
        return objectArrayList;
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
