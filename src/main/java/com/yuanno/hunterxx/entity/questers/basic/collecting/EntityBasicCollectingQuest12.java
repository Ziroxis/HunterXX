package com.yuanno.hunterxx.entity.questers.basic.collecting;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicCollectingQuest12 extends QuesterEntity {
    public EntityBasicCollectingQuest12(EntityType type, World world) {
        super(type, world);
        this.questGiving = ModQuests.BASICCOLLECTINGQUEST12;
        this.questSpeech = "Always been a fan of Minato... Would love to teleport, could you do that for me?";
        this.acceptanceSpeech = "My Minato roleplay will be perfect!";
        this.decliningSpeech = "RIP my cosplay for the upcoming convention";
        this.ongoingSpeech = "Time isn't eternal, my love for 2D girls is though.";
        this.doneSpeech = "Thanks! The girls will love this cosplay!";
    }
}
