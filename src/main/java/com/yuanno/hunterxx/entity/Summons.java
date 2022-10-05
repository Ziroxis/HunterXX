package com.yuanno.hunterxx.entity;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.client.overlay.renderer.EnModelRenderer;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Summons {

    public static final RegistryObject<EntityType<EnEntity>> EN = Beapi.registerEntityType("En entity",
            () -> Beapi.createEntityType(EnEntity::new)
                    .sized(1f, 1f)
                    .build(Main.MODID + ":en_entity"));

    
    
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(EN.get(), new EnModelRenderer.Factory());
    }
}
