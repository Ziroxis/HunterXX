package com.yuanno.hunterxx.abilities.basic;

import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
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
        this.setExperienceGainLevelCap(12);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        return true;
    }
}
