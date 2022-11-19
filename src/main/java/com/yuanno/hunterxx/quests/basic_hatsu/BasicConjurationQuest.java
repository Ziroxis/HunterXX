package com.yuanno.hunterxx.quests.basic_hatsu;

import com.yuanno.hunterxx.abilities.advanced.EnAbility;
import com.yuanno.hunterxx.abilities.basic_hatsu.BasicConjurerAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class BasicConjurationQuest extends Quest {

    private Objective objective = new ObtainItemObjective("Get 3 diamond swords", 3, Items.DIAMOND_SWORD.delegate);

    public BasicConjurationQuest()
    {
        super("basicconjurationquest", "Learning basic conjuration");
        this.setDescription("Get 3 diamond swords");
        this.setRank("");
        this.addObjective(this.objective);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(BasicConjurerAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
        return true;
    }
}
