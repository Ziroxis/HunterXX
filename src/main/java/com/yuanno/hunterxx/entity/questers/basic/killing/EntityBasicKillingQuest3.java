package com.yuanno.hunterxx.entity.questers.basic.killing;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicKillingQuest3 extends QuesterEntity {
    public EntityBasicKillingQuest3(EntityType type, World world)
    {
        super(type, world);
        this.questGiving = ModQuests.BASICKILLINGQUEST3;
        this.questSpeech = "Can't reach those damn skelly things! Could you take care of them for me please?";
        this.acceptanceSpeech = "Thanks, let me know when you're done";
        this.decliningSpeech = "I'll take up my bow and do it myself!";
        this.ongoingSpeech = "Can you reach them? Let me know when you're done";
        this.doneSpeech = "Finally! Thanks!!!";
    }
}
