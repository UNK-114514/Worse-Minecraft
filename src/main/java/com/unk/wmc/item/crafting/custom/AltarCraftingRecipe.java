package com.unk.wmc.item.crafting.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.unk.wmc.item.crafting.WmcRecipeSerializers;
import com.unk.wmc.item.crafting.WmcRecipeTypes;
import com.unk.wmc.item.crafting.input.AltarCraftingRecipeInput;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record AltarCraftingRecipe(Ingredient n, Ingredient en, Ingredient e, Ingredient es, Ingredient s, Ingredient ws,
                                  Ingredient w, Ingredient wn, Ingredient mid,
                                  ItemStack result) implements Recipe<AltarCraftingRecipeInput> {
    @Override
    public boolean matches(AltarCraftingRecipeInput input, @NotNull Level level) {
        return n.test(input.getItem(0))
                && en.test(input.getItem(1))
                && e.test(input.getItem(2))
                && es.test(input.getItem(3))
                && s.test(input.getItem(4))
                && ws.test(input.getItem(5))
                && w.test(input.getItem(6))
                && wn.test(input.getItem(7))
                && mid.test(input.getItem(8));
    }

    @Override
    public @NotNull ItemStack assemble(
            @NotNull AltarCraftingRecipeInput altarCraftingRecipeInput,
            HolderLookup.@NotNull Provider provider) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return WmcRecipeSerializers.ALTAR_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return WmcRecipeTypes.ALTAR_RECIPE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<AltarCraftingRecipe> {
        public static final MapCodec<AltarCraftingRecipe> CODEC =
                RecordCodecBuilder.mapCodec(
                        inst -> inst.group(
                                Ingredient.CODEC.fieldOf("n").forGetter(AltarCraftingRecipe::n),
                                Ingredient.CODEC.fieldOf("en").forGetter(AltarCraftingRecipe::en),
                                Ingredient.CODEC.fieldOf("e").forGetter(AltarCraftingRecipe::e),
                                Ingredient.CODEC.fieldOf("es").forGetter(AltarCraftingRecipe::es),
                                Ingredient.CODEC.fieldOf("s").forGetter(AltarCraftingRecipe::s),
                                Ingredient.CODEC.fieldOf("ws").forGetter(AltarCraftingRecipe::ws),
                                Ingredient.CODEC.fieldOf("w").forGetter(AltarCraftingRecipe::w),
                                Ingredient.CODEC.fieldOf("wn").forGetter(AltarCraftingRecipe::wn),
                                Ingredient.CODEC.fieldOf("mid").forGetter(AltarCraftingRecipe::mid),
                                net.minecraft.world.item.ItemStack.CODEC.fieldOf("result").forGetter(AltarCraftingRecipe::result)
                        ).apply(inst, AltarCraftingRecipe::new)
                );

        public static StreamCodec<RegistryFriendlyByteBuf, AltarCraftingRecipe> STREAM_CODEC =
                new StreamCodec<>() {
                    @Override
                    public @NotNull AltarCraftingRecipe decode(@NotNull RegistryFriendlyByteBuf buf) {
                        Ingredient n = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                        Ingredient en = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                        Ingredient e = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                        Ingredient es = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                        Ingredient s = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                        Ingredient ws = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                        Ingredient w = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                        Ingredient wn = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                        Ingredient mid = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
                        ItemStack result = net.minecraft.world.item.ItemStack.STREAM_CODEC.decode(buf);
                        return new AltarCraftingRecipe(n, en, e, es, s, ws, w, wn, mid, result);
                    }

                    @Override
                    public void encode(@NotNull RegistryFriendlyByteBuf buf, AltarCraftingRecipe recipe) {
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.n());
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.en());
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.e());
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.es());
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.s());
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.ws());
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.w());
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.wn());
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.mid());
                        ItemStack.STREAM_CODEC.encode(buf, recipe.result());
                    }
                };

        @Override
        public @NotNull MapCodec<AltarCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, AltarCraftingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
