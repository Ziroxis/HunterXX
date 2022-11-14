package com.yuanno.hunterxx.quests.advanced_nen;

import com.yuanno.hunterxx.abilities.advanced.EnAbility;
import com.yuanno.hunterxx.abilities.basic.RenAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.UseContinuousAbilityObjective;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;

public class EnQuest extends Quest {

    private Objective useContinuousAbilityObjective = new UseContinuousAbilityObjective("Use ren for 30 seconds while standing still", 600, RenAbility.INSTANCE, true);
    //private Objective standingStillOBjective = new StandStillObjective("Stand still for 30 seconds", 600);

    public EnQuest()
    {
        super("enquest", "Learning en");
        this.setDescription("Use ren for 30 seconds");
        this.setRank("");
        this.addObjective(this.useContinuousAbilityObjective);
        //this.addObjective(this.standingStillOBjective);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(EnAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
