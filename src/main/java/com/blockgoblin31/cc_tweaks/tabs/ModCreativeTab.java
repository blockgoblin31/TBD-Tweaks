package com.blockgoblin31.cc_tweaks.tabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModCreativeTab {
    public static final CreativeModeTab TAB = new CreativeModeTab("cc_tweaks_tab") {
        @Override
        public ItemStack makeIcon() {
            return null;
        }
    };
}
