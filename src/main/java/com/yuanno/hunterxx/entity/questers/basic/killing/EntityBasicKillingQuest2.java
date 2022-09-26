package com.yuanno.hunterxx.entity.questers.basic.killing;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicKillingQuest2 extends QuesterEntity {
    public EntityBasicKillingQuest2(EntityType type, World world)
    {
        super(type, world);
        this.questGiving = ModQuests.BASICKILLINGQUEST2;
        this.questSpeech = "I'm too weak for those long folks, but they keep taking pieces of my house!";
        this.acceptanceSpeech = "My house will finally have a shot at not breaking for no reason";
        this.decliningSpeech = "Guess I'll have to gear up myself";
        this.ongoingSpeech = "So how is it going?";
        this.doneSpeech = "Finally, my house will be left alone";
    }
}
