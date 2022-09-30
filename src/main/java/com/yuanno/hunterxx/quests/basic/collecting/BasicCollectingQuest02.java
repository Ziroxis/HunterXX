package com.yuanno.hunterxx.quests.basic.collecting;

import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.events.levels.ExperienceUpEvent;
import com.yuanno.hunterxx.init.ModValues;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncentityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;

public class BasicCollectingQuest02 extends Quest {

    private Objective objective = new ObtainItemObjective("Get some wheat", 30, Items.WHEAT.delegate);

    public BasicCollectingQuest02()
    {
        super("basiccollectingquest02", "Getting some wheat");
        this.setDescription("Collect 30 wheat");
        this.setRank(ModValues.E_RANK);
        this.addObjective(this.objective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, Items.WHEAT, 30))
            return false;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.alterExperience(100);
        entityStats.alterJenny(15);
        ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, 100);
        MinecraftForge.EVENT_BUS.post(eventExperience);

        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));

        return true;
    }
}
