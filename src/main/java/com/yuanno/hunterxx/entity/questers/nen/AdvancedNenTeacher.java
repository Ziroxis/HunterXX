package com.yuanno.hunterxx.entity.questers.nen;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class AdvancedNenTeacher extends QuesterEntity {

    public AdvancedNenTeacher(EntityType type, World world)
    {
        super(type, world);
        this.questList.add(ModQuests.GYO_QUEST);
        this.questList.add(ModQuests.IN_QUEST);
        this.questList.add(ModQuests.KO_QUEST);
        this.questList.add(ModQuests.EN_QUEST);
        this.questList.add(ModQuests.SHU_QUEST);
        this.questList.add(ModQuests.RYU_KEN_QUEST);
        this.questPrerequisite = ModQuests.UNLOCK_NEN;

        this.prerequisite = true;

        this.denialSpeechPreviousQuest = "You should maybe first learn the basics no?";
        this.questSpeech = "You're interested in learning advanced nen stuff??";
        this.acceptanceSpeech = "Go follow my teachings";
        this.decliningSpeech = "Strength is not made for everyone...";
        this.ongoingSpeech = "Come back when you've finished my teachings";
        this.doneSpeech = "My teachings end here young student.";

    }
}
