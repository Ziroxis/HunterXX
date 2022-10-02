package com.yuanno.hunterxx.abilities.basic;

import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import net.minecraft.entity.player.PlayerEntity;

public class ZetsuAbility extends ContinuousAbility {
    public static final ZetsuAbility INSTANCE = new ZetsuAbility();

    public ZetsuAbility()
    {
        super("Zetsu", AbilityCategories.AbilityCategory.BASIC_NEN);
        this.setDescription("Completely stops the output of aura.\nMaking people unable to see your aura");
        this.setMaxCooldown(5);
        this.setauraCost(7);
        this.setExperience(7);
        this.setExperienceGainLevelCap(15);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        IAbilityData iAbilityData = AbilityDataCapability.get(player);
        for (int i = 0; i < iAbilityData.getEquippedAbilities().length; i++)
        {
            if (iAbilityData.getEquippedAbility(i).isContinuous() && iAbilityData.getEquippedAbility(i) != this)
                iAbilityData.getEquippedAbility(i).cooldown(player);
        }
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        return true;
    }
}
