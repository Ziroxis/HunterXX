package com.yuanno.hunterxx.api.ability.sorts;

import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.data.world.ExtendedWorldData;
import com.yuanno.hunterxx.events.levels.ExperienceUpEvent;
import com.yuanno.hunterxx.networking.AuraSync;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncEntityStatsPacket;
import com.yuanno.hunterxx.networking.server.SUpdateEquippedAbilityPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;

public abstract class RepeaterAbility extends ContinuousAbility implements IParallelContinuousAbility {


    private int repeaterCount;
    private int maxRepeaterCount;
    private int repeaterInterval;

    public RepeaterAbility(String name, AbilityCategories.AbilityCategory category)
    {
        super(name, category);
    }

    /*
     * 	Event Starters
     */
    private void repeater(PlayerEntity player, int passiveTimer)
    {
        if(this.repeaterCount > 0 && passiveTimer % this.repeaterInterval == 0)
        {
            if(this.onUseEvent.onUse(player))
                this.repeaterCount--;
        }
    }

    /*
     * 	Setters/Getters
     */
    public void setMaxRepeaterCount(int count, int interval)
    {
        this.maxRepeaterCount = count;
        this.repeaterCount = this.maxRepeaterCount;
        this.repeaterInterval = interval;
        int threshold = (int) Math.ceil((this.repeaterCount * this.repeaterInterval) / 20.0f);
        if(this.repeaterInterval == 0)
            threshold = -1;
        this.setThreshold(threshold);
    }

    public void setRepeaterCount(int count)
    {
        this.repeaterCount = count;
    }


    public int getMaxRepeaterCount()
    {
        return this.maxRepeaterCount;
    }

    public int getRepeaterCount(){
        return this.repeaterCount;
    }

    public int getRepeaterInterval()
    {
        return this.repeaterInterval;
    }
    /*
     * 	Methods
     */
    @Override
    public void use(PlayerEntity player)
    {
        super.use(player);
        IEntityStats stats = EntityStatsCapability.get(player);
        stats.alterAura((int) -getauraCost());
        if (stats.getLevel() < getExperienceGainLevelCap())
        {
            stats.alterExperience(getExperiencePoint());

            ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, getExperiencePoint());
            MinecraftForge.EVENT_BUS.post(eventExperience);
        }
        PacketHandler.sendTo(new AuraSync(stats.getAura()), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), stats), player);

    }

    @Override
    public void tick(PlayerEntity player)
    {
        if(this.isContinuous())
        {
            if(ExtendedWorldData.get(player.level).isInsideRestrictedArea((int)player.getX(), (int)player.getY(), (int)player.getZ()))
            {
                this.endContinuity(player);
                return;
            }

            this.continueTime++;
            if(!player.level.isClientSide) {
                this.duringContinuityEvent.duringContinuity(player, this.continueTime);
                this.repeater(player, this.continueTime);
            }

            if(this.getThreshold() != 0 && this.continueTime >= this.getThreshold())
                this.endContinuity(player);
        }
    }

    @Override
    public void endContinuity(PlayerEntity player)
    {
        if(player.level.isClientSide)
            return;
        if(this.onEndContinuityEvent.onEndContinuity(player))
        {
            this.continueTime = 0;
            this.repeaterCount = this.maxRepeaterCount;
            this.startCooldown(player);
            PacketHandler.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), player);
        }
    }

}
