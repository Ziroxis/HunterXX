package com.yuanno.hunterxx.client.renderer;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.questers.basic.collecting.EntityBasicCollectingQuest7;
import com.yuanno.hunterxx.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
@OnlyIn(Dist.CLIENT)
public class BasicQuestCollectingEntity7Renderer extends MobRenderer<EntityBasicCollectingQuest7, HumanoidModel<EntityBasicCollectingQuest7>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entity/npc/basicquestentitycollecting7_texture.png");

    public BasicQuestCollectingEntity7Renderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBasicCollectingQuest7 p_110775_1_) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityBasicCollectingQuest7> {

        @Override
        public EntityRenderer<? super EntityBasicCollectingQuest7> createRenderFor(EntityRendererManager manager) {
            return new BasicQuestCollectingEntity7Renderer(manager);
        }
    }
    
}
