package top.evanechecssss.slidingbox.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemRecord;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import top.evanechecssss.slidingbox.SlidingBox;
import top.evanechecssss.slidingbox.common.FantomBox;
import top.evanechecssss.slidingbox.common.TEFantomBlock;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Evanechecssss
 * \* https://bio.link/evanechecssss
 * \* Description:
 * \
 */
public class NetFantomBox {

    public static void register(int discriminator) {
        SlidingBox.getWrapper().registerMessage(FantomBoxMessagePosHandler.class, FantomBoxMessagePos.class, discriminator, Side.CLIENT);
    }

    public static class FantomBoxMessagePos implements IMessage {

        public FantomBoxMessagePos() {
        }

        public FantomBoxMessagePos(BlockPos block, BlockPos sliding_pos) {
            pos_x = block.getX();
            pos_y = block.getY();
            pos_z = block.getZ();
            sliding_pos_x = sliding_pos.getX();
            sliding_pos_y = sliding_pos.getY();
            sliding_pos_z = sliding_pos.getZ();
        }

        public double pos_x = 0;
        public double pos_y = 0;
        public double pos_z = 0;
        public double sliding_pos_x = 0;
        public double sliding_pos_y = 0;
        public double sliding_pos_z = 0;

        @Override
        public void fromBytes(ByteBuf buf) {
            this.pos_x = buf.readDouble();
            this.pos_y = buf.readDouble();
            this.pos_z = buf.readDouble();
            this.sliding_pos_x = buf.readDouble();
            this.sliding_pos_y = buf.readDouble();
            this.sliding_pos_z = buf.readDouble();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeDouble(pos_x);
            buf.writeDouble(pos_y);
            buf.writeDouble(pos_z);
            buf.writeDouble(sliding_pos_x);
            buf.writeDouble(sliding_pos_y);
            buf.writeDouble(sliding_pos_z);
        }
    }

    public static class FantomBoxMessagePosHandler implements IMessageHandler<FantomBoxMessagePos, IMessage> {

        @SideOnly(Side.CLIENT)
        private void writeToClient(BlockPos blockPos, BlockPos sliding_pos) {
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            TileEntity entity = player.world.getTileEntity(blockPos);
            if (entity instanceof TEFantomBlock) {
                ((TEFantomBlock) entity).setCord(sliding_pos.getX(),sliding_pos.getY(),sliding_pos.getZ());
            }
        }

        @Override
        public IMessage onMessage(FantomBoxMessagePos message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                writeToClient(new BlockPos(message.pos_x,message.pos_y,message.pos_z),new BlockPos(message.sliding_pos_x,message.sliding_pos_y,message.sliding_pos_z));
            });
            return null;
        }
    }

}