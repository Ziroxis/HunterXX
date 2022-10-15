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
public class BasicTransmutationModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer RightLeg;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer Sharp;

    public BasicTransmutationModel() {
        super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
        texWidth = 64;
        texHeight = 64;

        Head = new ModelRenderer(this);
        Head.setPos(0.0F, 0.0F, 0.0F);
        Head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        Body = new ModelRenderer(this);
        Body.setPos(0.0F, 0.0F, 0.0F);
        Body.texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

        RightArm = new ModelRenderer(this);
        RightArm.setPos(-5.0F, 2.0F, 0.0F);
        RightArm.texOffs(16, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setPos(5.0F, 2.0F, 0.0F);
        LeftArm.texOffs(32, 0).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        RightLeg = new ModelRenderer(this);
        RightLeg.setPos(-1.9F, 12.0F, 0.0F);
        RightLeg.texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setPos(1.9F, 12.0F, 0.0F);
        LeftLeg.texOffs(24, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        Sharp = new ModelRenderer(this);
        Sharp.setPos(0.0F, 24.0F, 0.0F);
        Sharp.texOffs(48, 8).addBox(-7.0F, -3.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        Sharp.texOffs(24, 0).addBox(4.0F, -3.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        Sharp.texOffs(48, 4).addBox(-7.0F, -9.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        Sharp.texOffs(44, 0).addBox(4.0F, -9.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        Sharp.texOffs(0, 48).addBox(-11.0F, -17.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        Sharp.texOffs(47, 14).addBox(8.0F, -17.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        Sharp.texOffs(40, 46).addBox(8.0F, -22.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        Sharp.texOffs(32, 44).addBox(-11.0F, -22.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        Sharp.texOffs(24, 4).addBox(-3.0F, -2.0F, -3.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Sharp.texOffs(20, 16).addBox(1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Sharp.texOffs(41, 41).addBox(1.0F, -17.0F, -5.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        Sharp.texOffs(41, 36).addBox(-3.0F, -17.0F, -5.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        Sharp.texOffs(40, 31).addBox(-7.0F, -20.0F, -5.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        Sharp.texOffs(40, 26).addBox(5.0F, -20.0F, 2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        Sharp.texOffs(40, 16).addBox(-7.0F, -20.0F, 2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        Sharp.texOffs(0, 3).addBox(-7.0F, -12.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Sharp.texOffs(0, 0).addBox(5.0F, -12.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Sharp.texOffs(40, 21).addBox(5.0F, -20.0F, -5.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        Sharp.texOffs(32, 38).addBox(4.0F, -26.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
        Sharp.texOffs(32, 32).addBox(-6.0F, -26.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.crouching = entity.isCrouching();

        this.Head.copyFrom(this.head);
        this.Body.copyFrom(this.body);
        this.LeftArm.copyFrom(this.leftArm);
        this.RightArm.copyFrom(this.rightArm);
        this.LeftLeg.copyFrom(this.leftLeg);
        this.RightLeg.copyFrom(this.rightLeg);
        this.Sharp.copyFrom(this.body);



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

        this.RightArm.copyFrom(this.rightArm);
        this.LeftArm.copyFrom(this.leftArm);

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
        ImmutableList.of(this.Head, this.Body, this.LeftArm, this.RightArm, this.LeftLeg, this.RightLeg, this.Sharp).forEach(modelRenderer -> {
            modelRenderer.render(matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
