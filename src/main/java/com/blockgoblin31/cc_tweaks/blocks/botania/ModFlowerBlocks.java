package com.blockgoblin31.cc_tweaks.blocks.botania;

import com.blockgoblin31.cc_tweaks.CcTweaks;
import com.blockgoblin31.cc_tweaks.tabs.ModCreativeTab;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.FloatingSpecialFlowerBlock;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.block.SpecialFlowerBlockItem;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.function.BiConsumer;

public class ModFlowerBlocks {
    public static final Block sourcebloom = XplatAbstractions.INSTANCE.createSpecialFlowerBlock(MobEffects.MOVEMENT_SPEED, 10, BlockBehaviour.Properties.copy(Blocks.POPPY), () -> ModFlowerBlocks.SOURCEBLOOM);
    public static final Block sourcebloomFloating = new FloatingSpecialFlowerBlock(BotaniaBlocks.FLOATING_PROPS, () -> ModFlowerBlocks.SOURCEBLOOM);
    public static final BlockEntityType<SourceBloom> SOURCEBLOOM = XplatAbstractions.INSTANCE.createBlockEntityType(SourceBloom::new, sourcebloom, sourcebloomFloating);


    //why is botania like this
    public static void registerFlowerBlocks(BiConsumer<Block, ResourceLocation> r) {
        r.accept(sourcebloom, new ResourceLocation(CcTweaks.MODID, "sourcebloom"));
        r.accept(sourcebloomFloating, new ResourceLocation(CcTweaks.MODID, "floating_sourcebloom"));
    }
    public static void registerFlowerBlockItems(BiConsumer<Item, ResourceLocation> r) {
        r.accept(new SpecialFlowerBlockItem(sourcebloom, BotaniaItems.defaultBuilder().tab(ModCreativeTab.TAB)), Registry.BLOCK.getKey(sourcebloom));
        r.accept(new SpecialFlowerBlockItem(sourcebloomFloating, BotaniaItems.defaultBuilder().tab(ModCreativeTab.TAB)), Registry.BLOCK.getKey(sourcebloomFloating));
    }
    public static void registerTileEntities(BiConsumer<BlockEntityType<?>, ResourceLocation> r) {
        r.accept(SOURCEBLOOM, Registry.BLOCK.getKey(sourcebloom));
    }
}
