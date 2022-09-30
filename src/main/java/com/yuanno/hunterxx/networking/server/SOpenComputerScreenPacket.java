package com.yuanno.hunterxx.networking.server;

import com.yuanno.hunterxx.client.gui.ComputerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenComputerScreenPacket {

    public SOpenComputerScreenPacket()
    {}

    public void encode(PacketBuffer buffer)
    {}

    public static SOpenComputerScreenPacket decode(PacketBuffer buffer)
    {
        SOpenComputerScreenPacket msg = new SOpenComputerScreenPacket();
        return msg;
    }

    public static void handle(SOpenComputerScreenPacket message, final Supplier<NetworkEvent.Context> contextSupplier)
    {
        if (contextSupplier.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            contextSupplier.get().enqueueWork(() -> ClientHandler.handle(message));
        contextSupplier.get().setPacketHandled(true);
    }

    public static class ClientHandler
    {
        @OnlyIn(Dist.CLIENT)
        public static void handle(SOpenComputerScreenPacket message)
        {
            PlayerEntity player = Minecraft.getInstance().player;;
            Minecraft.getInstance().setScreen(new ComputerScreen(player));
        }
    }
}
