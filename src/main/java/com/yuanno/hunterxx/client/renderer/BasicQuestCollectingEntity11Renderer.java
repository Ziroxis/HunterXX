package com.yuanno.hunterxx.client.renderer;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.questers.basic.collecting.EntityBasicCollectingQuest11;
import com.yuanno.hunterxx.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
@OnlyIn(Dist.CLIENT)
public class BasicQuestCollectingEntity11Renderer extends MobRenderer<EntityBasicCollectingQuest11, HumanoidModel<EntityBasicCollectingQuest11>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entity/npc/basicquestentitycollecting11_texture.png");

    public BasicQuestCollectingEntity11Renderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBasicCollectingQuest11 p_110775_1_) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityBasicCollectingQuest11> {

        @Override
        public EntityRenderer<? super EntityBasicCollectingQuest11> createRenderFor(EntityRendererManager manager) {
            return new BasicQuestCollectingEntity11Renderer(manager);
        }
    }
}
