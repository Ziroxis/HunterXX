package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest8 extends QuesterEntity {
    public EntityBasicCollectingQuest8(EntityType type, World world) {
        super(type, world);
        this.questGiving = ModQuests.BASICCOLLECTINGQUEST8;
        this.questSpeech = "I'd like to eat a legendary apple, could you get me it?";
        this.acceptanceSpeech = "Take your time.";
        this.decliningSpeech = "Guess it's gonna have to be a professional herbal hunter then";
        this.ongoingSpeech = "You can take your time, it'll be worth it.";
        this.doneSpeech = "Delicious...";
    }
}
