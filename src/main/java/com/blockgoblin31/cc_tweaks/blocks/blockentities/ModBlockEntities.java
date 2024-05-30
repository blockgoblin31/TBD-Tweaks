package com.blockgoblin31.cc_tweaks.blocks.blockentities;

import com.blockgoblin31.cc_tweaks.CcTweaks;
import com.blockgoblin31.cc_tweaks.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.botania.xplat.XplatAbstractions;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CcTweaks.MODID);
    public static final RegistryObject<BlockEntityType<SourceBloom>> SOURCEBLOOM = BLOCK_ENTITIES.register("sourcebloom", () ->
            BlockEntityType.Builder.of(SourceBloom::new, ModBlocks.sourcebloom.get(), ModBlocks.sourcebloomFloating.get()).build(null)
    );

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
