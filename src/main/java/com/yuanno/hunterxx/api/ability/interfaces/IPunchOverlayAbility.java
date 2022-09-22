package com.yuanno.hunterxx.api.ability.interfaces;

import com.yuanno.hunterxx.api.ability.AbilityOverlay;
import net.minecraft.entity.LivingEntity;

public interface IPunchOverlayAbility
{
	AbilityOverlay getPunchOverlay(LivingEntity player);
}
