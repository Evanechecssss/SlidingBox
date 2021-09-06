package top.evanechecssss.slidingbox.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import top.evanechecssss.slidingbox.common.TEFantomBlock;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Evanechecssss
 * \* https://bio.link/evanechecssss
 * \* Description:
 * \
 */
public class TESRFantomBox extends TileEntitySpecialRenderer<TEFantomBlock> {

    public TESRFantomBox() {
        super();
    }


    @Override
    public void render(TEFantomBlock te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (te.getStack().isEmpty()){
            return;
        }
        if (te.containBlock()){
            renderBlock(te,x,y,z,partialTicks,destroyStage,alpha);
        }else {
            renderItem(te,x,y,z,partialTicks,destroyStage,alpha);
        }


    }
    @SideOnly(Side.CLIENT)
    private void setAnimation(TEFantomBlock te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
        double[] direction = te.getCord();

        double d_x = direction[0];
        double d_y = direction[1];
        double d_z = direction[2];
        GlStateManager.translate(d_x-te.getPos().getX(),d_y-te.getPos().getY(),d_z-te.getPos().getZ());
    }
    @SideOnly(Side.CLIENT)
    private void renderItem(TEFantomBlock te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 8 / 16F, y + 8 / 16F, z + 8 / 16F);
        setAnimation(te,x,y,z,partialTicks,destroyStage,alpha);
        RenderItem render = Minecraft.getMinecraft().getRenderItem();
        IBakedModel model = render.getItemModelWithOverrides(te.getStack(), te.getWorld(), Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        render.renderItem(te.getStack(), model);

        GlStateManager.popMatrix();
    }
    @SideOnly(Side.CLIENT)
    private void renderBlock(TEFantomBlock te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z+1);
        setAnimation(te,x,y,z,partialTicks,destroyStage,alpha);
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        blockrendererdispatcher.renderBlockBrightness(te.getBlock().getDefaultState(), 1.0F);
        GlStateManager.popMatrix();
    }
}