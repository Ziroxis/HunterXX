package com.yuanno.hunterxx.quests.basic_hatsu;

import com.yuanno.hunterxx.abilities.basic_hatsu.BasicManipulationAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.HitEntityObjective;
import com.yuanno.hunterxx.api.Quest.objectives.SharedHitChecks;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;

public class BasicManipulationQuest extends Quest {

    private Objective hitEntityObjective = new HitEntityObjective("hit 100 enemies with ren", 100, SharedHitChecks.HAS_REN);
    //private Objective standingStillOBjective = new StandStillObjective("Stand still for 30 seconds", 600);

    public BasicManipulationQuest()
    {
        super("basicmanipulationquest", "Learning basic manipulation");
        this.setDescription("Hit 100 entities with ren");
        this.setRank("");
        this.addObjective(this.hitEntityObjective);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(BasicManipulationAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
