package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest10 extends QuesterEntity {
    public EntityBasicCollectingQuest10(EntityType type, World world) {
        super(type, world);
        this.questGiving = ModQuests.BASICCOLLECTINGQUEST10;
        this.questSpeech = "I'm a gourmet hunger, could you get me the legendary egg so I can make myself an omelette?";
        this.acceptanceSpeech = "Please hurry! I'm so hungry for something legendary!";
        this.decliningSpeech = "AAAAH, I HATE beast hunters... I don't want to hire any!";
        this.ongoingSpeech = "Where is it????";
        this.doneSpeech = "Delicious... I love myself some legendary dragon omelette.";
    }
}
