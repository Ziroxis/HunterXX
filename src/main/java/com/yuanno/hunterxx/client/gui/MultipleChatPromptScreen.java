package com.yuanno.hunterxx.client.gui;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.SequencedString;
import com.yuanno.hunterxx.api.TexturedIconButton;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MultipleChatPromptScreen extends Screen {
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

    public MultipleChatPromptScreen(PlayerEntity player, QuesterEntity questerEntity)
    {
        super(new StringTextComponent(""));
        this.questerEntity = questerEntity;
        this.minecraft = Minecraft.getInstance();
        this.player = player;
        assert this.player != null;
        this.questData = QuestDataCapability.get(player);

    }
}
