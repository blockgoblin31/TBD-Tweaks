package com.blockgoblin31.cc_tweaks.tabs;

import com.blockgoblin31.cc_tweaks.blocks.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static final CreativeModeTab TAB = new CreativeModeTab("cc_tweaks_tab") {
        @Override
        public ItemStack makeIcon() {
            return ModBlocks.sourcebloom.get().asItem().getDefaultInstance();
        }
    };
}
