package com.yuanno.hunterxx.client.renderer;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.questers.basic.EntityBasicQuest1;
import com.yuanno.hunterxx.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class BasicQuestEntity1Renderer extends MobRenderer<EntityBasicQuest1, HumanoidModel<EntityBasicQuest1>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entity/npc/basicquestentity1_texture.png");

    public BasicQuestEntity1Renderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBasicQuest1 p_110775_1_) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityBasicQuest1> {

        @Override
        public EntityRenderer<? super EntityBasicQuest1> createRenderFor(EntityRendererManager manager) {
            return new BasicQuestEntity1Renderer(manager);
        }
    }
}
