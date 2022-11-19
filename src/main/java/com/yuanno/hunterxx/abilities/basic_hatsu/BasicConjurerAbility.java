package com.yuanno.hunterxx.abilities.basic_hatsu;

import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.sorts.ItemAbility;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class BasicConjurerAbility extends ItemAbility {

    public static final BasicConjurerAbility INSTANCE = new BasicConjurerAbility();
    public CompoundNBT nbt = new CompoundNBT();
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
        ItemStack conjuredSword;
        //stack.setTag(nbt);
        //stack.getOrCreateTagElement("conujured");
        switch (category) {
            case ModValues.CONJURATION:
                conjuredSword = new ItemStack(Items.DIAMOND_SWORD);
                break;
            case ModValues.TRANSMUTATION:
            case ModValues.SPECIALIST:
                conjuredSword = new ItemStack(Items.IRON_SWORD);
                break;
            case ModValues.MANIPULATION:
            case ModValues.ENHANCEMENT:
                conjuredSword = new ItemStack(Items.STONE_SWORD);
                break;
            case ModValues.EMISSION:
                conjuredSword = new ItemStack(Items.WOODEN_SWORD);
                break;
            default:
                conjuredSword = new ItemStack(Items.STONE);
        }
        //nbt.putString("Basic sword", "conjured");
        //conjuredSword.getOrCreateTagElement("Conjured basic");
        //conjuredSword.setTag(nbt);
        //conjuredSword.addTagElement("test", INSTANCE.nbt);
        //conjuredSword.getItem().getTags().add(ResourceLocation.tryParse("test"));
        conjuredSword.getTag().putString("conjuration", "basic sword");
        return conjuredSword;
    }

    @Override
    public boolean canBeActive(PlayerEntity player) {
        return true;
    }

}
