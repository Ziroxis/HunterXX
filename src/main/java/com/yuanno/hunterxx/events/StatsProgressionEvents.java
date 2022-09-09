package com.yuanno.hunterxx.events;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.events.levels.ExperienceUpEvent;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class StatsProgressionEvents {
    public static UUID attack_attributemodifier_uuid = UUID.fromString("aa18be1b-b1d0-4917-a168-42dd240d000d");
    public static UUID defense_attributemodifier_uuid = UUID.fromString("806281d3-bb17-4b3f-8142-f03f077ba2e2");
    public static UUID health_attributemodifier_uuid = UUID.fromString("135d510d-26c6-403e-8615-899862332e86");
    public static UUID speed_attributemodifier_uuid = UUID.fromString("69082b90-7357-407c-9e82-7852b6925932");
    @SubscribeEvent
    public static void killingEntityEvent(LivingDeathEvent event)
    {
        if (event.getSource().getEntity() instanceof PlayerEntity)
        {
            PlayerEntity attacker = (PlayerEntity) event.getSource().getEntity();
            IEntityStats entityStats = EntityStatsCapability.get(attacker);
            entityStats.alterExperience(15);
            ExperienceUpEvent experienceUpEvent = new ExperienceUpEvent(attacker, entityStats.getExperience());
            if (MinecraftForge.EVENT_BUS.post(experienceUpEvent))
                return;
            PacketHandler.sendTo(new SSyncEntityStatsPacket(attacker.getId(), entityStats), attacker);
            System.out.println(entityStats.getLevel());
            System.out.println(entityStats.getLevelPoints());
            System.out.println(entityStats.getExperience());
            System.out.println(entityStats.getMaxExperience());
        }
    }

    /*
    @SubscribeEvent
    public static void tickEvent(LivingEvent.LivingUpdateEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;
        AttributeModifier healthAttribute;
        AttributeModifier strengthAttribute;
        AttributeModifier defenseAttribute;
        AttributeModifier speedAttribute;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        IEntityStats stats = EntityStatsCapability.get(player);
        int healthStat = stats.getHealth();
        int strengthStat = stats.getStrength();
        int defenseStat = stats.getDefense();
        int speedStat = stats.getSpeed();

        healthAttribute = new AttributeModifier(health_attributemodifier_uuid, "healthIncrease", 5, AttributeModifier.Operation.fromValue())
    }

     */
}
