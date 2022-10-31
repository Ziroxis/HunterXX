package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest6 extends QuesterEntity {
    public EntityBasicCollectingQuest6(EntityType type, World world) {
        super(type, world);
        this.questList.add(ModQuests.BASICCOLLECTINGQUEST6);
        this.questSpeech = "I'm a sickness hunter, phantom membrane could be a cure for something. Could you get me some?";
        this.acceptanceSpeech = "Thanks! Won't have to hire a professional beast hunter.";
        this.decliningSpeech = "I'll hire a professional beast hunter then.";
        this.ongoingSpeech = "Let me know when you gottem.";
        this.doneSpeech = "Big thanks! Will be able to work on my cure now.";
    }
}
