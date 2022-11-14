package com.yuanno.hunterxx.quests.advanced_nen;

import com.yuanno.hunterxx.abilities.advanced.KenAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.SharedHitChecks;
import com.yuanno.hunterxx.api.Quest.objectives.TakeHitsObjective;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;

public class KenQuest extends Quest {
    private Objective takeHitsObjective = new TakeHitsObjective("Take 30 hits while using ren", 30, SharedHitChecks.HAS_REN);

    public KenQuest()
    {
        super("kenquest", "Learning Ken");
        this.setDescription("Get hit 30 times using ren");
        this.setRank("");
        this.addObjective(takeHitsObjective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(KenAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
