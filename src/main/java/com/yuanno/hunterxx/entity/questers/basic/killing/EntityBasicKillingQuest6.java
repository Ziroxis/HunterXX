package com.yuanno.hunterxx.entity.questers.basic.killing;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicKillingQuest6 extends QuesterEntity {
    public EntityBasicKillingQuest6(EntityType type, World world)
    {
        super(type, world);
        this.questGiving = ModQuests.BASICKILLINGQUEST6;
        this.questSpeech = "Could you kill the wither for me? I don't like his head. I mean heads..";
        this.acceptanceSpeech = "Thanks, won't have to see it anymore";
        this.decliningSpeech = "Beast hunter it is";
        this.ongoingSpeech = "So, how is the head hunting going";
        this.doneSpeech = "Thanks, here is your reward";
    }
}
