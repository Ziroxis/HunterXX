package com.yuanno.hunterxx.events.ability;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.api.ability.Bearendering;
import com.yuanno.hunterxx.api.ability.interfaces.IOutOfBodyAbility;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class AbilitiesClientEvents
{	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onRenderOverlay(RenderGameOverlayEvent event)
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;
		IAbilityData abilityDataProps = AbilityDataCapability.get(player);

		if (abilityDataProps == null)
			return;

		int posX = mc.getWindow().getGuiScaledWidth();
		int posY = mc.getWindow().getGuiScaledHeight();
				
		if (event.getType() == ElementType.VIGNETTE)
		{
			Optional<Ability> ability = Arrays.<Ability>stream(abilityDataProps.getEquippedAbilities(IOutOfBodyAbility.IS_ACTIVE)).findFirst();
			if(ability.isPresent())
			{
				float distance = (float) (((IOutOfBodyAbility)ability.get()).getDistanceFromPivot(player) / ((IOutOfBodyAbility)ability.get()).getMaxRange());
				Bearendering.renderVignette(player, distance, posX, posY);
			}
		}
	}
}
