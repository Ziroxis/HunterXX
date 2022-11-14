package com.yuanno.hunterxx.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.ability.AbilityHelper;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncAbilityDataPacket;
import com.yuanno.hunterxx.networking.server.SSyncQuestDataPacket;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

public class QuestCommand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("quest").requires(source -> source.hasPermission(3));

		builder
			.then(Commands.literal("finish")
				.then(Commands.argument("quest", QuestArgument.quest())
				.then(Commands.argument("target", EntityArgument.player())
					.executes(context -> finishQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target"))))))
			.then(Commands.literal("give")
				.then(Commands.argument("quest", QuestArgument.quest())
				.then(Commands.argument("target", EntityArgument.player())
					.executes(context -> giveQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target"))))))
			.then(Commands.literal("unfinish")
				.then(Commands.argument("quest", QuestArgument.quest())
				.then(Commands.argument("target", EntityArgument.player())
					.executes(context -> unfinishQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target"))))))
			.then(Commands.literal("remove")
				.then(Commands.argument("quest", QuestArgument.quest())
				.then(Commands.argument("target", EntityArgument.player())
					.executes(context -> removeQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target"))))));
		
		dispatcher.register(builder);
	}
	
	private static int unfinishQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player)
	{
		IQuestData props = QuestDataCapability.get(player);
		IAbilityData abilityData = AbilityDataCapability.get(player);
		
		if(props.hasFinishedQuest(quest))
		{
			props.removeFinishedQuest(quest);
			//AbilityHelper.validateStyleMoves(player);
			PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
			PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
		}
		else
			player.sendMessage(new StringTextComponent("You haven't finished this quest!"), Util.NIL_UUID);
		
		return 1;
	}
	
	private static int finishQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player)
	{
		IQuestData props = QuestDataCapability.get(player);
		
		if(props.hasInProgressQuest(quest) && quest.triggerCompleteEvent(player))
		{
			props.addFinishedQuest(quest);
			props.removeInProgressQuest(quest);
			PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
		}
		else if(!props.hasInProgressQuest(quest))
			player.sendMessage(new StringTextComponent("You don't have this quest!"), Util.NIL_UUID);
		
		return 1;
	}
	
	private static int giveQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player)
	{
		IQuestData props = QuestDataCapability.get(player);
		
		if(!props.hasInProgressQuest(quest))
		{
			props.addInProgressQuest(quest.create());
			PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
		}
		else
			player.sendMessage(new StringTextComponent("You aleady have this quest!"), Util.NIL_UUID);
		
		return 1;
	}
	
	private static int removeQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player)
	{
		IQuestData props = QuestDataCapability.get(player);
		
		if(props.hasInProgressQuest(quest))
		{
			props.removeInProgressQuest(quest);
			props.removeFinishedQuest(quest);
			PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
		}
		else
			player.sendMessage(new StringTextComponent("You don't have this quest!"), Util.NIL_UUID);
		
		return 1;
	}
}
