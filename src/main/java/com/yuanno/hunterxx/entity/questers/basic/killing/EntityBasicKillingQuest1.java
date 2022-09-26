package com.yuanno.hunterxx.entity.questers.basic.killing;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicKillingQuest1 extends QuesterEntity {

    public EntityBasicKillingQuest1(EntityType type, World world)
    {
        super(type, world);
        this.questGiving = ModQuests.BASICKILLINGQUEST1;
        this.questSpeech = "Could you kill me zombies? They're so annoying...";
        this.acceptanceSpeech = "Finally a worthy younging helping me";
        this.decliningSpeech = "Guess, imma have to hire a hunter";
        this.ongoingSpeech = "Let me know when you killed a few, will give you a reward";
        this.doneSpeech = "Thanks, will finally be able to do night walks again";
    }
}
