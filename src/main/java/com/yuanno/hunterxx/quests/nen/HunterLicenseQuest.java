package com.yuanno.hunterxx.quests.nen;

import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.objectives.BrewPotionObjective;
import com.yuanno.hunterxx.api.Quest.objectives.ObtainItemObjective;
import com.yuanno.hunterxx.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

import java.util.function.Predicate;

public class HunterLicenseQuest extends Quest {
    private Objective obtainFur = new ObtainItemObjective("Obtain 3 foxbear fur", 3, ModItems.FOXBEAR_FUR);
    private Objective healingPotion = new BrewPotionObjective("Brew a healing potion", 1, new Effect[] {Effects.HEAL});
    private Objective obtainHeart = new ObtainItemObjective("Obtain a heart of the sea", 1, Items.HEART_OF_THE_SEA.delegate);

    public HunterLicenseQuest()
    {
        super("hunterlicensequest", "Becoming a hunter");
        this.setDescription("Do what I asked you to do and you'll pass the hunter exam");
        this.setRank("");
        this.addObjective(obtainFur);
        this.addObjective(healingPotion);
        this.addObjective(obtainHeart);

        this.onCompleteEvent = this::giveReward;
    }

    public boolean giveReward(PlayerEntity player)
    {
        if (!this.removeQuestItem(player, ModItems.FOXBEAR_FUR.get(), 3))
            return false;
        if (!this.removeQuestItem(player, Items.HEART_OF_THE_SEA, 1))
            return false;
        if (!this.addQuestItem(player, ModItems.HUNTER_LICENSE.get(), 1))
            return false;
        return true;
    }
}
