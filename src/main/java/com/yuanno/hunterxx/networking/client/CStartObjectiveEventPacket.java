package com.yuanno.hunterxx.networking.client;

import com.yuanno.hunterxx.api.Quest.Objective;
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

public class CStartObjectiveEventPacket
{
	private int questId;
	private int objId;

	public CStartObjectiveEventPacket() {}

	public CStartObjectiveEventPacket(int questId, int objId)
	{
		this.questId = questId;
		this.objId = objId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.questId);
		buffer.writeInt(this.objId);
	}

	public static CStartObjectiveEventPacket decode(PacketBuffer buffer)
	{
		CStartObjectiveEventPacket msg = new CStartObjectiveEventPacket();
		msg.questId = buffer.readInt();
		msg.objId = buffer.readInt();
		return msg;
	}

	public static void handle(CStartObjectiveEventPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				ServerPlayerEntity player = ctx.get().getSender();
				IQuestData props = QuestDataCapability.get(player);
				
				Quest current = props.getInProgressQuest(message.questId);
				if (current != null)
				{
					Objective obj = current.getObjectives().get(message.objId);
					if(obj != null)
					{
						if (obj.hasStartedEvent())
							obj.triggerRestartEvent(player);
						else
							obj.triggerStartEvent(player);
						PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
					}
				}
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
