package com.yuanno.hunterxx.setup;

import com.mojang.brigadier.CommandDispatcher;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.commands.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeSetup {

    @SubscribeEvent
    public static void serverStarting(FMLServerStartingEvent event)
    {
        CommandDispatcher dispatcher = event.getServer().getCommands().getDispatcher();

        QuestCommand.register(dispatcher);
        AuraCommand.register(dispatcher);
        PointsCommand.register(dispatcher);
        AbilityCommand.register(dispatcher);
        UnlockNenCommand.register(dispatcher);
    }
}
