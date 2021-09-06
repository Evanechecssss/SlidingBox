package top.evanechecssss.slidingbox.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import top.evanechecssss.slidingbox.SlidingBox;
import top.evanechecssss.slidingbox.common.ContainerFantomBox;
import top.evanechecssss.slidingbox.common.TEFantomBlock;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Evanechecssss
 * \* https://bio.link/evanechecssss
 * \* Description:
 * \
 */
public class GUIFantomBox extends GuiContainer {
    public static final int WIDTH = 169;
    public static final int HEIGHT = 159;

    private TEFantomBlock te;

    private static final ResourceLocation background = new ResourceLocation(SlidingBox.MOD_ID, "textures/gui/fantom_box.png");

    public GUIFantomBox(TEFantomBlock tileEntity, ContainerFantomBox container) {
        super(container);
        te = tileEntity;
        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}