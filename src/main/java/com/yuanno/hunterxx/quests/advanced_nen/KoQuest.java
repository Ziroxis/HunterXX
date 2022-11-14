package com.yuanno.hunterxx.quests.advanced_nen;

import com.yuanno.hunterxx.abilities.advanced.KoAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.HitEntityObjective;
import com.yuanno.hunterxx.api.Quest.objectives.SharedHitChecks;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;

public class KoQuest extends Quest {

    private Objective hitEntityObjective = new HitEntityObjective("Hit 20 entities with ren", 20, SharedHitChecks.HAS_REN);

    public KoQuest()
    {
        super("koquest", "Learning Ko");
        this.setDescription("Use ren and hit 20 entities");
        this.setRank("");
        this.addObjective(hitEntityObjective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(KoAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
