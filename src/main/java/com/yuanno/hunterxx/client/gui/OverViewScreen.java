package com.yuanno.hunterxx.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.TexturedIconButton;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
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

@OnlyIn(Dist.CLIENT)
public class OverViewScreen extends Screen {

    private final ResourceLocation overviewScreen = new ResourceLocation(Main.MODID, "textures/gui/overview.png");
    private final ResourceLocation basicButton = new ResourceLocation(Main.MODID, "textures/gui/basic_button.png");

    private final int xSize = 64;
    private final int ySize = 58;

    private int guiLeft;
    private int guiTop;
    private IEntityStats stats;


    private final PlayerEntity player;

    public OverViewScreen()
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        assert player != null;
        this.stats = EntityStatsCapability.get(player);
    }

    @Override
    public void init()
    {
        PacketHandler.sendToServer(new CRequestSyncWorldDataPacket());

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;

        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        TexturedIconButton test = new TexturedIconButton(basicButton, posX + 600, posY + 500, 64, 16, new TranslationTextComponent(""), b ->
        {
            Minecraft.getInstance().setScreen(new StatsScreen(this.player));
        });
        TexturedIconButton statsButton = new TexturedIconButton(basicButton, posX + 60, posY + 50, 64, 16, new TranslationTextComponent("gui.hunterxx.stats"), b ->
        {
           Minecraft.getInstance().setScreen(new StatsScreen(this.player));
        });
        statsButton = statsButton.setTextInfo(posX + 70, posY + 48, 0.95);
        TexturedIconButton questButton = new TexturedIconButton(basicButton, posX + 60, posY + 70, 64, 16, new TranslationTextComponent("gui.hunterxx.quests"), b ->
        {
            Minecraft.getInstance().setScreen(new QuestScreen(this.player));
        });
        questButton = questButton.setTextInfo(posX + 70, posY + 68, 0.95);
        TexturedIconButton achievementsButton = new TexturedIconButton(basicButton, posX + 60, posY + 90, 64, 16, new TranslationTextComponent("gui.hunterxx.achievements"), b ->
        {
            Minecraft.getInstance().setScreen(new AchievementsScreen(player));
        });
        achievementsButton = achievementsButton.setTextInfo(posX + 58, posY + 88, 0.95);
        TexturedIconButton abilitiesButton = new TexturedIconButton(basicButton, posX + 60, posY + 110, 64, 16, new TranslationTextComponent("gui.hunterxx.abilities"), b ->
        {
            Minecraft.getInstance().setScreen(new SelectHotbarAbilitiesScreen(player));
        });
        abilitiesButton = abilitiesButton.setTextInfo(posX + 70, posY + 108, 0.95);

        this.addButton(test);
        this.addButton(abilitiesButton);
        this.addButton(achievementsButton);
        this.addButton(questButton);
        this.addButton(statsButton);
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        this.renderBackground(matrixStack);
        overViewRendering(matrixStack);


        super.render(matrixStack, x, y, f);
    }

    public void overViewRendering(MatrixStack matrixStack) {
        int scaledWidth = this.getMinecraft().getWindow().getGuiScaledWidth();

        minecraft.getTextureManager().bind(overviewScreen);
        GuiUtils.drawTexturedModalRect((int) ((scaledWidth / 2) - 128), 0, 0, 0, 256, 256, 0);
        drawString(matrixStack, font, TextFormatting.BOLD + "Jenny: " + TextFormatting.RESET +  stats.getJenny(), guiLeft + 90 , guiTop + 100, 16777215);

    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new OverViewScreen());
    }
}
