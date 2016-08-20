package themattyboy.gadgetsngoodies.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import themattyboy.gadgetsngoodies.blocks.BlockPlacedTNTCannon;
import themattyboy.gadgetsngoodies.init.GadgetBlocks;
import themattyboy.gadgetsngoodies.init.GadgetItems;

public class ItemPortableTNTCannonLoaded extends Item {
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player, EnumHand hand) {
		boolean flag = player.inventory.hasItemStack(new ItemStack(Item.getItemFromBlock(Blocks.TNT))) || player.capabilities.isCreativeMode;
		if(flag && !player.isSneaking()) {
			if(!player.capabilities.isCreativeMode) {
				player.inventory.clearMatchingItems(Item.getItemFromBlock(Blocks.TNT), -1, 1, null);
			}
			double d1 = 32.5D;
			Vec3d vec3 = player.getLook(1.0F);
			double d2 = player.posX - (player.posX - vec3.xCoord * d1);
			double d3 = player.posY - (player.posY - vec3.yCoord * d1);
			double d4 = player.posZ - (player.posZ - vec3.zCoord * d1);
			EntityTNTPrimed primedtnt = new EntityTNTPrimed(worldIn, player.getPosition().getX() + 0.5F, player.getPosition().getY() + 1.5F, player.getPosition().getZ() + 0.5F, player);
			primedtnt.motionX = d2 / 40;
			primedtnt.motionY = d3 / 40;
			primedtnt.motionZ = d4 / 40;
			worldIn.playSound((EntityPlayer)null, primedtnt.getPosition(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
			worldIn.playSound((EntityPlayer)null, player.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F) * 0.7F);
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX, player.posY + 1.5, player.posZ, 1.0D, 0.0D, 0.0D, new int[0]);
			if(!worldIn.isRemote) {
				worldIn.spawnEntityInWorld(primedtnt);
			}
			stack.setItem(GadgetItems.portable_TNT_cannon_empty);
		}
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(playerIn.isSneaking()) {
			pos = pos.offset(side);
			worldIn.setBlockState(pos, GadgetBlocks.placed_portable_TNT_cannon.getDefaultState().withProperty(BlockPlacedTNTCannon.FACING, playerIn.getHorizontalFacing()).withProperty(BlockPlacedTNTCannon.HAS_TNT_PROPELLER, Boolean.valueOf(true)));
			worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F, false);
			--stack.stackSize;
		}
		return EnumActionResult.SUCCESS;
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		String status = I18n.format("tnt_cannon.status.loaded", new Object[0]);
		String place = I18n.format("tnt_cannon.place", new Object[0]);
		tooltip.add(status);
		tooltip.add(place);
	}
}
