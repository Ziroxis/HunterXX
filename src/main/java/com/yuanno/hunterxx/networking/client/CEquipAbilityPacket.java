package com.yuanno.hunterxx.networking.client;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CEquipAbilityPacket
{
	private int slot;
	private String abilityName;
	
	public CEquipAbilityPacket() {}
	
	public CEquipAbilityPacket(int id, Ability ability)
	{
		this.slot = id;
		this.abilityName = Beapi.getResourceName(ability.getName());
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.slot);
		buffer.writeInt(this.abilityName.length());
		buffer.writeUtf(this.abilityName, this.abilityName.length());
	}
	
	public static CEquipAbilityPacket decode(PacketBuffer buffer)
	{
		CEquipAbilityPacket msg = new CEquipAbilityPacket();
		msg.slot = buffer.readInt();
		int len = buffer.readInt();
		msg.abilityName = buffer.readUtf(len);
		return msg;
	}

	public static void handle(CEquipAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);

				Ability ability = GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(Main.MODID, message.abilityName)).create();
				
				abilityDataProps.setEquippedAbility(message.slot, ability);
				PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, ability), player);
			});	
		}
		ctx.get().setPacketHandled(true);
	}
}