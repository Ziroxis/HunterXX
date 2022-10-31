package com.yuanno.hunterxx.abilities.hatsu.jajanken;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.sorts.ChargeableAbility;
import com.yuanno.hunterxx.entity.projectiles.AuraBlastProjectile;
import com.yuanno.hunterxx.init.ModEffects;
import com.yuanno.hunterxx.init.ModValues;
import com.yuanno.hunterxx.particles.ParticleEffect;
import com.yuanno.hunterxx.particles.hatsu.jajanken.ChargeUpParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class JajankenPaperAbility extends ChargeableAbility {

    public static final JajankenPaperAbility INSTANCE = new JajankenPaperAbility();
    private boolean cancelled = false;
    public static final ParticleEffect PARTICLES = new ChargeUpParticleEffect();

    public JajankenPaperAbility()
    {
        super("Jajanken paper", AbilityCategories.AbilityCategory.HATSU);
        this.setDescription("Charges up aura in the palm of your aura, releasing it in a blast");
        this.setMaxChargeTime(5);
        this.setauraCost(10);
        this.setMaxCooldown(10);

        this.onStartChargingEvent = this::onStartChargingEvent;
        this.duringChargingEvent = this::duringChargingEvent;
        this.onEndChargingEvent = this::onEndChargingEvent;
    }

    private boolean onStartChargingEvent(PlayerEntity player)
    {
        this.cancelled = false;
        return true;
    }

    private void duringChargingEvent(PlayerEntity player, int chargeTimer)
    {
        if (player.invulnerableTime > 0)
        {
            this.cancelled = true;
            this.stopCharging(player);
        }
        PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);

        player.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), 200, 10));

    }
    private boolean onEndChargingEvent(PlayerEntity player)
    {
        if (this.cancelled)
            return true;


        int charge = this.getMaxChargeTime() - this.getChargeTime();

        if (charge < 20 * 5)
            return false;

        AuraBlastProjectile projectile = new AuraBlastProjectile(player.level, player);
        projectile.setDamage((charge / 5f) * Beapi.valueCategory(player, ModValues.EMISSION));
        projectile.setMaxLife((int) ((charge) * 16 * Beapi.valueCategory(player, ModValues.EMISSION)));
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 1, 0.1f);
        player.removeEffect(ModEffects.MOVEMENT_BLOCKED.get());

        return true;
    }

}
