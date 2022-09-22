package com.yuanno.hunterxx.init;

import com.yuanno.hunterxx.networking.AuraSync;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.*;
import com.yuanno.hunterxx.networking.server.*;

public class ModNetwork {

    public static void init()
    {
        //CLIENT PACKETS
        PacketHandler.registerPacket(COpenOverviewScreenPacket.class, COpenOverviewScreenPacket::encode, COpenOverviewScreenPacket::decode, COpenOverviewScreenPacket::handle);
        PacketHandler.registerPacket(CRequestSyncWorldDataPacket.class, CRequestSyncWorldDataPacket::encode, CRequestSyncWorldDataPacket::decode, CRequestSyncWorldDataPacket::handle);
        PacketHandler.registerPacket(CStatsCCPacket.class, CStatsCCPacket::encode, CStatsCCPacket::decode, CStatsCCPacket::handle);
        PacketHandler.registerPacket(CAttributePacket.class, CAttributePacket::encode, CAttributePacket::decode, CAttributePacket::handle);
        PacketHandler.registerPacket(CGiveItemStackPacket.class, CGiveItemStackPacket::encode, CGiveItemStackPacket::decode, CGiveItemStackPacket::handle);
        PacketHandler.registerPacket(CAbandonQuestPacket.class, CAbandonQuestPacket::encode, CAbandonQuestPacket::decode, CAbandonQuestPacket::handle);
        PacketHandler.registerPacket(CRequestSyncQuestDataPacket.class, CRequestSyncQuestDataPacket::encode, CRequestSyncQuestDataPacket::decode, CRequestSyncQuestDataPacket::handle);
        PacketHandler.registerPacket(CStartObjectiveEventPacket.class, CStartObjectiveEventPacket::encode, CStartObjectiveEventPacket::decode, CStartObjectiveEventPacket::handle);
        PacketHandler.registerPacket(CSyncQuestDataPacket.class, CSyncQuestDataPacket::encode, CSyncQuestDataPacket::decode, CSyncQuestDataPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsPacket.class, CSyncentityStatsPacket::encode, CSyncentityStatsPacket::decode, CSyncentityStatsPacket::handle);
        PacketHandler.registerPacket(CEquipAbilityPacket.class, CEquipAbilityPacket::encode, CEquipAbilityPacket::decode, CEquipAbilityPacket::handle);
        PacketHandler.registerPacket(CRemoveAbilityPacket.class, CRemoveAbilityPacket::encode, CRemoveAbilityPacket::decode, CRemoveAbilityPacket::handle);
        PacketHandler.registerPacket(CSyncAbilityDataPacket.class, CSyncAbilityDataPacket::encode, CSyncAbilityDataPacket::decode, CSyncAbilityDataPacket::handle);
        PacketHandler.registerPacket(CToggleCombatModePacket.class, CToggleCombatModePacket::encode, CToggleCombatModePacket::decode, CToggleCombatModePacket::handle);
        PacketHandler.registerPacket(CTogglePassiveAbilityPacket.class, CTogglePassiveAbilityPacket::encode, CTogglePassiveAbilityPacket::decode, CTogglePassiveAbilityPacket::handle);
        PacketHandler.registerPacket(CUseAbilityPacket.class, CUseAbilityPacket::encode, CUseAbilityPacket::decode, CUseAbilityPacket::handle);


        //SERVER PACKETS
        PacketHandler.registerPacket(SSyncEntityStatsPacket.class, SSyncEntityStatsPacket::encode, SSyncEntityStatsPacket::decode, SSyncEntityStatsPacket::handle);
        PacketHandler.registerPacket(SOpenOverviewScreenPacket.class, SOpenOverviewScreenPacket::encode, SOpenOverviewScreenPacket::decode, SOpenOverviewScreenPacket::handle);
        PacketHandler.registerPacket(CRequestSyncWorldDataPacket.class, CRequestSyncWorldDataPacket::encode, CRequestSyncWorldDataPacket::decode, CRequestSyncWorldDataPacket::handle);
        PacketHandler.registerPacket(SSyncQuestDataPacket.class, SSyncQuestDataPacket::encode, SSyncQuestDataPacket::decode, SSyncQuestDataPacket::handle);
        PacketHandler.registerPacket(SSyncTriggerQuest.class, SSyncTriggerQuest::encode, SSyncTriggerQuest::decode, SSyncTriggerQuest::handle);
        PacketHandler.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
        PacketHandler.registerPacket(SUpdateEquippedAbilityPacket.class, SUpdateEquippedAbilityPacket::encode, SUpdateEquippedAbilityPacket::decode, SUpdateEquippedAbilityPacket::handle);
        PacketHandler.registerPacket(SSyncWorldDataPacket.class, SSyncWorldDataPacket::encode, SSyncWorldDataPacket::decode, SSyncWorldDataPacket::handle);
        PacketHandler.registerPacket(SOpenChatPromptScreenPacket.class, SOpenChatPromptScreenPacket::encode, SOpenChatPromptScreenPacket::decode, SOpenChatPromptScreenPacket::handle);


        PacketHandler.registerPacket(AuraSync.class, AuraSync::encode, AuraSync::decode, AuraSync::handle);

    }
}
