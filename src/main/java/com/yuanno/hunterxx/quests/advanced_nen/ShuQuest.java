package com.yuanno.hunterxx.quests.advanced_nen;

import com.yuanno.hunterxx.abilities.advanced.ShuAbility;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.BreakBlockObjective;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CSyncAbilityDataPacket;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class ShuQuest extends Quest {

    private static final BreakBlockObjective.IBreakBlock ITEM_CHECK = (player, count, block) ->
    {
        return player.getMainHandItem().getItem().equals(Items.IRON_SHOVEL);
    };

    private Objective breakObjective = new BreakBlockObjective("Break 100 stone", 100, Blocks.STONE, ITEM_CHECK);

    public ShuQuest() {
        super("shuquest", "Learning shu");
        this.setDescription("Break 100 stone with an iron shovel");
        this.setRank("");
        this.addObjective(this.breakObjective);
        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.addUnlockedAbility(ShuAbility.INSTANCE);
        PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));

        return true;
    }
}
