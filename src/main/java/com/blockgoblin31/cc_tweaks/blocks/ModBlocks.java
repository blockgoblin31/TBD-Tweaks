package com.blockgoblin31.cc_tweaks.blocks;


import com.blockgoblin31.cc_tweaks.CcTweaks;
import com.blockgoblin31.cc_tweaks.blocks.blockentities.SourceBloom;
import com.blockgoblin31.cc_tweaks.items.ModItems;
import com.blockgoblin31.cc_tweaks.tabs.ModCreativeTab;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.FloatingSpecialFlowerBlock;
import vazkii.botania.common.block.flower.generating.EndoflameBlockEntity;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.block.SpecialFlowerBlockItem;
import vazkii.botania.forge.block.ForgeSpecialFlowerBlock;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CcTweaks.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CcTweaks.MODID);

    public static final RegistryObject<Block> sourcebloom = registerFlowerBlock("sourcebloom", () -> {
        return XplatAbstractions.INSTANCE.createSpecialFlowerBlock(MobEffects.MOVEMENT_SPEED, 10, BlockBehaviour.Properties.copy(Blocks.POPPY), () -> ModBlocks.SOURCEBLOOM);
    });
    public static final RegistryObject<Block> sourcebloomFloating = registerFlowerBlock("sourcebloom_floating", () -> {
        return new FloatingSpecialFlowerBlock(BotaniaBlocks.FLOATING_PROPS, () -> ModBlocks.SOURCEBLOOM);
    });

    public static final BlockEntityType<SourceBloom> SOURCEBLOOM = XplatAbstractions.INSTANCE.createBlockEntityType(SourceBloom::new, sourcebloom.get(), sourcebloomFloating.get());

    private static <T extends Block> RegistryObject<Block> registerFlowerBlock(String name, Supplier<T> block) {
        RegistryObject<Block> output = BLOCKS.register(name, block);
        ModItems.queueItemRegistry(name, () -> new SpecialFlowerBlockItem(block.get(), BotaniaItems.defaultBuilder().tab(ModCreativeTab.TAB)));
        return output;
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
