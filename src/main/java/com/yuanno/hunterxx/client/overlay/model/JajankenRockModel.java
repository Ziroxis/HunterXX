package com.yuanno.hunterxx.client.overlay.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JajankenRockModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer Rock;

    public JajankenRockModel() {
        super(RenderType::entityTranslucent, 1, 0.0F, 64, 64);
        texWidth = 32;
        texHeight = 32;

        Rock = new ModelRenderer(this);
        Rock.setPos(-5.0F, 2.0F, 0.0F);
        Rock.texOffs(0, 0).addBox(-4.0F, 4.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
        Rock.texOffs(14, 14).addBox(-3.0F, 6.0F, 3.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
        Rock.texOffs(0, 14).addBox(-3.0F, 6.0F, -6.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
        Rock.texOffs(12, 21).addBox(-6.0F, 6.0F, -2.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
        Rock.texOffs(0, 21).addBox(2.0F, 6.0F, -2.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.crouching = entity.isCrouching();

        this.Rock.copyFrom(this.rightArm);


    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder builder, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        ImmutableList.of(this.Rock).forEach(modelRenderer -> {
            modelRenderer.render(matrixStack, builder, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
