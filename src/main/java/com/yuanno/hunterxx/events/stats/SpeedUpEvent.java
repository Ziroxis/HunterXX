package com.yuanno.hunterxx.events.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class SpeedUpEvent extends PlayerEvent {
    public int speed;

    public SpeedUpEvent(PlayerEntity player, int speed)
    {
        super(player);
        this.speed = speed;
    }
}
