package com.yuanno.hunterxx.quests.basic_hatsu;

import com.yuanno.hunterxx.abilities.advanced.KoAbility;
import com.yuanno.hunterxx.abilities.basic_hatsu.BasicEmissionAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.HitEntityObjective;
import com.yuanno.hunterxx.api.Quest.objectives.SharedHitChecks;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;

public class BasicEmissionQuest extends Quest {

    private Objective hitEntityObjective = new HitEntityObjective("Hit 50 entities with a bow and ren", 50, SharedHitChecks.HAS_BOW.and(SharedHitChecks.HAS_REN));

    public BasicEmissionQuest()
    {
        super("emissionquest", "Learning basic emission");
        this.setDescription("Hit 50 entities with a bow and ren");
        this.setRank("");
        this.addObjective(hitEntityObjective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(BasicEmissionAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
