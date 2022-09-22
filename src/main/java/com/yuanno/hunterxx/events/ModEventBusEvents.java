package com.yuanno.hunterxx.events;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.entity.questers.basic.EntityBasicQuest1;
import com.yuanno.hunterxx.init.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerEntityAttribute(EntityAttributeCreationEvent event)
    {
        event.put(ModEntities.BASICQUESTENTITY1.get(), QuesterEntity.setCustomAttributes().build());
    }
}
