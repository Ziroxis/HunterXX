package com.yuanno.hunterxx.entity.projectiles;

import com.yuanno.hunterxx.api.ability.AbilityProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class AuraBlastProjectile extends AbilityProjectileEntity {

    public AuraBlastProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public AuraBlastProjectile(World world, LivingEntity player)
    {
        super(AuraProjectiles.AURA_BLAST.get(), world, player);
        this.setDamage(0);
        this.setMaxLife(0);
        this.setPhysical(false);
    }
}
