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

public class BasicCollectingQuest06 extends Quest {

    private Objective objective = new ObtainItemObjective("Get some phantom membrane", 10, Items.PHANTOM_MEMBRANE.delegate);

    public BasicCollectingQuest06()
    {
        super("basiccollectingquest06", "Getting some membrane");
        this.setDescription("Collect 10 phantom membrane");
        this.setRank(ModValues.D_RANK);
        this.addObjective(this.objective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, Items.PHANTOM_MEMBRANE, 10))
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
