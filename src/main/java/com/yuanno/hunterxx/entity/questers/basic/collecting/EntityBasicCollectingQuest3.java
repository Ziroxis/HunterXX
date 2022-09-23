package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest3 extends QuesterEntity {
    public EntityBasicCollectingQuest3(EntityType type, World world) {
        super(type, world);
        this.questGiving = ModQuests.BASICCOLLECTINGQUEST3;
        this.questSpeech = "I need to be able to protect myself, can you get me a sword?";
        this.acceptanceSpeech = "It's my right to bear arms! Please hurry.";
        this.decliningSpeech = "My fifth amendement!";
        this.ongoingSpeech = "When is it done?";
        this.doneSpeech = "Thanks! I can finally teach these ki- I mean monsters a lesson.";
    }
}
