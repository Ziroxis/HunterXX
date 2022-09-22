package com.yuanno.hunterxx.networking;

import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class AuraSync {
    private float aura;

    public AuraSync(float cursedEnergy)
    {
        this.aura = aura;
    }

    public static void encode(AuraSync msg, PacketBuffer buf)
    {
        buf.writeFloat(msg.aura);
    }

    public static AuraSync decode(PacketBuffer buf)
    {
        float data = buf.readFloat();
        return new AuraSync(data);
    }

    public static void handle(AuraSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            IEntityStats playercap = mc.player.getCapability(EntityStatsCapability.INSTANCE).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
            playercap.setAura((int) msg.aura);
        });
        ctx.get().setPacketHandled(true);
    }
}
