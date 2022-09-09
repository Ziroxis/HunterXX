package com.yuanno.hunterxx.events.levels;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ExperienceEvents {

    @SubscribeEvent
    public static void onLevelUp(ExperienceUpEvent e)
    {
        PlayerEntity player = e.getPlayer();
        IEntityStats statsProps = EntityStatsCapability.get(player);

        if (statsProps.getExperience() >= statsProps.getMaxExperience() && statsProps.getLevel() < 50)
        {
            int currentExperience = statsProps.getExperience();
            int currentMaxExperience = statsProps.getMaxExperience();
            statsProps.alterLevel(1);
            statsProps.alterLevelPoints(1);
            LevelUpEvent eventLevelUp = new LevelUpEvent(player, statsProps.getLevel());
            if (MinecraftForge.EVENT_BUS.post(eventLevelUp))
                return;
            statsProps.alterMaxEperience(50);
            MaxExperienceUpEvent eventMaxExperienceUp = new MaxExperienceUpEvent(player, statsProps.getMaxExperience());
            if (MinecraftForge.EVENT_BUS.post(eventMaxExperienceUp))
                return;
            statsProps.setExperience(currentExperience - currentMaxExperience);
            ExperienceUpEvent eventExperienceUp = new ExperienceUpEvent(player, statsProps.getExperience());
            if (MinecraftForge.EVENT_BUS.post(eventExperienceUp))
                return;

            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), statsProps), player);
            //player.sendMessage(new StringTextComponent("You leveled up to level " + statsProps.getLevel() + "!"), player.getUUID());
        }
    }
}
