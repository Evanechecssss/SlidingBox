package top.evanechecssss.slidingbox.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import top.evanechecssss.slidingbox.client.GUIFantomBox;
import top.evanechecssss.slidingbox.common.ContainerFantomBox;
import top.evanechecssss.slidingbox.common.TEFantomBlock;

import javax.annotation.Nullable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Evanechecssss
 * \* https://bio.link/evanechecssss
 * \* Description:
 * \
 */
public class GUIHandler implements IGuiHandler {
    public static final int FANTOM_BOX = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if(ID == FANTOM_BOX) {
            TEFantomBlock te = (TEFantomBlock) world.getTileEntity(pos);
            return new ContainerFantomBox(player.inventory, te);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if(ID == FANTOM_BOX) {
            TEFantomBlock te = (TEFantomBlock) world.getTileEntity(pos);
            return new GUIFantomBox(te, new ContainerFantomBox(player.inventory, te));
        }
        return null;
    }
}