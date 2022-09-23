package com.yuanno.hunterxx.client.renderer;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.questers.basic.collecting.EntityBasicCollectingQuest3;
import com.yuanno.hunterxx.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class BasicQuestCollectingEntity3Renderer extends MobRenderer<EntityBasicCollectingQuest3, HumanoidModel<EntityBasicCollectingQuest3>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entity/npc/basicquestentitycollecting3_texture.png");

    public BasicQuestCollectingEntity3Renderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBasicCollectingQuest3 p_110775_1_) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityBasicCollectingQuest3> {

        @Override
        public EntityRenderer<? super EntityBasicCollectingQuest3> createRenderFor(EntityRendererManager manager) {
            return new BasicQuestCollectingEntity3Renderer(manager);
        }
    }
}
