package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest2 extends QuesterEntity {
    public EntityBasicCollectingQuest2(EntityType type, World world) {
        super(type, world);
        this.questGiving = ModQuests.BASICCOLLECTINGQUEST2;
        this.questSpeech = "I'd like to make bread... Could you get me wheat?";
        this.acceptanceSpeech = "You can take your time, but not too much";
        this.decliningSpeech = "Guess I'll find someone else..";
        this.ongoingSpeech = "Almost got it?";
        this.doneSpeech = "Thanks! This will make delicious bread.";
    }
}
