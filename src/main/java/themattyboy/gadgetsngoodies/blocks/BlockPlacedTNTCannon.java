package themattyboy.gadgetsngoodies.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import themattyboy.gadgetsngoodies.init.GadgetItems;

public class BlockPlacedTNTCannon extends Block {
	
	public static final PropertyBool HAS_TNT_PROPELLER = PropertyBool.create("has_tnt_propeller");
	public static final PropertyBool HAS_TNT_PROJECTILE = PropertyBool.create("has_tnt_projectile");
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	protected static final AxisAlignedBB TNT_CANNON_NORTH_AABB = new AxisAlignedBB(0.125F, 0.0F, 0.0F, 0.875F, 0.3125F, 0.9375F);
	protected static final AxisAlignedBB TNT_CANNON_SOUTH_AABB = new AxisAlignedBB(0.125F, 0.0F, 0.0625F, 0.875F, 0.3125F, 1.0F);
	protected static final AxisAlignedBB TNT_CANNON_WEST_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.125F, 0.9375F, 0.3125F, 0.875F);
	protected static final AxisAlignedBB TNT_CANNON_EAST_AABB = new AxisAlignedBB(0.0625F, 0.0F, 0.125F, 1.0F, 0.3125F, 0.875F);

	public BlockPlacedTNTCannon(Material materialIn) {
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(HAS_TNT_PROPELLER, Boolean.valueOf(false)).withProperty(HAS_TNT_PROJECTILE, Boolean.valueOf(false)).withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		switch ((EnumFacing)state.getValue(FACING))
        {
            case NORTH:
                return TNT_CANNON_NORTH_AABB;
            case SOUTH:
                return TNT_CANNON_SOUTH_AABB;
            case WEST:
                return TNT_CANNON_WEST_AABB;
            case EAST:
            default:
                return TNT_CANNON_EAST_AABB;
        }
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

	@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		boolean flag = ((Boolean)state.getValue(HAS_TNT_PROPELLER)).booleanValue();
        if(flag) {
        	return GadgetItems.portable_TNT_cannon_loaded;
        }
        else {
        	return GadgetItems.portable_TNT_cannon_empty;
        }
    }
	
	public ItemStack getPickBlock(IBlockState state, RayTraceResult rarget, World worldIn, BlockPos pos, EntityPlayer player) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
        boolean flag = ((Boolean)iblockstate.getValue(HAS_TNT_PROPELLER)).booleanValue();
        if(flag) {
        	return new ItemStack(GadgetItems.portable_TNT_cannon_loaded);
        }
        else {
        	return new ItemStack(GadgetItems.portable_TNT_cannon_empty);
        }
    }
	
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack stack, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean projectile = ((Boolean)state.getValue(HAS_TNT_PROJECTILE)).booleanValue();
		boolean propeller = ((Boolean)state.getValue(HAS_TNT_PROPELLER)).booleanValue();
		if (stack != null) {
            Item item = stack.getItem();

            if (item == Item.getItemFromBlock(Blocks.TNT) && (!projectile || !propeller)) {
            	if(!projectile && !propeller) {
            		worldIn.setBlockState(pos, this.getDefaultState().withProperty(HAS_TNT_PROPELLER, Boolean.valueOf(true)).withProperty(FACING, state.getValue(FACING)));
            		worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F, false);
            		if(!playerIn.capabilities.isCreativeMode) {
            			playerIn.inventory.clearMatchingItems(Item.getItemFromBlock(Blocks.TNT), -1, 1, null);
            		}
            	}
            	else if(!projectile && propeller) {
            		worldIn.setBlockState(pos, this.getDefaultState().withProperty(HAS_TNT_PROJECTILE, Boolean.valueOf(true)).withProperty(HAS_TNT_PROPELLER, Boolean.valueOf(true)).withProperty(FACING, state.getValue(FACING)));
            		worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F, false);
            		if(!playerIn.capabilities.isCreativeMode) {
            			playerIn.inventory.clearMatchingItems(Item.getItemFromBlock(Blocks.TNT), -1, 1, null);
            		}
            	}
                return true;
            }
        }
		if(projectile && propeller) {
			EnumFacing facing = ((EnumFacing)state.getValue(FACING));
			EntityTNTPrimed tntprimed = new EntityTNTPrimed(worldIn, pos.getX() + 0.5F, pos.getY() + 1.0F, pos.getZ() + 0.5F, playerIn);
			tntprimed.motionY = 0.5;
			if(facing == EnumFacing.NORTH) {
				tntprimed.motionZ = -0.6;
			}
			if(facing == EnumFacing.EAST) {
				tntprimed.motionX = 0.6;
			}
			if(facing == EnumFacing.SOUTH) {
				tntprimed.motionZ = 0.6;
			}
			if(facing == EnumFacing.WEST) {
				tntprimed.motionX = -0.6;
			}
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
			worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F, false);
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, pos.getX(), pos.getY() + 1.5, pos.getZ(), 1.0D, 0.0D, 0.0D, new int[0]);
			if(!worldIn.isRemote) {
				worldIn.spawnEntityInWorld(tntprimed);
			}
			worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, state.getValue(FACING)));
			return true;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, stack, side, hitX, hitY, hitZ);
    }
	
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(HAS_TNT_PROPELLER, Boolean.valueOf((meta & 1) > 0)).withProperty(HAS_TNT_PROJECTILE, Boolean.valueOf((meta & 2) > 0)).withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    public int getMetaFromState(IBlockState state) {
    	byte b0 = 0;
    	int i = b0 | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();

        if (((Boolean)state.getValue(HAS_TNT_PROJECTILE)).booleanValue()) {
            i |= 8;
        }

        if (((Boolean)state.getValue(HAS_TNT_PROPELLER)).booleanValue()) {
            i |= 4;
        }

        return i;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {HAS_TNT_PROPELLER, HAS_TNT_PROJECTILE, FACING});
    }
    
    static final class SwitchEnumFacing {
        static final int[] FACING_LOOKUP = new int[EnumFacing.values().length];
        private static final String __OBFID = "CL_00002104";

        static {
            try {
                FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 1;
            }
            catch (NoSuchFieldError var4) {
                ;
            }

            try {
                FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 2;
            }
            catch (NoSuchFieldError var3) {
                ;
            }

            try {
                FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 3;
            }
            catch (NoSuchFieldError var2) {
                ;
            }

            try {
                FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 4;
            }
            catch (NoSuchFieldError var1) {
                ;
            }
        }
    }
}
