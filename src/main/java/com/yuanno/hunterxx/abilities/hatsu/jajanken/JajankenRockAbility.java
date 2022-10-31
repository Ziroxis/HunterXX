package com.yuanno.hunterxx.abilities.hatsu.jajanken;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.sorts.PunchAbility;
import com.yuanno.hunterxx.init.ModAttributes;
import com.yuanno.hunterxx.init.ModEffects;
import com.yuanno.hunterxx.init.ModValues;
import com.yuanno.hunterxx.particles.ParticleEffect;
import com.yuanno.hunterxx.particles.hatsu.jajanken.ChargeUpParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

import java.util.UUID;

public class JajankenRockAbility extends PunchAbility {

    public static final JajankenRockAbility INSTANCE = new JajankenRockAbility();
    public static final UUID jajankenRockRangeUUID = UUID.fromString("ea03a6be-587e-11ed-9b6a-0242ac120002");
    float timerDamage;
    public static final ParticleEffect PARTICLES = new ChargeUpParticleEffect();

    public JajankenRockAbility()
    {
        super("Jajanken rock", AbilityCategories.AbilityCategory.HATSU);
        this.setDescription("Charges up your fist with aura, releasing it when punching somebody");
        this.setMaxCooldown(15);
        this.setauraCost(10);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::duringContinuity;
        this.onHitEntityEvent = this::onHitEvent;
        this.onStopContinuityEvent = this::onStopContinuityEvent;
    }

    public boolean onStartContinuityEvent(PlayerEntity player)
    {
        timerDamage = 0;
        AttributeModifier jajankenReachModifier = new AttributeModifier(jajankenRockRangeUUID, "rockReach", 8, AttributeModifier.Operation.ADDITION);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).addTransientModifier(jajankenReachModifier);
        return true;
    }

    public void onStopContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(jajankenRockRangeUUID);
    }

    public void duringContinuity(PlayerEntity player, int timer)
    {
        if (!player.hasEffect(ModEffects.MOVEMENT_BLOCKED.get()))
            player.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 40, 0));
        if (timer % 20 == 0)
        {
            timerDamage++;
        }
        if (timerDamage > 6) {
            this.stopContinuity(player);
        }
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);

    }

    public float onHitEvent(PlayerEntity player, LivingEntity livingEntity)
    {
        if (player.hasEffect(ModEffects.MOVEMENT_BLOCKED.get()))
            player.removeEffect(ModEffects.MOVEMENT_BLOCKED.get());
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(jajankenRockRangeUUID);

        return 15 * (timerDamage) * (Beapi.valueCategory(player, ModValues.ENHANCEMENT));
    }
}
