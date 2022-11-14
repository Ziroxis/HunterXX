package com.yuanno.hunterxx.init;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.world.gen.ModStructureGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModEntityGeneration.onEntitySpawn(event);
        ModStructureGeneration.generateStructures(event);
        /*
        ModEntityGeneration.onEntitySpawn(event);
        ModTreeGeneration.generateTrees(event);

         */
    }
}
