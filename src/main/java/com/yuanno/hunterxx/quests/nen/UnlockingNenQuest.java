package com.yuanno.hunterxx.quests.nen;

import com.yuanno.hunterxx.abilities.basic.RenAbility;
import com.yuanno.hunterxx.abilities.basic.TenAbility;
import com.yuanno.hunterxx.abilities.basic.ZetsuAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.BrewPotionObjective;
import com.yuanno.hunterxx.api.Quest.objectives.KillEntityObjective;
import com.yuanno.hunterxx.api.Quest.objectives.TimedSurvivalObjective;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.init.ModQuests;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import com.yuanno.hunterxx.networking.client.CSyncentityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

public class UnlockingNenQuest extends Quest {

    private Objective meditateObjective = new TimedSurvivalObjective("Stand still and meditate for 1200 seconds", 1200);
    private Objective killObjective = new KillEntityObjective("Kill 30 entities", 30);
    private Objective brewObjective = new BrewPotionObjective("Brew a invisibility potion", 1, new Effect[] {Effects.INVISIBILITY});

    public UnlockingNenQuest()
    {
        super("basicnenquest", "Becoming a nen user");
        this.addRequirement(ModQuests.HUNTER_LICENSE);
        this.setDescription("These quests help you learn new abilities");
        this.setRank("");
        this.addObjective(meditateObjective);
        this.addObjective(killObjective);
        this.addObjective(brewObjective);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IEntityStats entityStats = EntityStatsCapability.get(player);
        abilityData.addUnlockedAbility(TenAbility.INSTANCE);
        abilityData.addUnlockedAbility(RenAbility.INSTANCE);
        abilityData.addUnlockedAbility(ZetsuAbility.INSTANCE);
        entityStats.setHasNen(true);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));

        return true;
    }
}
