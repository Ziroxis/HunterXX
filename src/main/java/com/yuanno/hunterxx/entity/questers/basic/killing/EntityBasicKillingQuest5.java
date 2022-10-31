package com.yuanno.hunterxx.entity.questers.basic.killing;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicKillingQuest5 extends QuesterEntity {
    public EntityBasicKillingQuest5(EntityType type, World world)
    {
        super(type, world);
        this.questList.add(ModQuests.BASICKILLINGQUEST5);
        this.questSpeech = "Could you kill that enderdragon? Would like to see it perish.";
        this.acceptanceSpeech = "Thanks, there will be a good reward at the end don't worry";
        this.decliningSpeech = "Guess it's gonna be a beast hunter then";
        this.ongoingSpeech = "So... When are you done?";
        this.doneSpeech = "Thanks, here is your reward";
    }
}
