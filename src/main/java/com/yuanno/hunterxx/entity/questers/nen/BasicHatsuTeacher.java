package com.yuanno.hunterxx.entity.questers.nen;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import com.yuanno.hunterxx.quests.basic_hatsu.BasicEnhancementQuest;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class BasicHatsuTeacher extends QuesterEntity {

    public BasicHatsuTeacher(EntityType type, World world)
    {
        super(type, world);
        this.questList.add(ModQuests.BASIC_ENHANCEMENT);
        this.questList.add(ModQuests.BASIC_EMISSION);
        this.questList.add(ModQuests.BASIC_TRANSMUTATION);
        this.questList.add(ModQuests.BASIC_CONJURATION);
        this.questList.add(ModQuests.BASIC_MANIPULATION);
        this.questPrerequisite = ModQuests.UNLOCK_NEN;

        this.prerequisite = true;

        this.denialSpeechPreviousQuest = "Shouldn't you at least learn the basics?";
        this.questSpeech = "I see potential in you, would you want to unlock it?";
        this.acceptanceSpeech = "I gave you training regiment to unlock part of your potential";
        this.decliningSpeech = "Understandable, your own potential can be scary";
        this.ongoingSpeech = "Almost done with my regiment?";
        this.doneSpeech = "The next step is to unlock your own specific potential...";
    }
}
