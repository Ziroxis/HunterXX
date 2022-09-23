package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest9 extends QuesterEntity {
    public EntityBasicCollectingQuest9(EntityType type, World world) {
        super(type, world);
        this.questGiving = ModQuests.BASICCOLLECTINGQUEST9;
        this.questSpeech = "Always wished to fly... Could you get me wings!";
        this.acceptanceSpeech = "Finally! Someone willing to do this";
        this.decliningSpeech = "Are there 'wing' hunters or something I could hire?";
        this.ongoingSpeech = "Gottem? I want to fly...";
        this.doneSpeech = "I'll finally be able to roleplay Icarus.";
    }
}
