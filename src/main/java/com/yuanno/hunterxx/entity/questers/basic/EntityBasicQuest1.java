package com.yuanno.hunterxx.entity.questers.basic;

import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncQuestDataPacket;
import com.yuanno.hunterxx.networking.server.SSyncTriggerQuest;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class EntityBasicQuest1 extends QuesterEntity {
    public EntityBasicQuest1(EntityType type, World world) {
        super(type, world);
        this.questGiving = ModQuests.BASICQUEST01;
        this.questSpeech = "I'm so hungry... Could you get me some porkchops please?";
        this.acceptanceSpeech = "Please hurry! I'm so hungry!";
        this.decliningSpeech = "I'll starve to death then...";
        this.ongoingSpeech = "almost gottem???";
        this.doneSpeech = "Delicious... I love raw pork chops.";
    }
}
