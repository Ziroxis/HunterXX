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
public class GyoModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer Gyo;

    public GyoModel() {
        super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
        texWidth = 16;
        texHeight = 16;

        Gyo = new ModelRenderer(this);
        Gyo.setPos(0.0F, 1.0F, 0.0F);
        Gyo.texOffs(0, 3).addBox(-3.0F, -6.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Gyo.texOffs(0, 0).addBox(1.0F, -6.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Gyo.texOffs(5, 5).addBox(-3.0F, -7.0F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Gyo.texOffs(5, 2).addBox(1.0F, -7.0F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Gyo.texOffs(6, 0).addBox(-2.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Gyo.texOffs(0, 6).addBox(1.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.crouching = entity.isCrouching();

        this.Gyo.copyFrom(this.head);


    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder builder, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        ImmutableList.of(this.Gyo).forEach(modelRenderer -> {
            modelRenderer.render(matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
