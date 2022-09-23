package com.yuanno.hunterxx.quests.basic.killing;

import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.KillEntityObjective;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.events.levels.ExperienceUpEvent;
import com.yuanno.hunterxx.init.ModValues;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncentityStatsPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;

public class BasicKillingQuest05 extends Quest {

    private static final KillEntityObjective.ICheckKill TARGET_CHECK = (player, target, source) ->
    {
        return target.getType() == EntityType.ENDER_DRAGON;
    };

    private Objective killObjective = new KillEntityObjective("Kill the enderdragon", 1, TARGET_CHECK);

    public BasicKillingQuest05()
    {
        super("basickillingquest05", "Kill the enderdragon");
        this.setDescription("Kill the enderdragon");
        this.setRank(ModValues.B_RANK);
        this.addObjectives(this.killObjective);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.alterExperience(1500);
        entityStats.alterJenny(1500);
        ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, 1500);
        MinecraftForge.EVENT_BUS.post(eventExperience);

        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));

        return true;
    }
}
