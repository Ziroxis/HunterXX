package com.yuanno.hunterxx.api.Quest.objectives;

import com.yuanno.hunterxx.abilities.advanced.KoAbility;
import com.yuanno.hunterxx.abilities.basic.RenAbility;
import com.yuanno.hunterxx.api.ItemsHelper;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import net.minecraft.item.ItemStack;

public class SharedHitChecks
{
	public static final HitEntityObjective.ICheckHit HAS_SWORD = (player, target, source, amount) ->
	{
		ItemStack heldItem = player.getMainHandItem();
		return ItemsHelper.isSword(heldItem);
	};
	
	public static final HitEntityObjective.ICheckHit HAS_BOW = (player, target, source, amount) ->
	{
		ItemStack heldItem = player.getMainHandItem();
		return ItemsHelper.isBow(heldItem);
	};
	
	public static final HitEntityObjective.ICheckHit HAS_REN = (player, target, source, amount) ->
	{
		IAbilityData abilityData = AbilityDataCapability.get(player);
		RenAbility renAbility = abilityData.getEquippedAbility(RenAbility.INSTANCE);
		if (renAbility != null)
			return renAbility.isContinuous();
		else
			return false;
	};
	public static final HitEntityObjective.ICheckHit HAS_KO = (player, target, source, amount) ->
	{
		IAbilityData abilityData = AbilityDataCapability.get(player);
		KoAbility koAbility = abilityData.getEquippedAbility(KoAbility.INSTANCE);
		if (koAbility != null)
			return koAbility.isContinuous();
		else
			return false;
	};
	
	public static final HitEntityObjective.ICheckHit SWEEP_ATTACK_CHECK = (player, target, source, amount) ->
	{
		return source.getMsgId().equalsIgnoreCase("sweep_damage");
	};

	/*
	public static final HitEntityObjective.ICheckHit checkAbilitySource(Ability ability)
	{
		return (player, target, source, amount) ->
		{
			return source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getAbilitySource().equals(ability);
		};
	}

	 */
}
