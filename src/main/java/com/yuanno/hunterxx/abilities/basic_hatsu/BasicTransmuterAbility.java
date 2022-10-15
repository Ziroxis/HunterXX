package com.yuanno.hunterxx.abilities.basic_hatsu;

import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import net.minecraft.entity.player.PlayerEntity;

public class BasicTransmuterAbility extends ContinuousAbility implements IParallelContinuousAbility {
    public static final BasicTransmuterAbility INSTANCE = new BasicTransmuterAbility();

    public BasicTransmuterAbility()
    {
        super("Basic transmutation", AbilityCategories.AbilityCategory.HATSU);
        this.setDescription("A basic transmutation hatsu that shapes your surrounding aura in thorns.\nDealing damage to enemies hitting you");
        this.setauraCost(10);
        this.setExperience(12);
        this.setExperienceGainLevelCap(40);

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
