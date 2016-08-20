package themattyboy.gadgetsngoodies.items;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import themattyboy.gadgetsngoodies.achievements.GadgetAchievements;

public class ItemBlockOCopter extends Item {
	
	private int timesUsedOnTNT = 0;
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		worldIn.playSound((EntityPlayer)null, playerIn.getPosition(), SoundEvents.ENTITY_FIREWORK_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!worldIn.isRemote) {
			stack.damageItem(1, playerIn);
			Block block = worldIn.getBlockState(pos).getBlock();
			if(block != Blocks.BEDROCK && block != Blocks.DOUBLE_PLANT && block != Blocks.BED && block != Blocks.STANDING_SIGN && block != Blocks.WALL_SIGN && block != Blocks.FLOWER_POT && block != Blocks.SKULL && block != Blocks.STANDING_BANNER && block != Blocks.WALL_BANNER && block != Blocks.OAK_DOOR && block != Blocks.IRON_DOOR && block != Blocks.BIRCH_DOOR && block != Blocks.SPRUCE_DOOR && block != Blocks.JUNGLE_DOOR && block != Blocks.DARK_OAK_DOOR && block != Blocks.ACACIA_DOOR && block != Blocks.COMMAND_BLOCK && block != Blocks.MOB_SPAWNER && block != Blocks.TNT) {
				EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
				if(block == Blocks.OBSIDIAN) {
					entityfallingblock.motionY = 1;
				}
				else {
					entityfallingblock.motionY = 2;
				}
				entityfallingblock.shouldDropItem = false;
				worldIn.spawnEntityInWorld(entityfallingblock);
			}
			else if(block == Blocks.TNT) {
				++timesUsedOnTNT;
				if(timesUsedOnTNT >= 20) {
					playerIn.addStat(GadgetAchievements.july4th);
				}
				EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, playerIn);
				entitytntprimed.motionY = 2;
				entitytntprimed.setFuse(50);
				worldIn.playSound((EntityPlayer)null, entitytntprimed.getPosition(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
				worldIn.spawnEntityInWorld(entitytntprimed);
				worldIn.setBlockToAir(pos);
			}
		}
		return EnumActionResult.SUCCESS;
    }
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		playerIn.worldObj.playSound((EntityPlayer)null, target.getPosition(), SoundEvents.ENTITY_FIREWORK_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		target.motionY = 2;
		if(target instanceof EntityPig) {
			playerIn.addStat(GadgetAchievements.flying_pig);
		}
        return true;
    }
}
