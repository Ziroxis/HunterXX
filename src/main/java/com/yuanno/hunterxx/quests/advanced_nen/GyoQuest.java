package com.yuanno.hunterxx.quests.advanced_nen;

import com.yuanno.hunterxx.abilities.advanced.EnAbility;
import com.yuanno.hunterxx.abilities.advanced.GyoAbility;
import com.yuanno.hunterxx.abilities.basic.TenAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.UseContinuousAbilityObjective;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;

public class GyoQuest extends Quest {

    private Objective useContinuousAbilityObjective = new UseContinuousAbilityObjective("Use Ten for 2 minutes", 2400, TenAbility.INSTANCE, false);

    public GyoQuest()
    {
        super("gyoquest", "Learning Gyo");
        this.setDescription("Use Ten for 2 minutes");
        this.setRank("");
        this.addObjective(useContinuousAbilityObjective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(GyoAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
