package com.yuanno.hunterxx.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.SequencedString;
import com.yuanno.hunterxx.api.TexturedIconButton;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CRequestSyncQuestDataPacket;
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
    private PlayerEntity player;
    private SequencedString message = new SequencedString("", 0, 0);
    TexturedIconButton test;

    TexturedIconButton acceptbutton;
    TexturedIconButton declinebutton;

    public ChatPromptScreen(PlayerEntity player, QuesterEntity questerEntity)
    {
        super(new StringTextComponent(""));
        this.questerEntity = questerEntity;
        this.minecraft = Minecraft.getInstance();
        this.player = player;
        assert this.player != null;
        this.questData = QuestDataCapability.get(player);

    }

    @Override
    public void init()
    {
        super.init();
        PacketHandler.sendToServer(new CRequestSyncWorldDataPacket());
        PacketHandler.sendToServer(new CRequestSyncQuestDataPacket());
        Quest questHaveToDo = questerEntity.questPreviouslyDone;
        Quest npcQuest = questerEntity.questGiving;
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        boolean finishedquest = false;
        if (questerEntity.previousQuestObliged)
        {
            for (int i = 0; i < questData.getFinishedQuests().size(); i++) {
                if (questData.getFinishedQuests().get(i) != null && questData.getFinishedQuests().get(i).getId().equals(questHaveToDo.getId())) {
                    finishedquest = true;
                    break;
                }
            }
        }
        if (!finishedquest && questerEntity.previousQuestObliged)
        {
            this.message = new SequencedString(questerEntity.denialSpeechPreviousQuest + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
            this.state = 4;
        }
        for (int i = 0; i <questData.getInProgressQuests().length; i++)
        {
            if (questData.getInProgressQuest(i) != null && questData.getInProgressQuest(i).getId().equals(npcQuest.getId()))
            {
                this.message = new SequencedString(questerEntity.ongoingSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                this.state = 2;
            }
        }
        for (int i = 0; i < questData.getFinishedQuests().size(); i++)
        {
            if (questData.getFinishedQuests().get(i) != null && questData.getFinishedQuests().get(i).getId().equals(npcQuest.getId()))
            {
                this.message = new SequencedString(questerEntity.doneSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                this.state = 3;
            }
        }
        // first time talking to npc
        if (this.state == 0)
        {
            this.message = new SequencedString(questerEntity.questSpeech + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000); // -> first time talking to the npc
            test = new TexturedIconButton(acceptButton, posX + 800, posY + 800, 32, 32, new TranslationTextComponent(""), b ->
            {
                //just a button to remove the giant black square
            });
            acceptbutton = new TexturedIconButton(acceptButton, posX + 10, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
            {
                this.questData.addInProgressQuest(npcQuest);
                PacketHandler.sendToServer(new CUpdateQuestStatePacket(npcQuest));
                PacketHandler.sendToServer(new CRequestSyncQuestDataPacket());
                this.state = 1;
                this.message = new SequencedString(questerEntity.acceptanceSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
            }); // -> accepting the quest
            declinebutton = new TexturedIconButton(declineButton, posX + 180, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
            {
                this.message = new SequencedString(questerEntity.decliningSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                this.state = -1;
            }); // -> declining the quest
            this.addButton(test);
            this.addButton(acceptbutton);
            this.addButton(declinebutton);
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f) {
        this.renderBackground(matrixStack);


        if (!(this.state == 0)) {
            this.buttons.remove(acceptbutton);
            this.buttons.remove(declinebutton);
        }


        int posX = this.width / 2;
        int posY = this.height / 2;
        minecraft.getTextureManager().bind(chatPrompt);
        GuiUtils.drawTexturedModalRect(posX - 128, posY - 125, 0, 0, 256, 256, 0);
        this.message.render(matrixStack, guiLeft - 88, guiTop + 115);
        super.render(matrixStack, x, y, f);
    }
}
