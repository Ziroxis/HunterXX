package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest11 extends QuesterEntity {
    public EntityBasicCollectingQuest11(EntityType type, World world) {
        super(type, world);
        this.questGiving = ModQuests.BASICCOLLECTINGQUEST11;
        this.questSpeech = "Could you get a collector some rare star? I see potential in you.";
        this.acceptanceSpeech = "Please hurry! The wedding comes near!";
        this.decliningSpeech = "My wife won't be happy about this...";
        this.ongoingSpeech = "The wedding depends on you my guy";
        this.doneSpeech = "My wife will love this ring embedded with a literal star!";
    }
}
