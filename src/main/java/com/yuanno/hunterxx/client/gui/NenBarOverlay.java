package com.yuanno.hunterxx.client.gui;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class NenBarOverlay {
    private final ResourceLocation nenBar = new ResourceLocation(Main.MODID, "textures/gui/nenbar.png");
    private final int tex_width = 130, tex_height = 45;
    int aura = 0;

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event)
    {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        assert player != null;
        IEntityStats entityStats = EntityStatsCapability.get(player);

        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT)
        {
            if (entityStats.getCombatMode() && entityStats.getMaxAura() > 0)
            {
                Minecraft mc = Minecraft.getInstance();

                String auraString = "" + entityStats.getAura();
                mc.textureManager.bind(nenBar);
                float auraRatio = (float) entityStats.getAura() / entityStats.getMaxAura();
                int move_tex = (int) (tex_width * auraRatio);
                mc.gui.blit(event.getMatrixStack(), 30, 172, 0, 0, move_tex, tex_height); // aura itself
                mc.gui.blit(event.getMatrixStack(), 40, 175, 10, 50, tex_width, tex_height); // bar where the aura is in
                Beapi.drawStringWithBorder(Minecraft.getInstance().font, event.getMatrixStack(), TextFormatting.BOLD + auraString, 98, 193, Beapi.hexToRGB("#00FF00").getRGB());
            }
        }
    }
}
