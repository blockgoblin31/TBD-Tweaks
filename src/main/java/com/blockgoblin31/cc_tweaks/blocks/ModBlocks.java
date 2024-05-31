package com.blockgoblin31.cc_tweaks.blocks;


import com.blockgoblin31.cc_tweaks.CcTweaks;
import com.blockgoblin31.cc_tweaks.items.ModItems;
import com.blockgoblin31.cc_tweaks.tabs.ModCreativeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CcTweaks.MODID);

    public static final RegistryObject<Block> MANA_SOURCE_LINK = registerBlock("mana_sourcelink", ManaSourcelinkBlock::new);

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        ModItems.queueItemRegistry(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModCreativeTab.TAB)));
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
