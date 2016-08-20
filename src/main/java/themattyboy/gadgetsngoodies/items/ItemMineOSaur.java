package themattyboy.gadgetsngoodies.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import themattyboy.gadgetsngoodies.entity.mob.EntityMineOSaur;

public class ItemMineOSaur extends Item {
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		EntityMineOSaur entitymineosaur = new EntityMineOSaur(worldIn);
		entitymineosaur.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
		entitymineosaur.setOwnerId(playerIn.getUniqueID());
		if(!worldIn.isRemote) {
			worldIn.spawnEntityInWorld(entitymineosaur);
		}
		worldIn.playSound((EntityPlayer)null, entitymineosaur.getPosition(), SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, SoundCategory.NEUTRAL, 1.0F, 1.0F);
		if(!playerIn.capabilities.isCreativeMode) {
			playerIn.inventory.clearMatchingItems(this, -1, 1, null);
		}
		return EnumActionResult.SUCCESS;
	}

}
