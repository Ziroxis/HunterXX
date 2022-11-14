package com.yuanno.hunterxx.api.Quest.objectives;

import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.data.quest.objectives.ITakeHitsObjective;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;

public class TakeHitsObjective extends Objective implements ITakeHitsObjective {
    protected HitEntityObjective.ICheckHit hitEvent = (player, target, source, amount) -> true;

    private int hits;
    private Ability ability;

    public TakeHitsObjective(String title, int count, @Nullable HitEntityObjective.ICheckHit check)
    {
        super(title);
        this.setMaxProgress(count);
        if (check != null)
            this.hitEvent = check;
    }

    @Override
    public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source, float amount)
    {
        return this.hitEvent.test(player, target, source, amount);
    }

    @FunctionalInterface
    public interface ITakeHitsObjective
    {
        boolean test(PlayerEntity player, LivingEntity target, DamageSource source, float amount);

        default HitEntityObjective.ICheckHit and(HitEntityObjective.ICheckHit check)
        {
            return (player, target, source, amount) -> {
                return this.test(player, target, source, amount) && check.test(player, target, source, amount);
            };
        }

        default HitEntityObjective.ICheckHit or(HitEntityObjective.ICheckHit check)
        {
            return (player, target, source, amount) -> {
                return this.test(player, target, source, amount) || check.test(player, target, source, amount);
            };
        }
    }
}
