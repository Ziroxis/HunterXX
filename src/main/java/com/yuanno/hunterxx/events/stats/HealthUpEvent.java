package com.yuanno.hunterxx.events.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class HealthUpEvent extends PlayerEvent {
    public int health;

    public HealthUpEvent(PlayerEntity player, int healthStat)
    {
        super(player);
        this.health = health;
    }
}
