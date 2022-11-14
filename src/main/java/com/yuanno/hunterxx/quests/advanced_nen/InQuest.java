package com.yuanno.hunterxx.quests.advanced_nen;

import com.yuanno.hunterxx.abilities.advanced.InAbiltiy;
import com.yuanno.hunterxx.abilities.basic.ZetsuAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.UseContinuousAbilityObjective;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;

public class InQuest extends Quest {

    private Objective useContinuousAbilityObjective = new UseContinuousAbilityObjective("Use zetsu standing still for 30 seconds", 600, ZetsuAbility.INSTANCE, true);

    public InQuest()
    {
        super("inquest", "Learning In");
        this.setDescription("Use In for 30 seconds");
        this.setRank("");
        this.addObjective(useContinuousAbilityObjective);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(InAbiltiy.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
