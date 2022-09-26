package com.yuanno.hunterxx.entity.hostile;

import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.events.levels.ExperienceUpEvent;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class HXEntity extends CreatureEntity {
    protected int xpDrop = 0;
    public boolean canUseMagic = true;
    protected HXEntity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
/*
        if (cause.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) cause.getEntity();
            IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));

            Beapi.experienceMultiplier(player, this.magicXPDrop);

            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketMagicExpSync(playercap.returnMagicExp(), player.getId()));

        }
        */
        if (cause.getEntity() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) cause.getEntity();
            IEntityStats props = EntityStatsCapability.get(player);

            props.alterExperience(this.getExperience());
            ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, props.getExperience());
            if (MinecraftForge.EVENT_BUS.post(eventExperience))
                return;
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), props), player);
        }
    }

    public int getExperience()
    {
        return xpDrop;
    }
    public boolean canUseMagic()
    {
        return canUseMagic;
    }
}
