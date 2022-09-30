package com.yuanno.hunterxx.items;

import com.yuanno.hunterxx.init.ModItemGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.UUID;

public class HunterLicenseItem extends Item {

    boolean hasOwner = false;
    public HunterLicenseItem()
    {
        super(new Properties().tab(ModItemGroup.MISC));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack itemstack = player.getItemInHand(hand);
        if (world.isClientSide)
            return ActionResult.success(itemstack);

        if (!hasOwner)
        {
            ItemStack itemStack = player.getItemInHand(hand);

            ((HunterLicenseItem) itemStack.getItem()).setLicenseOwner(itemStack, player);
            itemStack.setHoverName(new StringTextComponent(player.getDisplayName().getString() + "'s license"));
            hasOwner = true;
        }

        return ActionResult.success(itemstack);
    }

    @Nullable
    public LivingEntity getOwner(World world, BlockPos pos, ItemStack itemStack)
    {
        UUID uuid = itemStack.getOrCreateTag().getUUID("ownerUUID");
        return (LivingEntity) ((ServerWorld)world).getEntity(uuid);
    }

    public void setLicenseOwner(ItemStack itemStack, LivingEntity owner)
    {
        itemStack.setTag(new CompoundNBT());
        itemStack.getTag().putUUID("ownerUUID", owner.getUUID());
    }
}
