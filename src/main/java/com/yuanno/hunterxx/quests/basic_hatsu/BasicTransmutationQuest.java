package com.yuanno.hunterxx.quests.basic_hatsu;

import com.yuanno.hunterxx.abilities.advanced.EnAbility;
import com.yuanno.hunterxx.abilities.basic.RenAbility;
import com.yuanno.hunterxx.abilities.basic_hatsu.BasicTransmuterAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.UseContinuousAbilityObjective;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;

public class BasicTransmutationQuest extends Quest {

    private Objective meditationObjective = new UseContinuousAbilityObjective("Use ren for 2 minutes while standing still", 2400, RenAbility.INSTANCE, true);

    public BasicTransmutationQuest()
    {
        super("transmutationquest", "Learning basic transmutation");
        this.setDescription("Use ren for 2 minutes");
        this.setRank("");
        this.addObjective(this.meditationObjective);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(BasicTransmuterAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
