package com.unk.wmc.item.crafting.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import com.unk.wmc.item.crafting.input.SmithingTemplateCraftingRecipeInput;
import com.unk.wmc.item.crafting.WmcRecipeSerializers;
import org.jetbrains.annotations.NotNull;

public record SmithingTemplateCraftingRecipeImpl(Ingredient baseTemplate, Ingredient templateCore,
                                                 ItemStack result) implements SmithingTemplateCraftingRecipe {

    @Override
    public boolean isBaseTemplate(ItemStack var1) {
        return baseTemplate.test(var1);
    }

    @Override
    public boolean isTemplateCore(ItemStack var1) {
        return templateCore.test(var1);
    }

    @Override
    public boolean matches(SmithingTemplateCraftingRecipeInput smithingTemplateCraftingRecipeInput, @NotNull Level level) {
        return baseTemplate.test(smithingTemplateCraftingRecipeInput.baseTemplate()) && templateCore.test(smithingTemplateCraftingRecipeInput.templateCore());
    }

    @Override
    public @NotNull ItemStack assemble(
            @NotNull SmithingTemplateCraftingRecipeInput smithingTemplateCraftingRecipeInput,
            HolderLookup.@NotNull Provider provider) {
        return result.copy();
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return WmcRecipeSerializers.SMITHING_TEMPLATE_CRAFTING_RECIPE_SERIALIZER.get();
    }

    public static class Serializer implements RecipeSerializer<SmithingTemplateCraftingRecipeImpl> {
        private static final MapCodec<SmithingTemplateCraftingRecipeImpl> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("base_template").forGetter(r -> r.baseTemplate),
                        Ingredient.CODEC_NONEMPTY.fieldOf("template_core").forGetter(r -> r.templateCore),
                        ItemStack.CODEC.fieldOf("result").forGetter(r -> r.result)
                ).apply(instance, SmithingTemplateCraftingRecipeImpl::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, SmithingTemplateCraftingRecipeImpl> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        public @NotNull MapCodec<SmithingTemplateCraftingRecipeImpl> codec() {
            return CODEC;
        }

        public @NotNull StreamCodec<RegistryFriendlyByteBuf, SmithingTemplateCraftingRecipeImpl> streamCodec() {
            return STREAM_CODEC;
        }

        private static SmithingTemplateCraftingRecipeImpl fromNetwork(RegistryFriendlyByteBuf buf) {
            Ingredient baseTemplate = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            Ingredient templateCore = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
            return new SmithingTemplateCraftingRecipeImpl(baseTemplate, templateCore, result);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, SmithingTemplateCraftingRecipeImpl recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.baseTemplate);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.templateCore);
            ItemStack.STREAM_CODEC.encode(buf, recipe.result);
        }
    }
}
