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

public class BasicCollectingQuest12 extends Quest {

    private Objective objective = new ObtainItemObjective("Get a teleporting item", 10, Items.ENDER_PEARL.delegate);

    public BasicCollectingQuest12()
    {
        super("basiccollectingquest12", "Getting a teleporting item");
        this.setDescription("Collect 10 ender pearls");
        this.setRank(ModValues.C_RANK);
        this.addObjective(this.objective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, Items.ENDER_PEARL, 10))
            return false;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        entityStats.alterExperience(500);
        entityStats.alterJenny(500);
        ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, 500);
        MinecraftForge.EVENT_BUS.post(eventExperience);

        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));

        return true;
    }
}
