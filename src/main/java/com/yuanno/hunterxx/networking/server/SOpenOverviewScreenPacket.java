package com.yuanno.hunterxx.networking.server;

import com.yuanno.hunterxx.client.gui.OverViewScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenOverviewScreenPacket {

    public SOpenOverviewScreenPacket() {}

    public void encode(PacketBuffer buffer)
    {
    }

    public static SOpenOverviewScreenPacket decode(PacketBuffer buffer)
    {
        SOpenOverviewScreenPacket msg = new SOpenOverviewScreenPacket();
        return msg;
    }

    public static void handle(SOpenOverviewScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            ctx.get().enqueueWork(() -> ClientHandler.handle(message));
        ctx.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SOpenOverviewScreenPacket message)
        {
            OverViewScreen.open();
        }
    }
}
