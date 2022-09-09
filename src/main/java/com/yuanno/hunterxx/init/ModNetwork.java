package com.yuanno.hunterxx.init;

import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CAttributePacket;
import com.yuanno.hunterxx.networking.client.COpenOverviewScreenPacket;
import com.yuanno.hunterxx.networking.client.CRequestSyncWorldDataPacket;
import com.yuanno.hunterxx.networking.client.CStatsCCPacket;
import com.yuanno.hunterxx.networking.server.SOpenOverviewScreenPacket;
import com.yuanno.hunterxx.networking.server.SSyncEntityStatsPacket;
import com.yuanno.hunterxx.networking.server.SSyncWorldDataPacket;

public class ModNetwork {

    public static void init()
    {
        //CLIENT PACKETS
        PacketHandler.registerPacket(COpenOverviewScreenPacket.class, COpenOverviewScreenPacket::encode, COpenOverviewScreenPacket::decode, COpenOverviewScreenPacket::handle);
        PacketHandler.registerPacket(CRequestSyncWorldDataPacket.class, CRequestSyncWorldDataPacket::encode, CRequestSyncWorldDataPacket::decode, CRequestSyncWorldDataPacket::handle);
        PacketHandler.registerPacket(SSyncWorldDataPacket.class, SSyncWorldDataPacket::encode, SSyncWorldDataPacket::decode, SSyncWorldDataPacket::handle);
        PacketHandler.registerPacket(CStatsCCPacket.class, CStatsCCPacket::encode, CStatsCCPacket::decode, CStatsCCPacket::handle);
        PacketHandler.registerPacket(CAttributePacket.class, CAttributePacket::encode, CAttributePacket::decode, CAttributePacket::handle);

        //SERVER PACKETS
        PacketHandler.registerPacket(SSyncEntityStatsPacket.class, SSyncEntityStatsPacket::encode, SSyncEntityStatsPacket::decode, SSyncEntityStatsPacket::handle);
        PacketHandler.registerPacket(SOpenOverviewScreenPacket.class, SOpenOverviewScreenPacket::encode, SOpenOverviewScreenPacket::decode, SOpenOverviewScreenPacket::handle);
        PacketHandler.registerPacket(CRequestSyncWorldDataPacket.class, CRequestSyncWorldDataPacket::encode, CRequestSyncWorldDataPacket::decode, CRequestSyncWorldDataPacket::handle);

    }
}
