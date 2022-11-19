package com.yuanno.hunterxx.networking.client;

import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Random;
import java.util.function.Supplier;

public class CStatsCCPacket {
    private int levelPoints, health, strength, defense, speed, maxAura, auraRegen;

    public CStatsCCPacket()
    {

    }

    public CStatsCCPacket(int levelPoints, int health, int strength, int defense, int speed, int maxAura, int auraRegen)
    {
        this.levelPoints = levelPoints;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
        this.speed = speed;
        this.maxAura = maxAura;
        this.auraRegen = auraRegen;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.levelPoints);
        buffer.writeInt(this.health);
        buffer.writeInt(this.strength);
        buffer.writeInt(this.defense);
        buffer.writeInt(this.speed);
        buffer.writeInt(this.maxAura);
        buffer.writeInt(this.auraRegen);
    }

    public static CStatsCCPacket decode(PacketBuffer buffer)
    {
        CStatsCCPacket msg = new CStatsCCPacket();
        msg.levelPoints = buffer.readInt();
        msg.health = buffer.readInt();
        msg.strength = buffer.readInt();
        msg.defense = buffer.readInt();
        msg.speed = buffer.readInt();
        msg.maxAura = buffer.readInt();
        msg.auraRegen = buffer.readInt();

        return msg;
    }

    public static void handle(CStatsCCPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                IEntityStats entityStats = EntityStatsCapability.get(player);
                Random random = player.getRandom();

                entityStats.setLevelPoints(message.levelPoints);
                entityStats.setHealth(message.health);
                entityStats.setStrength(message.strength);
                entityStats.setDefense(message.defense);
                entityStats.setSpeed(message.speed);
                entityStats.setMaxAura(message.maxAura);
                entityStats.setAuraRegeneration(message.auraRegen);


                PacketHandler.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getId(), entityStats), player);

            });
        }
        ctx.get().setPacketHandled(true);
    }
}
