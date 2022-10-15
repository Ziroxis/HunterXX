package com.yuanno.hunterxx.abilities.basic_hatsu;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.entity.projectiles.AuraBlastProjectile;
import com.yuanno.hunterxx.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;

public class BasicEmissionAbility extends Ability {

    public static final BasicEmissionAbility INSTANCE = new BasicEmissionAbility();

    public BasicEmissionAbility()
    {
        super("Basic emission", AbilityCategories.AbilityCategory.HATSU);
        this.setDescription("A basic hatsu that blasts aura out of your body");
        this.setCooldown(5);
        this.setauraCost(10);
        this.setExperience(12);
        this.setExperienceGainLevelCap(40);

        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        AuraBlastProjectile projectile = new AuraBlastProjectile(player.level, player);
        projectile.setDamage((8 * Beapi.valueCategory(player, ModValues.EMISSION)));
        projectile.setMaxLife((int) (128 * Beapi.valueCategory(player, ModValues.EMISSION)));
        player.level.addFreshEntity(projectile);
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f * Beapi.valueCategory(player, ModValues.EMISSION), 1);
        return true;
    }
}
