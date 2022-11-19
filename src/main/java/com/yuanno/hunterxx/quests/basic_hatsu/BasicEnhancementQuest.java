package com.yuanno.hunterxx.quests.basic_hatsu;

import com.yuanno.hunterxx.abilities.advanced.KoAbility;
import com.yuanno.hunterxx.abilities.basic_hatsu.BasicConjurerAbility;
import com.yuanno.hunterxx.abilities.basic_hatsu.BasicEnhancerAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.HitEntityObjective;
import com.yuanno.hunterxx.api.Quest.objectives.SharedHitChecks;
import com.yuanno.hunterxx.api.Quest.objectives.TakeHitsObjective;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;

public class BasicEnhancementQuest extends Quest {

    private Objective hitEntityObjective = new HitEntityObjective("Hit 50 entities with ko", 50, SharedHitChecks.HAS_KO);
    private Objective getHitEntityObjective = new TakeHitsObjective("Take 50 hits with 50", 50, SharedHitChecks.HAS_KO);

    public BasicEnhancementQuest()
    {
        super("enhancementquest", "Learning basic enhancement");
        this.setDescription("Hit 50 entities and take 50 hits with ko");
        this.setRank("");
        this.addObjective(hitEntityObjective);
        this.addObjective(getHitEntityObjective);
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(BasicEnhancerAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
