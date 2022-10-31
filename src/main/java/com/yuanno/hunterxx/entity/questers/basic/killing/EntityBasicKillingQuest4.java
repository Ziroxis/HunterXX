package com.yuanno.hunterxx.entity.questers.basic.killing;

import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityBasicKillingQuest4 extends QuesterEntity {
    public EntityBasicKillingQuest4(EntityType type, World world)
    {
        super(type, world);
        this.questList.add(ModQuests.BASICKILLINGQUEST4);
        this.questSpeech = "I keep getting poisoned by these damn witches, can you take care of them?";
        this.acceptanceSpeech = "Thanks, good luck with it by the way";
        this.decliningSpeech = "I'll have to find an antidote then for when I'm poisoned again";
        this.ongoingSpeech = "How is it going?";
        this.doneSpeech = "Big thanks man!";
    }
}
