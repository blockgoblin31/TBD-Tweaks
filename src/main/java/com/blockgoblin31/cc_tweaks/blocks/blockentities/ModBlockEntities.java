package com.blockgoblin31.cc_tweaks.blocks.blockentities;

import com.blockgoblin31.cc_tweaks.CcTweaks;
import com.blockgoblin31.cc_tweaks.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CcTweaks.MODID);

    @ObjectHolder(value = CcTweaks.MODID + ":mana_sourcelink", registryName = "minecraft:block_entity_type")
    public static BlockEntityType<ManaSourcelinkBlockEntity> MANA_SOURCE_LINK;

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }

    //for ars stuff
    public static void onBlockEntityRegistry(IForgeRegistry<BlockEntityType<?>> registry) {
        registry.register(CcTweaks.MODID + ":mana_sourcelink", BlockEntityType.Builder.of(ManaSourcelinkBlockEntity::new, ModBlocks.MANA_SOURCE_LINK).build(null));
    }
}
