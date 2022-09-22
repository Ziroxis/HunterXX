package com.yuanno.hunterxx.api.Quest.objectives;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.data.quest.objectives.IEquipItemObjective;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

public class EquippedItemObjective extends Objective implements IEquipItemObjective
{
	private Item itemTarget;
	private EquipmentSlotType slotTarget;

	public EquippedItemObjective(String title, int count, Item item, EquipmentSlotType slot)
	{
		super(title);
		this.setMaxProgress(count);
		this.itemTarget = item;
		this.slotTarget = slot;
	}

	@Override
	public boolean checkEquippedItem(PlayerEntity player)
	{
		return player.getItemBySlot(this.slotTarget).getItem() == this.itemTarget;
	}

	@Override
	public String getLocalizedTitle() 
	{
		String objectiveKey = new TranslationTextComponent(String.format("quest.objective." + Main.MODID + ".%s", this.getId())).getKey();
		return new TranslationTextComponent(objectiveKey, new ItemStack(this.itemTarget).getDisplayName()).getString(); 
	}
}
