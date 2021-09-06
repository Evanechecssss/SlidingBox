package top.evanechecssss.slidingbox.common;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;
import top.evanechecssss.slidingbox.SlidingBox;
import top.evanechecssss.slidingbox.network.NetFantomBox;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Evanechecssss
 * \* https://bio.link/evanechecssss
 * \* Description:
 * \
 */
public class TEFantomBlock extends TileEntity {

    public TEFantomBlock() {

    }

    public ItemStackHandler itemRender = new ItemStackHandler(1);
    /**
     * Side states is handler of which side of block will activate sliding
     * 0 - non sides
     * 1 - North side
     * 2 - North and South side
     * 3 - Every side
     */
    public int sideStates = 3;

    public double x = 0;
    public double y = 0;
    public double z = 0;


    public double[] getCord() {
        return new double[]{x, y, z};
    }

    public void setCord(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        markDirty();
    }
    public ItemStack getStack() {
        return itemRender.getStackInSlot(0);
    }

    public Item getItem() {
        return itemRender.getStackInSlot(0).getItem();
    }

    public Block getBlock() {
        return Block.getBlockFromItem(itemRender.getStackInSlot(0).getItem());
    }

    public boolean containBlock() {
        return itemRender.getStackInSlot(0).getItem() instanceof ItemBlock;
    }

    public int getSideStates() {
        return sideStates;
    }

    public void setSideStates(int sideStates) {
        this.sideStates = sideStates;
        markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("itemRender")) itemRender.deserializeNBT((NBTTagCompound) compound.getTag("itemRender"));
        if (compound.hasKey("sideStates")) sideStates = compound.getInteger("sideStates");
        if (compound.hasKey("Dx")) x = compound.getDouble("Dx");
        if (compound.hasKey("Dy")) y = compound.getDouble("Dy");
        if (compound.hasKey("Dz")) z = compound.getDouble("Dz");
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        compound.setTag("itemRender", itemRender.serializeNBT());
        compound.setInteger("sideStates", sideStates);
        compound.setDouble("Dx",x);
        compound.setDouble("Dy",y);
        compound.setDouble("Dz",z);
        return compound;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, this.getBlockMetadata(), this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }
}