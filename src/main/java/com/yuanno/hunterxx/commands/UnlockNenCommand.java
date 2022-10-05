package com.yuanno.hunterxx.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncEntityStatsPacket;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class UnlockNenCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("nen").requires((commandSource) -> commandSource.hasPermission(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("unlock", StringArgumentType.string()).suggests(SUGGEST_SET)
                        .executes((command) ->
                        {
                            String unlock = StringArgumentType.getString(command, "unlock");

                            return setPoints(command.getSource(), EntityArgument.getPlayer(command, "target"), unlock);
                        }))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("UNLOCK");
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setPoints(CommandSource commandSource, PlayerEntity player, String set)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (set.equals("UNLOCK"))
            entityStats.setHasNen(true);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        commandSource.sendSuccess(new TranslationTextComponent(player.getDisplayName().getString() + " unlocked nen"), true);

        return 1;
    }
}
