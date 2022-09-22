package com.yuanno.hunterxx.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.SequencedString;
import com.yuanno.hunterxx.api.TexturedIconButton;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CRequestSyncWorldDataPacket;
import com.yuanno.hunterxx.networking.client.CUpdateQuestStatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

@OnlyIn(Dist.CLIENT)
public class ChatPromptScreen extends Screen {

    private final ResourceLocation chatPrompt = new ResourceLocation(Main.MODID, "textures/gui/chatprompt.png");
    private final ResourceLocation declineButton = new ResourceLocation(Main.MODID, "textures/gui/questdeclinebutton.png");
    private final ResourceLocation acceptButton = new ResourceLocation(Main.MODID, "textures/gui/questacceptbutton.png");

    private int state = 0;
    private QuesterEntity questerEntity;

    private IQuestData questData;
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;
    protected PlayerEntity player;
    private SequencedString message = new SequencedString("", 0, 0);


    public ChatPromptScreen(QuesterEntity questerEntity)
    {
        super(new StringTextComponent(""));
        this.questerEntity = questerEntity;
        this.minecraft = Minecraft.getInstance();
        this.player = Minecraft.getInstance().player;

    }

    @Override
    public void init()
    {
        PacketHandler.sendToServer(new CRequestSyncWorldDataPacket());

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        this.questData = QuestDataCapability.get(player);
        this.message = new SequencedString(questerEntity.questSpeech + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000);
        TexturedIconButton test = new TexturedIconButton(acceptButton, posX + 800, posY + 800, 32, 32, new TranslationTextComponent(""), b ->
        {
            //just a button to remove the giant black square
        });
        TexturedIconButton acceptbutton = new TexturedIconButton(acceptButton, posX + 10, posY + 215, 32, 32, new TranslationTextComponent(""), b ->
        {
            System.out.println("Given quest");
            questData.addInProgressQuest(questerEntity.questGiving);
            PacketHandler.sendToServer(new CUpdateQuestStatePacket(questerEntity.questGiving));
            this.state = 1;
        });
        TexturedIconButton declinebutton = new TexturedIconButton(declineButton, posX + 140, posY + 215, 32, 32, new TranslationTextComponent(""), b ->
        {
            this.message = new SequencedString(questerEntity.decliningSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
            System.out.println("Quest declined");
            this.state = -1;
        });
        this.addButton(test);
        if (this.state == 0)
        {
            this.addButton(acceptbutton);
            this.addButton(declinebutton);
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        this.renderBackground(matrixStack);

        if (this.state == 0) {
            questSpeechrendering(matrixStack);
        }
        else if (this.state == 1)
            acceptanceSpeechrender(matrixStack);
        else if (this.state == -1)
            declineSpeechrender(matrixStack);
        else if (questData.hasInProgressQuest(questerEntity.questGiving))
            onGoingSpeechrender(matrixStack);
        else if (questData.hasFinishedQuest(questerEntity.questGiving))
            endingSpeechrender(matrixStack);
        this.message.render(matrixStack, guiLeft - 88, guiTop + 105);
        super.render(matrixStack, x ,y , f);
    }

    public void questSpeechrendering(MatrixStack matrixStack)
    {
        int posX = this.width / 2;
        int posY = this.height / 2;
        int scaledWidth = this.getMinecraft().getWindow().getGuiScaledWidth();
        int scaleHeight = this.getMinecraft().getWindow().getGuiScaledHeight();
        minecraft.getTextureManager().bind(chatPrompt);
        GuiUtils.drawTexturedModalRect(posX - 128, posY - 128, 0, 0, 256, 256, 0);
        IQuestData questProps = QuestDataCapability.get(this.player);
        //drawString(matrixStack, font, questerEntity.questSpeech + "", guiLeft - 88, guiTop + 100, 16777215);
    }
    public void acceptanceSpeechrender(MatrixStack matrixStack)
    {
        int scaledWidth = this.getMinecraft().getWindow().getGuiScaledWidth();
        minecraft.getTextureManager().bind(chatPrompt);
        GuiUtils.drawTexturedModalRect((int) ((scaledWidth / 2) - 128), -16, 0, 0, 256, 256, 0);

        //drawString(matrixStack, font, questerEntity.acceptanceSpeech, guiLeft - 40 , guiTop - 80, 16777215);
    }
    public void declineSpeechrender(MatrixStack matrixStack)
    {
        int scaledWidth = this.getMinecraft().getWindow().getGuiScaledWidth();
        minecraft.getTextureManager().bind(chatPrompt);
        GuiUtils.drawTexturedModalRect((int) ((scaledWidth / 2) - 128), -16, 0, 0, 256, 256, 0);

        //drawString(matrixStack, font, questerEntity.decliningSpeech, guiLeft - 40 , guiTop - 80, 16777215);

    }

    public void onGoingSpeechrender(MatrixStack matrixStack)
    {
        int scaledWidth = this.getMinecraft().getWindow().getGuiScaledWidth();
        minecraft.getTextureManager().bind(chatPrompt);
        GuiUtils.drawTexturedModalRect((int) ((scaledWidth / 2) - 128), 0, 0, 0, 256, 256, 0);

        drawString(matrixStack, font, questerEntity.ongoingSpeech, guiLeft - 40 , guiTop - 80, 16777215);

    }

    public void endingSpeechrender(MatrixStack matrixStack)
    {
        int scaledWidth = this.getMinecraft().getWindow().getGuiScaledWidth();
        minecraft.getTextureManager().bind(chatPrompt);
        GuiUtils.drawTexturedModalRect((int) ((scaledWidth / 2) - 128), 0, 0, 0, 256, 256, 0);

        drawString(matrixStack, font, questerEntity.doneSpeech, guiLeft - 40 , guiTop - 80, 16777215);

    }


}
