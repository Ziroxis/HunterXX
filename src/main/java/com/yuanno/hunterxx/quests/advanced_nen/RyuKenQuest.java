package com.yuanno.hunterxx.quests.advanced_nen;

import com.yuanno.hunterxx.abilities.advanced.RyuAbility;
import com.yuanno.hunterxx.abilities.advanced.ShuAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.HitEntityObjective;
import com.yuanno.hunterxx.api.Quest.objectives.SharedHitChecks;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;

public class RyuKenQuest extends Quest {

    private Objective hitEntityObjective = new HitEntityObjective("Hit 30 entities with ko", 30, SharedHitChecks.HAS_KO);

    public RyuKenQuest()
    {
        super("ryuken", "Learning Ryu and Ken");
        this.setDescription("Use Ko and hit 30 entities");
        this.setRank("");
        this.addObjective(hitEntityObjective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(RyuAbility.INSTANCE);
        abilityData.addUnlockedAbility(ShuAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }

}
