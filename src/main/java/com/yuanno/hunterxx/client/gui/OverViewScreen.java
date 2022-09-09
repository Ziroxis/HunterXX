package com.yuanno.hunterxx.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CRequestSyncWorldDataPacket;
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
public class OverViewScreen extends Screen {

    private final ResourceLocation overviewScreen = new ResourceLocation(Main.MODID, "textures/gui/overview.png");
    private final ResourceLocation basicButton = new ResourceLocation(Main.MODID, "textures/gui/basic_button.png");

    private final int xSize = 64;
    private final int ySize = 58;

    private int guiLeft;
    private int guiTop;



    private final PlayerEntity player;

    public OverViewScreen()
    {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
    }

    @Override
    public void init()
    {
        PacketHandler.sendToServer(new CRequestSyncWorldDataPacket());

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;

        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;

        TexturedIconButton statsButton = new TexturedIconButton(basicButton, posX + 60, posY + 50, 64, 16, new TranslationTextComponent("gui.hunterxx.stats"), b ->
        {
           Minecraft.getInstance().setScreen(new StatsScreen(this.player));
        });
        statsButton = statsButton.setTextInfo(posX + 70, posY + 48, 0.95);
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
