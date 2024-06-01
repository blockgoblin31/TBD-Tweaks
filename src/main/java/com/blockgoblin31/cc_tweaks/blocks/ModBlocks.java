package com.blockgoblin31.cc_tweaks.blocks;


import com.blockgoblin31.cc_tweaks.CcTweaks;
import com.blockgoblin31.cc_tweaks.items.ModItems;
import com.blockgoblin31.cc_tweaks.tabs.ModCreativeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CcTweaks.MODID);

    @ObjectHolder(value = CcTweaks.MODID + ":mana_sourcelink", registryName = "minecraft:block")
    public static Block MANA_SOURCE_LINK;

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        ModItems.queueItemRegistry(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModCreativeTab.TAB)));
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }

    //for ars stuff
    public static void onBlockRegistry(IForgeRegistry<Block> registry) {
        registry.register(CcTweaks.MODID + ":mana_sourcelink", new ManaSourcelinkBlock());
    }
}
