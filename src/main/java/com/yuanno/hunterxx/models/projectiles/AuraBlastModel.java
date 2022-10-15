package com.yuanno.hunterxx.models.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AuraBlastModel extends EntityModel {

    private final ModelRenderer Aura_Blast;

    public AuraBlastModel() {
        texWidth = 128;
        texHeight = 128;

        Aura_Blast = new ModelRenderer(this);
        Aura_Blast.setPos(0.0F, 24.0F, 0.0F);
        Aura_Blast.texOffs(0, 18).addBox(-8.0F, -7.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
        Aura_Blast.texOffs(0, 0).addBox(-8.0F, -21.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
        Aura_Blast.texOffs(32, 36).addBox(7.0F, -19.0F, -7.0F, 2.0F, 12.0F, 14.0F, 0.0F, false);
        Aura_Blast.texOffs(0, 36).addBox(-9.0F, -19.0F, -7.0F, 2.0F, 12.0F, 14.0F, 0.0F, false);
        Aura_Blast.texOffs(48, 18).addBox(-8.0F, -19.0F, 7.0F, 16.0F, 12.0F, 2.0F, 0.0F, false);
        Aura_Blast.texOffs(48, 0).addBox(-8.0F, -19.0F, -9.0F, 16.0F, 12.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                               float alpha) {
        Aura_Blast.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
