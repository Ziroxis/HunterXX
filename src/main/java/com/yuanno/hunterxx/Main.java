package com.yuanno.hunterxx;

import com.yuanno.hunterxx.client.gui.NenBarOverlay;
import com.yuanno.hunterxx.client.handler.ClientHandler;
import com.yuanno.hunterxx.data.AttachingCapability;
import com.yuanno.hunterxx.init.*;
import com.yuanno.hunterxx.world.structure.configured.ConfiguredStructures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("hunterxx")
public class Main
{
    // Directly reference a log4j logger.
    public static final String MODID = "hunterxx";
    private static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModRegistry.ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new AttachingCapability.Registry());
        ModAttributes.ATTRIBUTES.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModAbilities.register(modEventBus);
        ModQuests.QUESTS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModParticleTypes.PARTICLE_TYPES.register(modEventBus);
        ModStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);



        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        ModCapabilities.init();
        ModNetwork.init();
        event.enqueueWork(() ->
        {
            ModStructures.setupStructures();
            ConfiguredStructures.registerConfiguredStructures();
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        ClientHandler.onSetup();
        ModKeyBinds.init();
        MinecraftForge.EVENT_BUS.register(new NenBarOverlay());
        ModRenderers.registerRenderers();
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {

    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }
}
