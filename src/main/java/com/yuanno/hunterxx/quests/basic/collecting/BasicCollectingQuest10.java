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

public class BasicCollectingQuest10 extends Quest {

    private Objective objective = new ObtainItemObjective("Get the egg", 1, Items.DRAGON_EGG.delegate);

    public BasicCollectingQuest10()
    {
        super("basiccollectingquest10", "Getting the dragon egg");
        this.setDescription("Collect a dragon egg");
        this.setRank(ModValues.B_RANK);
        this.addObjective(this.objective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, Items.DRAGON_EGG, 1))
            return false;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.alterExperience(2000);
        entityStats.alterJenny(2000);
        ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, 2000);
        MinecraftForge.EVENT_BUS.post(eventExperience);

        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));

        return true;
    }
}
