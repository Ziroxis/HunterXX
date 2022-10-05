package com.yuanno.hunterxx.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
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

public class AuraCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("aura").requires((commandSource) -> commandSource.hasPermission(3))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("aura", StringArgumentType.string()).suggests(SUGGEST_SET)
                                .then(Commands.argument("amount", IntegerArgumentType.integer())
                                        .executes((command) ->
                                        {
                                            String aura = StringArgumentType.getString(command, "aura");
                                            int amount = IntegerArgumentType.getInteger(command, "amount");
                                            return setPoints(command.getSource(), EntityArgument.getPlayer(command, "target"), aura, amount);
                                        })))));
    }

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("AURAREGEN");
        suggestions.add("MAXAURA");
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static int setPoints(CommandSource commandSource, PlayerEntity player, String aura, int amount)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (aura.equals("AURAREGEN"))
            entityStats.alterAuraRegeneration(amount);
        else if (aura.equals("MAXAURA"))
            entityStats.alterMaxAura(amount);
        else
            commandSource.sendFailure(new TranslationTextComponent(player.getDisplayName().getString() + " aura command failed"));
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        commandSource.sendSuccess(new TranslationTextComponent(player.getDisplayName().getString() + " added " + amount + " aura regeneration"), true);

        return 1;
    }
}
