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

public class BasicKillingQuest04 extends Quest {

    private static final KillEntityObjective.ICheckKill TARGET_CHECK = (player, target, source) ->
    {
        return target.getType() == EntityType.WITCH;
    };

    private Objective killObjective = new KillEntityObjective("Kill 3 witches", 3, TARGET_CHECK);

    public BasicKillingQuest04()
    {
        super("basickillingquest04", "Killing witches");
        this.setDescription("Kill 3 witches");
        this.setRank(ModValues.D_RANK);
        this.addObjectives(this.killObjective);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.alterExperience(300);
        entityStats.alterJenny(100);
        ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, 300);
        MinecraftForge.EVENT_BUS.post(eventExperience);

        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));

        return true;
    }
}
