package com.yuanno.hunterxx.events;

import com.yuanno.hunterxx.entity.hostile.FoxBearEntity;
import com.yuanno.hunterxx.entity.questers.QuesterEntity;
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
        event.put(ModEntities.BASICQUESTENTITY2.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICQUESTENTITY3.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICQUESTENTITY4.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICQUESTENTITY5.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICQUESTENTITY6.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICQUESTENTITY7.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICQUESTENTITY8.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICQUESTENTITY9.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICQUESTENTITY10.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICQUESTENTITY11.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICQUESTENTITY12.get(), QuesterEntity.setCustomAttributes().build());

        event.put(ModEntities.BASICKILLINGENTITY1.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICKILLINGENTITY2.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICKILLINGENTITY3.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICKILLINGENTITY4.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICKILLINGENTITY5.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.BASICKILLINGENTITY6.get(), QuesterEntity.setCustomAttributes().build());

        event.put(ModEntities.ENTITYLICENSEQUEST.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.ENTITYNENTEACHER.get(), QuesterEntity.setCustomAttributes().build());
        event.put(ModEntities.ADVANCEDNENTEACHER.get(), QuesterEntity.setCustomAttributes().build());

        event.put(ModEntities.FOXBEAR.get(), FoxBearEntity.setCustomAttributes().build());
    }
}
