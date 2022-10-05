package com.yuanno.hunterxx.client.overlay.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KoModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer Ko;

    public KoModel() {
        super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
        texWidth = 32;
        texHeight = 32;

        Ko = new ModelRenderer(this);
        Ko.setPos(-5.0F, 2.0F, 0.0F);
        Ko.texOffs(0, 0).addBox(-3.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
        Ko.texOffs(16, 6).addBox(-3.0F, 6.0F, 2.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
        Ko.texOffs(16, 0).addBox(-3.0F, 6.0F, -4.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
        Ko.texOffs(10, 10).addBox(-4.0F, 6.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
        Ko.texOffs(0, 10).addBox(1.0F, 6.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.crouching = entity.isCrouching();

        this.Ko.copyFrom(this.rightArm);

        AbstractClientPlayerEntity clientPlayer = (AbstractClientPlayerEntity) entity;

        ArmPose mainHandPos = armPose(clientPlayer, Hand.MAIN_HAND);
        ArmPose offHandPos = armPose(clientPlayer, Hand.OFF_HAND);

        this.swimAmount = clientPlayer.getSwimAmount(ageInTicks);

        if (clientPlayer.getMainArm() == HandSide.RIGHT) {
            this.rightArmPose = mainHandPos;
            this.leftArmPose = offHandPos;
        } else {
            this.rightArmPose = offHandPos;
            this.leftArmPose = mainHandPos;
        }

        this.Ko.copyFrom(this.rightArm);

    }

    private static ArmPose armPose(AbstractClientPlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.isEmpty()) {
            return ArmPose.EMPTY;
        } else {
            if (player.getUsedItemHand() == hand && player.getUseItemRemainingTicks() > 0) {
                UseAction useaction = itemstack.getUseAnimation();
                if (useaction == UseAction.BLOCK) {
                    return ArmPose.BLOCK;
                }

                if (useaction == UseAction.BOW) {
                    return ArmPose.BOW_AND_ARROW;
                }

                if (useaction == UseAction.SPEAR) {
                    return ArmPose.THROW_SPEAR;
                }

                if (useaction == UseAction.CROSSBOW && hand == player.getUsedItemHand()) {
                    return ArmPose.CROSSBOW_CHARGE;
                }
            } else if (!player.swinging && itemstack.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemstack)) {
                return ArmPose.CROSSBOW_HOLD;
            }

            return ArmPose.ITEM;
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder builder, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        ImmutableList.of(this.Ko).forEach(modelRenderer -> {
            modelRenderer.render(matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
