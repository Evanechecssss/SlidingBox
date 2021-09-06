package top.evanechecssss.slidingbox.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import top.evanechecssss.slidingbox.common.FantomBox;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Evanechecssss
 * \* https://bio.link/evanechecssss
 * \* Description:
 * \
 */
public class ABlocksInit {

    public static final Block BOX = new FantomBox();
    public static final Block[] LIST = new Block[] {
            BOX
    };

    public static void registerBlock() {
        setRegister(LIST);
    }

    public static void setRegister(Block[] block) {
        for (Block value : block) {
            ForgeRegistries.BLOCKS.register(value);
            ForgeRegistries.ITEMS.register(new ItemBlock(value).setRegistryName(value.getRegistryName()));
        }
    }

    @SideOnly(Side.CLIENT)
    public static void renderBlock() {
        setRender(LIST);
    }

    @SideOnly(Side.CLIENT)
    public static void setRender(Block[] block) {
        for (Block value : block) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(value), 0, new ModelResourceLocation(value.getRegistryName(), "inventory"));
        }
    }


}
