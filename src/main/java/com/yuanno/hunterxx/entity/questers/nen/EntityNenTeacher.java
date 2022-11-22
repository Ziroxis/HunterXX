package com.yuanno.hunterxx.entity.questers.nen;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityNenTeacher extends QuesterEntity {

    public EntityNenTeacher(EntityType type, World world)
    {
        super(type, world);
        this.questList.add(ModQuests.UNLOCK_NEN);
        this.prerequisite = true;
        this.questPrerequisite = ModQuests.HUNTER_LICENSE;
        this.denialSpeechPreviousQuest = "You should first pass the hunter exam bozu";
        this.questSpeech = "You're interested in learning something new kid?";
        this.acceptanceSpeech = "Do what I asked and you'll be on your way to master a new kind of strength";
        this.decliningSpeech = "I gues not everyone is interested in strength";
        this.ongoingSpeech = "Come back when you're done with what I asked";
        this.doneSpeech = "My teachings end here younging.";
    }
}
