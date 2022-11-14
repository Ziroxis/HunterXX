package com.yuanno.hunterxx.api.Quest.objectives;

import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.data.quest.objectives.IContinuousAbilityObjective;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;


public class UseContinuousAbilityObjective extends Objective implements IContinuousAbilityObjective {
    
    private int timer;
    private Ability ability;
    private BlockPos blockPos;
    private boolean meditation;

    public UseContinuousAbilityObjective(String title, int count, Ability ability, boolean meditation)
    {
        super(title);
        this.timer = count;
        this.ability = ability;
        this.meditation = meditation;
        this.setMaxProgress(this.timer);
        this.onStartEvent = this::onStartEvent;
    }

    private boolean onStartEvent(PlayerEntity player)
    {
        //this.timer = 0;
        this.blockPos = player.getEntity().blockPosition();
        return true;
    }

    @Override
    public boolean checkContinuesTimeAbility(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        Ability continuousAbility = abilityData.getEquippedAbility(this.ability);
        if (this.meditation)
        {
            if (continuousAbility == null || !continuousAbility.isContinuous() || !player.getEntity().blockPosition().equals(this.blockPos)) {
                this.setProgress(0);
                this.blockPos = player.getEntity().blockPosition();
                return false;
            }
        }
        else
        {
            if (continuousAbility == null || !continuousAbility.isContinuous()) {
                this.setProgress(0);
                return false;
            }
        }
        return true;
    }
}
