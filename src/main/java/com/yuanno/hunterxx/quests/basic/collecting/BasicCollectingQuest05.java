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

public class BasicCollectingQuest05 extends Quest {

    private Objective objective = new ObtainItemObjective("Get a diamond", 3, Items.DIAMOND.delegate);

    public BasicCollectingQuest05()
    {
        super("basiccollectingquest01", "Getting diamonds");
        this.setDescription("Collect 3 diamonds");
        this.setRank(ModValues.D_RANK);
        this.addObjective(this.objective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, Items.DIAMOND, 3))
            return false;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.alterExperience(300);
        entityStats.alterJenny(150);
        ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, 300);
        MinecraftForge.EVENT_BUS.post(eventExperience);

        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));

        return true;
    }
}
