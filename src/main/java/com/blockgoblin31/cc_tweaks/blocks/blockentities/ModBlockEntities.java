package com.blockgoblin31.cc_tweaks.blocks.blockentities;

import com.blockgoblin31.cc_tweaks.CcTweaks;
import com.blockgoblin31.cc_tweaks.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CcTweaks.MODID);

    public static final RegistryObject<BlockEntityType<ManaSourcelinkBlockEntity>> MANA_SOURCE_LINK = BLOCK_ENTITIES.register("mana_sourcelink", () -> BlockEntityType.Builder.of(ManaSourcelinkBlockEntity::new, ModBlocks.MANA_SOURCE_LINK.get()).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
