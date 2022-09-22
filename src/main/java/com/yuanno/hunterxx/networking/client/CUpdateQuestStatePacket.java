package com.yuanno.hunterxx.networking.client;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncAbilityDataPacket;
import com.yuanno.hunterxx.networking.server.SSyncQuestDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public class CUpdateQuestStatePacket
{
	private INBT data;
	private String questId;
	
	public CUpdateQuestStatePacket() {}

	public CUpdateQuestStatePacket(Quest quest)
	{
		this.questId = quest.getRegistryName().toString();
	}
	
	@Deprecated
	public CUpdateQuestStatePacket(IQuestData props)
	{
		this.data = new CompoundNBT();
		this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		int len = this.questId.length();
		buffer.writeInt(len);
		buffer.writeUtf(this.questId, len);
	}

	public static CUpdateQuestStatePacket decode(PacketBuffer buffer)
	{
		CUpdateQuestStatePacket msg = new CUpdateQuestStatePacket();
		int len = buffer.readInt();
		msg.questId = buffer.readUtf(len);
		return msg;
	}

	public static void handle(CUpdateQuestStatePacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				if(Beapi.isNullOrEmpty(message.questId))
					return;
							
				PlayerEntity player = ctx.get().getSender();
				assert player != null;
				IQuestData props = QuestDataCapability.get(player);
				Quest quest = GameRegistry.findRegistry(Quest.class).getValue(new ResourceLocation(message.questId));

				if (props.hasInProgressQuest(quest) && props.getInProgressQuest(quest).isComplete() && props.getInProgressQuest(quest).triggerCompleteEvent(player))
				{
					props.addFinishedQuest(quest);
					props.removeInProgressQuest(quest);
				}
				else if (!props.hasInProgressQuest(quest) && quest.triggerStartEvent(player))
					props.addInProgressQuest(quest);

				PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
				PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), AbilityDataCapability.get(player)), player);
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
