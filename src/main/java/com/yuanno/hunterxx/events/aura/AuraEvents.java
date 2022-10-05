package com.yuanno.hunterxx.events.aura;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.abilities.advanced.EnAbility;
import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AuraEvents {

    @SubscribeEvent
    public static void auraEvents(TickEvent.PlayerTickEvent event)
    {
        //regen aura
        PlayerEntity player = event.player;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        float regen = entityStats.getAuraRegeneration();

        if (player.tickCount % 20 == 0 && entityStats.getAura() < entityStats.getMaxAura())
        {
            entityStats.alterAura((int) regen);
        }

    }
}
