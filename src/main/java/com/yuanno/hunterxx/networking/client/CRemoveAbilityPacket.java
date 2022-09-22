package com.yuanno.hunterxx.networking.client;

import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.api.ability.sorts.ChargeableAbility;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CRemoveAbilityPacket
{
	private int slot;
	
	public CRemoveAbilityPacket() {}
	
	public CRemoveAbilityPacket(int id)
	{
		this.slot = id;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.slot);
	}
	
	public static CRemoveAbilityPacket decode(PacketBuffer buffer)
	{
		CRemoveAbilityPacket msg = new CRemoveAbilityPacket();
		msg.slot = buffer.readInt();
		return msg;
	}

	public static void handle(CRemoveAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);

				Ability ability = abilityDataProps.getEquippedAbility(message.slot);
				if(ability == null)
					return;
				
				if(ability instanceof ContinuousAbility && ((ContinuousAbility)ability).isContinuous())
					((ContinuousAbility)ability).stopContinuity(player);
				else if(ability instanceof ChargeableAbility && ((ChargeableAbility)ability).isCharging())
					((ChargeableAbility)ability).stopCharging(player);
				else if(ability.isOnCooldown())
					ability.stopCooldown(player);
				
				abilityDataProps.setEquippedAbility(message.slot, null);
				
				if(abilityDataProps.getEquippedAbilitySlot(ability) >= 0)
					PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, ability), player);
			});	
		}
		ctx.get().setPacketHandled(true);
	}
}