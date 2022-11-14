package com.yuanno.hunterxx.entity.questers.license;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityLicenseQuest extends QuesterEntity {

    public EntityLicenseQuest(EntityType type, World world)
    {
        super(type, world);
        this.questList.add(ModQuests.HUNTER_LICENSE);
        this.prerequisite = false;
        //this.denialSpeechPreviousQuest = "T?";
        this.questSpeech = "So you want to become a true hunter huh?";
        this.acceptanceSpeech = "Do what I asked and you'll get your hunter license";
        this.decliningSpeech = "The hunter lifestyle ain't made for everyone, can't blame you";
        this.ongoingSpeech = "I see potential in you as a hunter, hopefully you won't disappoint me.";
        this.doneSpeech = "Great job, here is your hunter license. Right click it to make it truly yours.";
    }
}
