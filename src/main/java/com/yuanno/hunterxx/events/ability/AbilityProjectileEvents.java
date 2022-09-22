package com.yuanno.hunterxx.events.ability;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.events.projectiles.ProjectileBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityProjectileEvents
{
	@SubscribeEvent
	public static void onBlockCheck(ProjectileBlockEvent event)
	{
		if( false )
		{
			event.setCanBlock(true);
		}
	}
}
