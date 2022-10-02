package com.yuanno.hunterxx.data.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class EntityStatsCapability {

    @CapabilityInject(IEntityStats.class)
    public static final Capability<IEntityStats> INSTANCE = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IEntityStats.class, new Capability.IStorage<IEntityStats>()
        {
            @Override
            public INBT writeNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side)
            {
                CompoundNBT props = new CompoundNBT();
                //combat mode
                props.putBoolean("combatMode", instance.getCombatMode());

                //levels + experience
                props.putInt("level", instance.getLevel());
                props.putInt("experience", instance.getExperience());
                props.putInt("maxExperience", instance.getMaxExperience());
                props.putInt("levelPoints", instance.getLevelPoints());

                //stats
                props.putInt("health", instance.getHealth());
                props.putInt("strength", instance.getStrength());
                props.putInt("defense", instance.getDefense());
                props.putInt("speed", instance.getSpeed());
                props.putInt("nen", instance.getNen());

                //nen
                props.putInt("aura", instance.getAura());
                props.putInt("maxAura", instance.getMaxAura());
                props.putInt("auraRegeneration", instance.getAuraRegeneration());
                props.putString("category", instance.getCategory());

                //jenny
                props.putInt("jenny", instance.getJenny());

                //nen
                props.putBoolean("hasNen", instance.getHasNen());
                return props;
            }

            @Override
            public void readNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side, INBT nbt)
            {
                CompoundNBT props = (CompoundNBT) nbt;

                //combat mode
                instance.setCombatMode(props.getBoolean("combatMode"));

                //levels + experience
                instance.setLevel(props.getInt("level"));
                instance.setExperience(props.getInt("experience"));
                instance.setMaxExperience(props.getInt("maxExperience"));
                instance.setLevelPoints(props.getInt("levelPoints"));

                //stats
                instance.setHealth(props.getInt("health"));
                instance.setStrength(props.getInt("strength"));
                instance.setDefense(props.getInt("defense"));
                instance.setSpeed(props.getInt("speed"));
                instance.setNen(props.getInt("nen"));

                //nen
                instance.setAura(props.getInt("aura"));
                instance.setMaxAura(props.getInt("maxAura"));
                instance.setAuraRegeneration(props.getInt("auraRegeneration"));
                instance.setCategory(props.getString("category"));

                //jenny
                instance.setJenny(props.getInt("jenny"));

                //nen
                instance.setHasNen(props.getBoolean("hasNen"));
            }
        }, () -> new EntityStatsBase());

    }
    public static IEntityStats get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new EntityStatsBase());
    }
}
