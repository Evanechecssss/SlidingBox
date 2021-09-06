package top.evanechecssss.slidingbox.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import top.evanechecssss.slidingbox.SlidingBox;
import top.evanechecssss.slidingbox.handler.GUIHandler;
import top.evanechecssss.slidingbox.init.AKeyCodeInit;
import top.evanechecssss.slidingbox.network.NetFantomBox;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Evanechecssss
 * \* https://bio.link/evanechecssss
 * \* Description:
 * \
 */
public class FantomBox extends RotatableBlock {


    public FantomBox() {
        super(Material.BARRIER, MapColor.BLACK);
        this.setBlockUnbreakable();
        this.setResistance(6000001.0F);
        this.setRegistryName("fantom_box");
        this.setTranslationKey(SlidingBox.MOD_ID + ".fantom_box");
        this.translucent = true;
        GameRegistry.registerTileEntity(TEFantomBlock.class, getRegistryName().toString());
    }

    public TEFantomBlock getTileEntity(World world, BlockPos pos) {
        return (TEFantomBlock) world.getTileEntity(pos);
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getAmbientOcclusionLightValue(IBlockState state) {
        return 1.0F;
    }

    @Override
    public TEFantomBlock createTileEntity(World world, IBlockState blockState) {
        return new TEFantomBlock();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TEFantomBlock te = getTileEntity(worldIn,pos);
        if (!worldIn.isRemote){
            te.setCord(pos.getX(),pos.getY(),pos.getZ());
            SlidingBox.getWrapper().sendToAll(new NetFantomBox.FantomBoxMessagePos(pos,pos));
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TEFantomBlock te = getTileEntity(worldIn,pos);
        if (!worldIn.isRemote) {
            if (playerIn.isCreative()) {
                playerIn.openGui(SlidingBox.INSTANCE, GUIHandler.FANTOM_BOX, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
            if (AKeyCodeInit.PUSH.isKeyDown()){
            switch (te.getSideStates()){
                case 1:
                    if (facing==EnumFacing.NORTH){
                        doSliding(pos,pos.south(),te,worldIn);
                    }
                    break;
                case 2:
                    if (facing==EnumFacing.NORTH){
                        doSliding(pos,pos.south(),te,worldIn);
                    }else if(facing==EnumFacing.SOUTH){
                        doSliding(pos,pos.north(),te,worldIn);
                    }
                    break;
                case 3:
                    if (facing==EnumFacing.NORTH){
                        doSliding(pos,pos.south(),te,worldIn);
                    }else if(facing==EnumFacing.SOUTH){
                        doSliding(pos,pos.north(),te,worldIn);
                    }else if(facing==EnumFacing.EAST){
                        doSliding(pos,pos.west(),te,worldIn);
                    }else if(facing==EnumFacing.WEST){
                        doSliding(pos,pos.east(),te,worldIn);
                    }
                    break;
            }}

        }
        return true;
    }

    private void doSliding(BlockPos pos,BlockPos slidingPos,TEFantomBlock te,World world){
        te.setCord(slidingPos.getX(),slidingPos.getY(),slidingPos.getZ());
        SlidingBox.getWrapper().sendToAll(new NetFantomBox.FantomBoxMessagePos(pos,slidingPos));
    }

    @Override
    public boolean hasTileEntity(IBlockState blockState) {
        return true;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        TEFantomBlock te = (TEFantomBlock) worldIn.getTileEntity(pos);
        if (te == null) {
            return FULL_BLOCK_AABB;
        }
        if (te.containBlock()) {
            return te.getBlock().getCollisionBoundingBox(te.getBlock().getDefaultState(), worldIn, pos);
        } else {
            return FULL_BLOCK_AABB;
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        TEFantomBlock te = (TEFantomBlock) source.getTileEntity(pos);
        if (te == null) {
            return FULL_BLOCK_AABB;
        }
        if (te.containBlock()) {
            return te.getBlock().getBoundingBox(te.getBlock().getDefaultState(), source, pos);
        } else {
            return FULL_BLOCK_AABB;
        }
    }

}