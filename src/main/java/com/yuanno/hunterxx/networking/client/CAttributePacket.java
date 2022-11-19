package com.yuanno.hunterxx.networking.client;

import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.init.ModAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import org.w3c.dom.Attr;

import java.util.UUID;
import java.util.function.Supplier;

public class CAttributePacket {

    public static UUID attack_attributemodifier_uuid = UUID.fromString("aa18be1b-b1d0-4917-a168-42dd240d000d");
    public static UUID defense_attributemodifier_uuid = UUID.fromString("806281d3-bb17-4b3f-8142-f03f077ba2e2");
    public static UUID health_attributemodifier_uuid = UUID.fromString("135d510d-26c6-403e-8615-899862332e86");
    public static UUID speed_attributemodifier_uuid = UUID.fromString("69082b90-7357-407c-9e82-7852b6925932");

    public CAttributePacket() {}

    int attributes;

    public CAttributePacket(int attributes)
    {
        this.attributes = attributes;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(attributes);
    }

    public static CAttributePacket decode(PacketBuffer buffer)
    {
        CAttributePacket msg = new CAttributePacket();
        msg.attributes = buffer.readInt();
        return msg;
    }

    public static void handle(CAttributePacket msg, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                IEntityStats entityStats = EntityStatsCapability.get(player);
                ModifiableAttributeInstance health = player.getAttribute(Attributes.MAX_HEALTH);
                ModifiableAttributeInstance strength = player.getAttribute(Attributes.ATTACK_DAMAGE);
                ModifiableAttributeInstance defense = player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get());
                ModifiableAttributeInstance speed = player.getAttribute(Attributes.MOVEMENT_SPEED);

                if (msg.attributes == 1) // health
                {
                    UUID healthUUID = health_attributemodifier_uuid;
                    AttributeModifier healthModifier = new AttributeModifier(healthUUID, "healthStatIncrease", entityStats.getHealth() * 2, AttributeModifier.Operation.ADDITION);

                    health.removeModifier(healthUUID);
                    health.addPermanentModifier(healthModifier);
                }
                else if (msg.attributes == 2)
                {
                    UUID strengthUUID = attack_attributemodifier_uuid;
                    AttributeModifier strengthModifier = new AttributeModifier(strengthUUID, "attackStatIncrease", entityStats.getStrength() * 0.5, AttributeModifier.Operation.ADDITION);

                    strength.removeModifier(strengthModifier);
                    strength.addPermanentModifier(strengthModifier);
                }
                else if (msg.attributes == 3)
                {
                    UUID defenseUUID = defense_attributemodifier_uuid;
                    AttributeModifier defenseModifier = new AttributeModifier(defenseUUID, "defenseStatIncrease", entityStats.getDefense() * 0.5, AttributeModifier.Operation.ADDITION);

                    defense.removeModifier(defenseModifier);
                    defense.addPermanentModifier(defenseModifier);
                }
                else if (msg.attributes == 4)
                {
                    UUID speedUUID = speed_attributemodifier_uuid;
                    AttributeModifier speedModifier = new AttributeModifier(speedUUID, "speedStatIncrease", entityStats.getSpeed() * 0.01, AttributeModifier.Operation.ADDITION);

                    speed.removeModifier(speedModifier);
                    speed.addPermanentModifier(speedModifier);
                }
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
