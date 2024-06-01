package com.blockgoblin31.cc_tweaks.items;

import com.blockgoblin31.cc_tweaks.CcTweaks;
import com.blockgoblin31.cc_tweaks.blocks.ModBlocks;
import com.blockgoblin31.cc_tweaks.tabs.ModCreativeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CcTweaks.MODID);

    public static void queueItemRegistry(String name, Supplier<? extends Item> item) {
        ITEMS.register(name, item);
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    //for ars stuff
    public static void onBlockItemRegistry(IForgeRegistry<Item> registry) {
        /*registry.register(CcTweaks.MODID + ":mana_sourcelink", new RendererBlockItem() {
            @Override
            public Supplier<BlockEntityWithoutLevelRenderer> getRenderer() {
                return null;
            }
        });*/
        registry.register(CcTweaks.MODID + ":mana_sourcelink", new BlockItem(ModBlocks.MANA_SOURCE_LINK, new Item.Properties().tab(ModCreativeTab.TAB)));
    }
}
