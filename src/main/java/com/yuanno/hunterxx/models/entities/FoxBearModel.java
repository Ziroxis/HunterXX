package com.yuanno.hunterxx.models.entities;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.hunterxx.entity.hostile.FoxBearEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class FoxBearModel<T extends FoxBearEntity> extends EntityModel<T> {
	private final ModelRenderer Foxbear;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftArm;
	private final ModelRenderer RightArm;
	private final ModelRenderer Body;
	private final ModelRenderer head;

	public FoxBearModel() {
		texWidth = 128;
		texHeight = 128;

		Foxbear = new ModelRenderer(this);
		Foxbear.setPos(0.0F, 24.0F, 0.0F);
		setRotationAngle(Foxbear, 0.0F, 3.1416F, 0.0F);


		LeftLeg = new ModelRenderer(this);
		LeftLeg.setPos(4.0F, -23.0F, 0.0F);
		Foxbear.addChild(LeftLeg);
		LeftLeg.texOffs(0, 44).addBox(-3.0F, -3.0F, -3.0F, 5.0F, 26.0F, 5.0F, 0.0F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setPos(-2.0F, -23.0F, 0.0F);
		Foxbear.addChild(RightLeg);
		RightLeg.texOffs(36, 27).addBox(-3.0F, -3.0F, -3.0F, 5.0F, 26.0F, 5.0F, 0.0F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setPos(5.0F, -36.0F, 0.0F);
		Foxbear.addChild(LeftArm);
		LeftArm.texOffs(20, 52).addBox(2.0F, -2.0F, -3.0F, 4.0F, 22.0F, 6.0F, 0.0F, false);

		RightArm = new ModelRenderer(this);
		RightArm.setPos(-3.0F, -36.0F, 0.0F);
		Foxbear.addChild(RightArm);
		RightArm.texOffs(51, 0).addBox(-7.0F, -2.0F, -3.0F, 4.0F, 23.0F, 6.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, -29.0F, 0.0F);
		Foxbear.addChild(Body);
		Body.texOffs(0, 0).addBox(-6.0F, -9.0F, -4.0F, 13.0F, 19.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -38.0F, 0.0F);
		Foxbear.addChild(head);
		head.texOffs(0, 27).addBox(-5.0F, -8.0F, -4.0F, 10.0F, 9.0F, 8.0F, -0.5F, false);
		head.texOffs(20, 44).addBox(-7.0F, -10.0F, -1.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);
		head.texOffs(34, 0).addBox(3.0F, -10.0F, -1.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.LeftArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.RightArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		this.RightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.LeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		Foxbear.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}