package com.unk.wmc.item.crafting.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.unk.wmc.block.WmcBlocks;
import com.unk.wmc.item.crafting.WmcRecipeSerializers;
import com.unk.wmc.item.crafting.WmcRecipeTypes;
import com.unk.wmc.item.crafting.input.AltarCraftingRecipeInput;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record AltarCraftingRecipe(ItemStack n, ItemStack en, ItemStack e, ItemStack es, ItemStack s, ItemStack ws,
                                  ItemStack w, ItemStack wn, ItemStack mid,
                                  ItemStack result) implements Recipe<AltarCraftingRecipeInput> {
    @Override
    public boolean matches(AltarCraftingRecipeInput input, @NotNull Level level) {
        return ItemStack.isSameItemSameComponents(n, input.getItem(0))
                && ItemStack.isSameItemSameComponents(en, input.getItem(1))
                && ItemStack.isSameItemSameComponents(e, input.getItem(2))
                && ItemStack.isSameItemSameComponents(es, input.getItem(3))
                && ItemStack.isSameItemSameComponents(s, input.getItem(4))
                && ItemStack.isSameItemSameComponents(ws, input.getItem(5))
                && ItemStack.isSameItemSameComponents(w, input.getItem(6))
                && ItemStack.isSameItemSameComponents(wn, input.getItem(7))
                && ItemStack.isSameItemSameComponents(mid, input.getItem(8));
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
        return WmcRecipeSerializers.ALTAR_RECIPE.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return WmcRecipeTypes.ALTAR_CRAFTING.get();
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(WmcBlocks.ACTIVATE_ALTAR.get());
    }

    public static class Serializer implements RecipeSerializer<AltarCraftingRecipe> {
        public static final MapCodec<AltarCraftingRecipe> CODEC =
                RecordCodecBuilder.mapCodec(
                        inst -> inst.group(
                                ItemStack.CODEC.fieldOf("n").forGetter(AltarCraftingRecipe::n),
                                ItemStack.CODEC.fieldOf("en").forGetter(AltarCraftingRecipe::en),
                                ItemStack.CODEC.fieldOf("e").forGetter(AltarCraftingRecipe::e),
                                ItemStack.CODEC.fieldOf("es").forGetter(AltarCraftingRecipe::es),
                                ItemStack.CODEC.fieldOf("s").forGetter(AltarCraftingRecipe::s),
                                ItemStack.CODEC.fieldOf("ws").forGetter(AltarCraftingRecipe::ws),
                                ItemStack.CODEC.fieldOf("w").forGetter(AltarCraftingRecipe::w),
                                ItemStack.CODEC.fieldOf("wn").forGetter(AltarCraftingRecipe::wn),
                                ItemStack.CODEC.fieldOf("mid").forGetter(AltarCraftingRecipe::mid),
                                ItemStack.CODEC.fieldOf("result").forGetter(AltarCraftingRecipe::result)
                        ).apply(inst, AltarCraftingRecipe::new)
                );

        public static StreamCodec<RegistryFriendlyByteBuf, AltarCraftingRecipe> STREAM_CODEC =
                new StreamCodec<>() {
                    @Override
                    public @NotNull AltarCraftingRecipe decode(@NotNull RegistryFriendlyByteBuf buf) {
                        ItemStack n = ItemStack.STREAM_CODEC.decode(buf);
                        ItemStack en = ItemStack.STREAM_CODEC.decode(buf);
                        ItemStack e = ItemStack.STREAM_CODEC.decode(buf);
                        ItemStack es = ItemStack.STREAM_CODEC.decode(buf);
                        ItemStack s = ItemStack.STREAM_CODEC.decode(buf);
                        ItemStack ws = ItemStack.STREAM_CODEC.decode(buf);
                        ItemStack w = ItemStack.STREAM_CODEC.decode(buf);
                        ItemStack wn = ItemStack.STREAM_CODEC.decode(buf);
                        ItemStack mid = ItemStack.STREAM_CODEC.decode(buf);
                        ItemStack result = net.minecraft.world.item.ItemStack.STREAM_CODEC.decode(buf);
                        return new AltarCraftingRecipe(n, en, e, es, s, ws, w, wn, mid, result);
                    }

                    @Override
                    public void encode(@NotNull RegistryFriendlyByteBuf buf, AltarCraftingRecipe recipe) {
                        ItemStack.STREAM_CODEC.encode(buf, recipe.n());
                        ItemStack.STREAM_CODEC.encode(buf, recipe.en());
                        ItemStack.STREAM_CODEC.encode(buf, recipe.e());
                        ItemStack.STREAM_CODEC.encode(buf, recipe.es());
                        ItemStack.STREAM_CODEC.encode(buf, recipe.s());
                        ItemStack.STREAM_CODEC.encode(buf, recipe.ws());
                        ItemStack.STREAM_CODEC.encode(buf, recipe.w());
                        ItemStack.STREAM_CODEC.encode(buf, recipe.wn());
                        ItemStack.STREAM_CODEC.encode(buf, recipe.mid());
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
