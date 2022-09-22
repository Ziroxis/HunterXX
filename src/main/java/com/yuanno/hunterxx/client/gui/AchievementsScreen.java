package com.yuanno.hunterxx.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.TexturedIconButton;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.init.ModValues;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CRequestSyncWorldDataPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class AchievementsScreen extends Screen {


    protected PlayerEntity player;
    private final ResourceLocation questScreen = new ResourceLocation(Main.MODID, "textures/gui/overview.png");
    private final ResourceLocation backButton = new ResourceLocation(Main.MODID, "textures/gui/test.png");
    private IEntityStats entityStats;
    private final int xSize = 64;
    private final int ySize = 58;
    private int guiLeft;
    private int guiTop;

    public AchievementsScreen(PlayerEntity player)
    {
        super(new StringTextComponent(""));
        this.player = player;
    }

    @Override
    public void init()
    {
        PacketHandler.sendToServer(new CRequestSyncWorldDataPacket());

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;

        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        this.entityStats = EntityStatsCapability.get(player);
        TexturedIconButton testButton = new TexturedIconButton(backButton, posX + 500, posY + 1700, 32, 32, new TranslationTextComponent(""), b ->
        {
            // empty button to not have the ugly big black box
        });
        TexturedIconButton statsButton = new TexturedIconButton(backButton, posX + 50, posY + 170, 32, 32, new TranslationTextComponent(""), b ->
        {
            Minecraft.getInstance().setScreen(new OverViewScreen());
        });
        this.addButton(testButton);
        this.addButton(statsButton);
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        this.renderBackground(matrixStack);
        questListRendering(matrixStack);
        super.render(matrixStack, x, y, f);

    }

    public void questListRendering(MatrixStack matrixStack)
    {
        int scaledWidth = this.getMinecraft().getWindow().getGuiScaledWidth();

        minecraft.getTextureManager().bind(questScreen);
        GuiUtils.drawTexturedModalRect((int) ((scaledWidth / 2) - 128), 0, 0, 0, 256, 256, 0);
        IQuestData questProps = QuestDataCapability.get(this.player);

        List<Quest> questsCompleted = questProps.getFinishedQuests();
        int E = 0;
        int D = 0;
        int C = 0;
        int B = 0;
        int A = 0;
        int S = 0;
        for (Quest quest : questsCompleted) {
            switch (quest.getRank()) {
                case ModValues.E_RANK:
                    E += 1;
                    break;
                case ModValues.D_RANK:
                    D += 1;
                    break;
                case ModValues.C_RANK:
                    C += 1;
                    break;
                case ModValues.B_RANK:
                    B += 1;
                    break;
                case ModValues.A_RANK:
                    A += 1;
                    break;
                case ModValues.S_RANK:
                    S += 1;
                    break;
            }
        }
        drawString(matrixStack, font, TextFormatting.BOLD + "E missions completed: " + TextFormatting.RESET + E, guiLeft - 40, guiTop - 80, 16777215);
        drawString(matrixStack, font, TextFormatting.BOLD + "D missions completed: " + TextFormatting.RESET + D, guiLeft - 40, guiTop - 70, 16777215);
        drawString(matrixStack, font, TextFormatting.BOLD + "C missions completed: " + TextFormatting.RESET + C, guiLeft - 40, guiTop - 60, 16777215);
        drawString(matrixStack, font, TextFormatting.BOLD + "B missions completed: " + TextFormatting.RESET + B, guiLeft - 40, guiTop - 50, 16777215);
        drawString(matrixStack, font, TextFormatting.BOLD + "A missions completed: " + TextFormatting.RESET + A, guiLeft - 40, guiTop - 40, 16777215);
        drawString(matrixStack, font, TextFormatting.BOLD + "S missions completed: " + TextFormatting.RESET + S, guiLeft - 40, guiTop - 30, 16777215);
        drawString(matrixStack, font, TextFormatting.BOLD + "STARS EARNED: " + TextFormatting.RESET, guiLeft - 10, guiTop - 10, 16777215);


    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
