package com.blockgoblin31.cc_tweaks;

import com.blockgoblin31.cc_tweaks.blocks.ModBlocks;
import com.blockgoblin31.cc_tweaks.blocks.blockentities.ModBlockEntities;
import com.blockgoblin31.cc_tweaks.blocks.botania.ModFlowerBlocks;
import com.blockgoblin31.cc_tweaks.items.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;
import vazkii.botania.api.BotaniaForgeCapabilities;
import vazkii.botania.api.mana.ManaReceiver;
import vazkii.botania.forge.CapabilityUtil;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CcTweaks.MODID)
public class CcTweaks {
    public static final String MODID = "cc_tweaks";
    private static final Logger LOGGER = LogUtils.getLogger();
    public CcTweaks() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        botaniaRegistryInit(modEventBus);
        modEventBus.addListener(CcTweaks::registerEvents);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);


        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addGenericListener(BlockEntity.class, CcTweaks::attachBlockEntityCapabilities);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static void registerEvents(RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.BLOCKS)) {
            IForgeRegistry<Block> registry = Objects.requireNonNull(event.getForgeRegistry());
            ModBlocks.onBlockRegistry(registry);
        }
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)) {
            IForgeRegistry<Item> registry = Objects.requireNonNull(event.getForgeRegistry());
            ModItems.onBlockItemRegistry(registry);
        }
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES)) {
            IForgeRegistry<BlockEntityType<?>> registry = Objects.requireNonNull(event.getForgeRegistry());
            ModBlockEntities.onBlockEntityRegistry(registry);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }

    //copying how botania does its flowers because I cant figure out how else to do them. I know this is cursed.
    //never touch again
    public static void botaniaRegistryInit(IEventBus bus) {
        bind(bus, Registry.BLOCK_REGISTRY, ModFlowerBlocks::registerFlowerBlocks);
        bind(bus, Registry.ITEM_REGISTRY, ModFlowerBlocks::registerFlowerBlockItems);
        bind(bus, Registry.BLOCK_ENTITY_TYPE_REGISTRY, ModFlowerBlocks::registerTileEntities);
    }
    private static <T> void bind(IEventBus bus, ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        bus.addListener((RegisterEvent event) -> {
            if (registry.equals(event.getRegistryKey())) {
                source.accept((t, rl) -> event.register(registry, rl, () -> t));
            }
        });
    }
    private static void attachBlockEntityCapabilities(AttachCapabilitiesEvent<BlockEntity> event) {
        BlockEntity be = event.getObject();
        if (be.getType() == ModBlockEntities.MANA_SOURCE_LINK) {
            event.addCapability(new ResourceLocation(CcTweaks.MODID, "mana_receiver"), CapabilityUtil.makeProvider(BotaniaForgeCapabilities.MANA_RECEIVER, (ManaReceiver) be));
        }
    }
}
