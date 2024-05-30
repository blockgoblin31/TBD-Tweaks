package com.blockgoblin31.cc_tweaks.blocks;


import com.blockgoblin31.cc_tweaks.CcTweaks;
import com.blockgoblin31.cc_tweaks.blocks.blockentities.ModBlockEntities;
import com.blockgoblin31.cc_tweaks.blocks.blockentities.SourceBloom;
import com.blockgoblin31.cc_tweaks.items.ModItems;
import com.blockgoblin31.cc_tweaks.tabs.ModCreativeTab;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.FloatingSpecialFlowerBlock;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.block.SpecialFlowerBlockItem;
import vazkii.botania.forge.block.ForgeSpecialFlowerBlock;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CcTweaks.MODID);
    public static final RegistryObject<Block> sourcebloom = registerBlock("sourcebloom", () ->
            new ForgeSpecialFlowerBlock(MobEffects.MOVEMENT_SPEED, 10, BlockBehaviour.Properties.copy(Blocks.POPPY), ModBlockEntities.SOURCEBLOOM::get)
    );
    public static final RegistryObject<Block> sourcebloomFloating = registerBlock("floating_sourcebloom", () ->
            new FloatingSpecialFlowerBlock(BotaniaBlocks.FLOATING_PROPS, ModBlockEntities.SOURCEBLOOM::get)
    );

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        ModItems.queueItemRegistry(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModCreativeTab.TAB)));
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
