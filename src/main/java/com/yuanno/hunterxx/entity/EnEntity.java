package com.yuanno.hunterxx.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;
import java.util.UUID;

public class EnEntity extends Entity {

    private LivingEntity owner;
    private UUID ownerUUID;

    public EnEntity(EntityType type, World world) {
        super(type, world);
    }

    public EnEntity(World world, LivingEntity owner)
    {
        super(Summons.EN.get(), world);
        this.ownerUUID = owner.getUUID();
    }

    public void setOwner(UUID uuid)
    {
        this.ownerUUID = uuid;
        this.owner = (LivingEntity) ((ServerWorld) this.level).getEntity(this.ownerUUID);
    }

    @Override
    public boolean isColliding(BlockPos pPos, BlockState pState) {
        return super.isColliding(pPos, pState);
    }


    @Override
    public void tick()
    {
        super.tick();

        if(this.level.isClientSide)
            return;

        if (this.ownerUUID == null)
        {
            this.remove();
            return;
        }

        if(this.owner == null)
        {
            this.owner = (LivingEntity) ((ServerWorld) this.level).getEntity(this.ownerUUID);
            return;
        }

        if(this.owner.getHealth() <= 0)
        {
            this.remove();
        }

        //this.move(MoverType.SELF, new Vector3d(this.owner.getX(), this.owner.getY(), this.owner.getZ()));
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void addAdditionalSaveData(CompoundNBT compound)
    {
        compound.putString("OwnerUUID", this.ownerUUID.toString());
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT compound)
    {
        if (compound.contains("OwnerUUID", 8))
            this.setOwner(UUID.fromString(compound.getString("OwnerUUID")));
    }

    @Override
    public IPacket<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
