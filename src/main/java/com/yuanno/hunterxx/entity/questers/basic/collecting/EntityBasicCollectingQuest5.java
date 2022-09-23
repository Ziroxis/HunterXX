package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest5 extends QuesterEntity {
    public EntityBasicCollectingQuest5(EntityType type, World world) {
        super(type, world);
        this.questGiving = ModQuests.BASICCOLLECTINGQUEST5;
        this.questSpeech = "I'm a mineral hunter, could you get me diamonds so I can research them?";
        this.acceptanceSpeech = "Thanks, won't have to do it myself.";
        this.decliningSpeech = "I'll get them myself then I guess.";
        this.ongoingSpeech = "Almost got it or am I gonna have to do it myself?";
        this.doneSpeech = "Thanks! You'd be a great hunter.";
    }
}
