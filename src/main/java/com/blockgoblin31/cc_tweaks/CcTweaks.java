package com.blockgoblin31.cc_tweaks;

import com.blockgoblin31.cc_tweaks.blocks.ModFlowerBlocks;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CcTweaks.MODID)
public class CcTweaks {
    public static final String MODID = "cc_tweaks";
    private static final Logger LOGGER = LogUtils.getLogger();
    public CcTweaks() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        registryInit();
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    //copying how botania does its flowers because I cant figure out how else to do them
    private static void registryInit() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        bind(Registry.BLOCK_REGISTRY, ModFlowerBlocks::registerFlowerBlocks);
        bind(Registry.ITEM_REGISTRY, ModFlowerBlocks::registerFlowerBlockItems);
        bind(Registry.BLOCK_ENTITY_TYPE_REGISTRY, ModFlowerBlocks::registerTileEntities);
    }
    private static <T> void bind(ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener((RegisterEvent event) -> {
            if (registry.equals(event.getRegistryKey())) {
                source.accept((t, rl) -> event.register(registry, rl, () -> t));
            }
        });
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
}
