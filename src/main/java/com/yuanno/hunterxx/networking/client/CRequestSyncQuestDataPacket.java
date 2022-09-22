package com.yuanno.hunterxx.networking.client;

import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncQuestDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CRequestSyncQuestDataPacket
{
	public CRequestSyncQuestDataPacket() {}
	
	public void encode(PacketBuffer buffer)
	{
	}
	
	public static CRequestSyncQuestDataPacket decode(PacketBuffer buffer)
	{
		CRequestSyncQuestDataPacket msg = new CRequestSyncQuestDataPacket();
		return msg;
	}
	
	public static void handle(CRequestSyncQuestDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IQuestData props = QuestDataCapability.get(player);

				PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
			});			
		}
		ctx.get().setPacketHandled(true);
	}
}
