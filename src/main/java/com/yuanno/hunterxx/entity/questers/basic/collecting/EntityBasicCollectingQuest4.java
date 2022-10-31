package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest4 extends QuesterEntity {
    public EntityBasicCollectingQuest4(EntityType type, World world) {
        super(type, world);
        this.questList.add(ModQuests.BASICCOLLECTINGQUEST4);
        this.questSpeech = "I want a weapon, but don't like swords. Can you get me something else?";
        this.acceptanceSpeech = "Please, I want to protect myself from a distance!";
        this.decliningSpeech = "I'll just die then.";
        this.ongoingSpeech = "Almost got it all?";
        this.doneSpeech = "Thanks!";
    }
}
