package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import com.yuanno.hunterxx.quests.basic.collecting.BasicCollectingQuest02;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest1 extends QuesterEntity {
    public EntityBasicCollectingQuest1(EntityType type, World world) {
        super(type, world);
        this.questList.add(ModQuests.BASICCOLLECTINGQUEST1);
        this.questList.add(ModQuests.BASICCOLLECTINGQUEST2);
        //this.questGiving = ModQuests.BASICCOLLECTINGQUEST1;
        this.questSpeech = "I'm so hungry... Could you get me some porkchops please?";
        this.acceptanceSpeech = "Please hurry! I'm so hungry!";
        this.decliningSpeech = "I'll starve to death then...";
        this.ongoingSpeech = "almost gottem???";
        this.doneSpeech = "Delicious... I love raw pork chops.";
    }
}
