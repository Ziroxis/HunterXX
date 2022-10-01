package com.yuanno.hunterxx.events.aura;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AuraEvents {
    
    @SubscribeEvent
    public static void regenerateAura(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        float regen = entityStats.getAuraRegeneration();

        if (player.tickCount % 20 == 0 && entityStats.getAura() < entityStats.getMaxAura())
        {
            entityStats.alterAura((int) regen);
        }
    }
}
