package com.yuanno.hunterxx.abilities.advanced;

import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import net.minecraft.entity.player.PlayerEntity;

public class InAbiltiy extends ContinuousAbility implements IParallelContinuousAbility {
    public static final InAbiltiy INSTANCE = new InAbiltiy();

    public InAbiltiy()
    {
        super("In", AbilityCategories.AbilityCategory.ADVANCED_NEN);
        this.setDescription("Shrouds your own aura making it invisible for other nen users.\nCan only be seen through gyo.");
        this.setauraCost(10);
        this.setExperience(10);
        this.setExperienceGainLevelCap(30);

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
