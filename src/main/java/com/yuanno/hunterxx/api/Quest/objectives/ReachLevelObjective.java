package com.yuanno.hunterxx.api.Quest.objectives;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.data.quest.objectives.IReachLevelObjective;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;


public class ReachLevelObjective extends Objective implements IReachLevelObjective
{
	private int level;
	
	public ReachLevelObjective(String title, int doriki)
	{
		super(title);
		this.setMaxProgress(1);
		this.level = doriki;
	}

	@Override
	public boolean checkLevel(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		return props.getLevel() >= this.level;
	}

	@Override
	public String getLocalizedTitle() 
	{
		String objectiveKey = new TranslationTextComponent(String.format("quest.objective." + Main.MODID + ".%s", this.getId())).getKey();
		return new TranslationTextComponent(objectiveKey, this.level).getString();
	}


}
