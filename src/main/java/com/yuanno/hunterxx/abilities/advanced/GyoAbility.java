package com.yuanno.hunterxx.abilities.advanced;

import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import net.minecraft.entity.player.PlayerEntity;

public class GyoAbility extends ContinuousAbility implements IParallelContinuousAbility {

    public static final GyoAbility INSTANCE = new GyoAbility();

    public GyoAbility()
    {
        super("Gyo", AbilityCategories.AbilityCategory.BASIC_NEN);
        this.setDescription("Focuses your aura into your eyes.\nMaking you able to see other aura constructs");
        this.setMaxCooldown(5);
        this.setauraCost(8);
        this.setExperience(8);
        this.setExperienceGainLevelCap(20);

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
