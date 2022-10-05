package com.yuanno.hunterxx.client.overlay.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnSphereModel extends EntityModel<Entity>{
	private final ModelRenderer bone;

	public EnSphereModel() {
		texWidth = 128;
		texHeight = 128;

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 24.0F, 0.0F);
		bone.texOffs(0, 38).addBox(-1.0F, -11.0F, 0.0F, 3.0F, 11.0F, 3.0F, 0.0F, false);
		bone.texOffs(36, 54).addBox(-1.0F, -11.0F, 3.0F, 3.0F, 11.0F, 1.0F, 0.0F, false);
		bone.texOffs(24, 56).addBox(-1.0F, -11.0F, -1.0F, 3.0F, 11.0F, 1.0F, 0.0F, false);
		bone.texOffs(48, 32).addBox(2.0F, -11.0F, 0.0F, 1.0F, 11.0F, 3.0F, 0.0F, false);
		bone.texOffs(48, 48).addBox(-2.0F, -11.0F, 0.0F, 1.0F, 11.0F, 3.0F, 0.0F, false);
		bone.texOffs(28, 28).addBox(-2.0F, -10.0F, -1.0F, 5.0F, 9.0F, 5.0F, 0.0F, false);
		bone.texOffs(0, 24).addBox(-3.0F, -9.0F, -2.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
		bone.texOffs(0, 12).addBox(-4.0F, -8.0F, -2.0F, 9.0F, 5.0F, 7.0F, 0.0F, false);
		bone.texOffs(25, 5).addBox(-2.0F, -7.0F, -2.0F, 7.0F, 3.0F, 7.0F, 0.0F, false);
		bone.texOffs(0, 52).addBox(-2.0F, -10.0F, -2.0F, 5.0F, 9.0F, 1.0F, 0.0F, false);
		bone.texOffs(46, 0).addBox(-2.0F, -10.0F, 4.0F, 5.0F, 9.0F, 1.0F, 0.0F, false);
		bone.texOffs(12, 38).addBox(3.0F, -10.0F, -1.0F, 1.0F, 9.0F, 5.0F, 0.0F, false);
		bone.texOffs(24, 42).addBox(-3.0F, -10.0F, -1.0F, 1.0F, 9.0F, 5.0F, 0.0F, false);
		bone.texOffs(36, 42).addBox(-4.0F, -9.0F, -1.0F, 1.0F, 7.0F, 5.0F, 0.0F, false);
		bone.texOffs(32, 15).addBox(4.0F, -9.0F, -1.0F, 1.0F, 7.0F, 5.0F, 0.0F, false);
		bone.texOffs(56, 32).addBox(-2.0F, -9.0F, 5.0F, 5.0F, 7.0F, 1.0F, 0.0F, false);
		bone.texOffs(56, 56).addBox(-2.0F, -9.0F, -3.0F, 5.0F, 7.0F, 1.0F, 0.0F, false);
		bone.texOffs(43, 26).addBox(-3.0F, -8.0F, -3.0F, 7.0F, 5.0F, 1.0F, 0.0F, false);
		bone.texOffs(44, 15).addBox(-3.0F, -8.0F, 5.0F, 7.0F, 5.0F, 1.0F, 0.0F, false);
		bone.texOffs(21, 24).addBox(-1.0F, -8.0F, 6.0F, 3.0F, 5.0F, 1.0F, 0.0F, false);
		bone.texOffs(58, 21).addBox(-1.0F, -8.0F, -4.0F, 3.0F, 5.0F, 1.0F, 0.0F, false);
		bone.texOffs(23, 0).addBox(-2.0F, -7.0F, -4.0F, 5.0F, 3.0F, 1.0F, 0.0F, false);
		bone.texOffs(25, 15).addBox(-2.0F, -7.0F, 6.0F, 5.0F, 3.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 0).addBox(5.0F, -8.0F, 0.0F, 1.0F, 5.0F, 3.0F, 0.0F, false);
		bone.texOffs(53, 43).addBox(-5.0F, -8.0F, 0.0F, 1.0F, 5.0F, 3.0F, 0.0F, false);
		bone.texOffs(53, 5).addBox(-5.0F, -7.0F, -1.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
		bone.texOffs(12, 52).addBox(5.0F, -7.0F, -1.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
		bone.texOffs(0, 0).addBox(-3.0F, -7.0F, -3.0F, 7.0F, 3.0F, 9.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay, 1, 1, 1, 0.3f);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}