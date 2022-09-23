package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest7 extends QuesterEntity {
    public EntityBasicCollectingQuest7(EntityType type, World world) {
        super(type, world);
        this.questGiving = ModQuests.BASICCOLLECTINGQUEST7;
        this.questSpeech = "I'm a collector, but professional hunters cost so much... Could you get me something?";
        this.acceptanceSpeech = "Thanks! I'll pay you good.";
        this.decliningSpeech = "Guess I'll have to get a professional hunter then.";
        this.ongoingSpeech = "Where is it?";
        this.doneSpeech = "This fits perfect in my question.";
    }
}
