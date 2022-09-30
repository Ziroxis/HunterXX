package com.yuanno.hunterxx.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.TexturedIconButton;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
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
public class QuestScreen extends Screen {

    protected PlayerEntity player;
    private final ResourceLocation questScreen = new ResourceLocation(Main.MODID, "textures/gui/overview.png");
    private final ResourceLocation backButton = new ResourceLocation(Main.MODID, "textures/gui/test.png");
    private final ResourceLocation basicButton = new ResourceLocation(Main.MODID, "textures/gui/basic_button.png");

    private IEntityStats entityStats;
    private final int xSize = 64;
    private final int ySize = 58;
    private int guiLeft;
    private int guiTop;

    public QuestScreen(PlayerEntity player)
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
        questRendering(matrixStack);
        super.render(matrixStack, x, y, f);
    }

    public void questRendering(MatrixStack matrixStack)
    {
        int scaledWidth = this.getMinecraft().getWindow().getGuiScaledWidth();

        minecraft.getTextureManager().bind(questScreen);
        GuiUtils.drawTexturedModalRect((int) ((scaledWidth / 2) - 128), 0, 0, 0, 256, 256, 0);
        IQuestData questProps = QuestDataCapability.get(this.player);

        Quest[] quests = questProps.getInProgressQuests();

        for (int i = 0; i < quests.length; i++)
        {
            if (quests[i] != null)
            {
                Quest questInProgress = questProps.getInProgressQuests()[i];
                String questInProgressString = questInProgress.getTitle();
                String questInProgressDescription = questInProgress.getDescription();
                drawString(matrixStack, font, "Quest: ", guiLeft - 40 , guiTop - 80 + (i * 50), 16777215);
                drawString(matrixStack, font, questInProgressString, guiLeft - 5, guiTop - 80 + (i * 50), 16777215);
                List<Objective> objectives = questInProgress.getObjectives();
                drawString(matrixStack, font, "Rank: " + questInProgress.getRank(), guiLeft - 40, guiTop - 60 + (i * 50) + (objectives.size() * 8), 16777215);
                if (quests[i].isComplete())
                {
                    drawString(matrixStack, font, TextFormatting.BOLD + "DONE", guiLeft - 40 , guiTop - 50 + (i * 50) + (objectives.size() * 8), 16777215);

                }
                for (int a = 0; a < objectives.size(); a++)
                {
                    Objective objective = objectives.get(a);
                    String objectiveString = objective.getTitle();
                    drawString(matrixStack, font, "Goal: " + objectiveString, guiLeft - 40, guiTop - 70 + (i * 50) + (a * 8), 16777215);

                }
            }
        }
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

}
