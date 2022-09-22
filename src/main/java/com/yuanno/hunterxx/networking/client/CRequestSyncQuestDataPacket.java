package com.yuanno.hunterxx.networking.client;

import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.data.quest.objectives.IObtainItemObjective;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SOpenChatPromptScreenPacket;
import com.yuanno.hunterxx.networking.server.SSyncQuestDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

public class CRequestSyncQuestDataPacket
{
	private boolean openScreen;
	public CRequestSyncQuestDataPacket() {}

	public CRequestSyncQuestDataPacket(boolean openScreen)
	{
		this.openScreen = openScreen;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeBoolean(this.openScreen);
	}

	public static CRequestSyncQuestDataPacket decode(PacketBuffer buffer)
	{
		CRequestSyncQuestDataPacket msg = new CRequestSyncQuestDataPacket();
		msg.openScreen = buffer.readBoolean();
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

				for (Quest qst : props.getInProgressQuests())
				{
					if(qst == null)
						continue;
					for(Objective obj : qst.getObjectives())
					{
						if (obj instanceof IObtainItemObjective)
						{
							ArrayList<ItemStack> slots = new ArrayList<ItemStack>();
							slots.addAll(player.inventory.items);
							slots.addAll(player.inventory.offhand);
							int items = ((IObtainItemObjective) obj).checkItems(slots);
							if (items > 0)
							{
								obj.setProgress(items);
								PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
							}
						}
					}
				}

				PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
