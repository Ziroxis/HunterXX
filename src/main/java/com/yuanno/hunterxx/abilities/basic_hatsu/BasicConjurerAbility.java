package com.yuanno.hunterxx.abilities.basic_hatsu;

import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.sorts.ItemAbility;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BasicConjurerAbility extends ItemAbility {

    public static final BasicConjurerAbility INSTANCE = new BasicConjurerAbility();

    public BasicConjurerAbility()
    {
        super("Basic conjuration", AbilityCategories.AbilityCategory.HATSU);
        this.setDescription("Conjures a basic sword");
        this.setMaxCooldown(0);
        this.setauraCost(10);
    }

    @Override
    public ItemStack getItemStack(PlayerEntity player)
    {
        IEntityStats entityStats = EntityStatsCapability.get(player);
        String category = entityStats.getCategory();
        switch (category) {
            case ModValues.CONJURATION:
                return new ItemStack(Items.DIAMOND_SWORD);
            case ModValues.TRANSMUTATION:
            case ModValues.SPECIALIST:
                return new ItemStack(Items.IRON_SWORD);
            case ModValues.MANIPULATION:
            case ModValues.ENHANCEMENT:
                return new ItemStack(Items.STONE_SWORD);
            case ModValues.EMISSION:
                return new ItemStack(Items.WOODEN_SWORD);
            default:
                return new ItemStack(Items.STONE);
        }
    }

    @Override
    public boolean canBeActive(PlayerEntity player) {
        return true;
    }

}
