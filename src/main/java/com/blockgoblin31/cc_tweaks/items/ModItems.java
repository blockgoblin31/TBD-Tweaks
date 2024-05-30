package com.blockgoblin31.cc_tweaks.items;

import com.blockgoblin31.cc_tweaks.CcTweaks;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CcTweaks.MODID);

    public static void queueItemRegistry(String name, Supplier<? extends Item> item) {
        ITEMS.register(name, item);
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
