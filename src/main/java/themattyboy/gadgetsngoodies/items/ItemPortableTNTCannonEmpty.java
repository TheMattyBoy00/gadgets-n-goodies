package themattyboy.gadgetsngoodies.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import themattyboy.gadgetsngoodies.blocks.BlockPlacedTNTCannon;
import themattyboy.gadgetsngoodies.init.GadgetBlocks;
import themattyboy.gadgetsngoodies.init.GadgetItems;

public class ItemPortableTNTCannonEmpty extends Item {

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player, EnumHand hand) {
		boolean flag = player.inventory.hasItemStack(new ItemStack(Item.getItemFromBlock(Blocks.TNT))) || player.capabilities.isCreativeMode;
		if(flag && !player.isSneaking()) {
			if(!player.capabilities.isCreativeMode) {
				player.inventory.clearMatchingItems(Item.getItemFromBlock(Blocks.TNT), -1, 1, null);
			}
			stack.setItem(GadgetItems.portable_TNT_cannon_loaded);
    	}
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(playerIn.isSneaking()) {
			pos = pos.offset(side);
			worldIn.setBlockState(pos, GadgetBlocks.placed_portable_TNT_cannon.getDefaultState().withProperty(BlockPlacedTNTCannon.FACING, playerIn.getHorizontalFacing()));
			worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F, false);
			--stack.stackSize;
		}
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		String status = I18n.format("tnt_cannon.status.empty", new Object[0]);
		String load = I18n.format("tnt_cannon.status.load", new Object[0]);
		String place = I18n.format("tnt_cannon.place", new Object[0]);
		tooltip.add(status);
		//tooltip.add(load);
		tooltip.add(place);
	}
}
