package com.yuanno.hunterxx.client.renderer;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.questers.nen.AdvancedNenTeacher;
import com.yuanno.hunterxx.entity.questers.nen.EntityNenTeacher;
import com.yuanno.hunterxx.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class AdvancedNenRenderer extends MobRenderer<AdvancedNenTeacher, HumanoidModel<AdvancedNenTeacher>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entity/npc/advancednen_texture.png");

    public AdvancedNenRenderer(EntityRendererManager rendererManager)
    {
        super(rendererManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(AdvancedNenTeacher p_110775_1_)
    {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<AdvancedNenTeacher> {
        @Override
        public EntityRenderer<? super AdvancedNenTeacher> createRenderFor(EntityRendererManager manager)
        {
            return new AdvancedNenRenderer(manager);
        }
    }
}
