package com.yuanno.hunterxx.networking.client;

import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncQuestDataPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CAbandonQuestPacket
{
	private int questId;

	public CAbandonQuestPacket() {}

	public CAbandonQuestPacket(int questId)
	{
		this.questId = questId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.questId);
	}

	public static CAbandonQuestPacket decode(PacketBuffer buffer)
	{
		CAbandonQuestPacket msg = new CAbandonQuestPacket();
		msg.questId = buffer.readInt();
		return msg;
	}
	
	public static void handle(CAbandonQuestPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				ServerPlayerEntity player = ctx.get().getSender();
				IQuestData props = QuestDataCapability.get(player);

				Quest current = props.getInProgressQuest(message.questId);
				props.removeInProgressQuest(current);
				PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
