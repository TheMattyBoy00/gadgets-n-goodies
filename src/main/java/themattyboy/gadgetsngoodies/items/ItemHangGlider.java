package themattyboy.gadgetsngoodies.items;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHangGlider extends Item {
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		EntityPlayer playerIn = (EntityPlayer)entityIn;
		if(isSelected) {
			//playerIn.setItemInUse(stack, 5);
		}
		if(isSelected && entityIn.isSneaking()) {
			entityIn.fallDistance = 0;
			entityIn.motionY *= 0.8;
		}
		if(isSelected && entityIn.isAirBorne && Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() && !playerIn.capabilities.isFlying) {
			entityIn.motionY *= 0.8;
			entityIn.motionX *= 1.1;
			entityIn.motionZ *= 1.1;
			if(playerIn.motionX >= 0.5) {
				playerIn.motionX = 0.5;
			}
			if(playerIn.motionZ >= 0.5) {
				playerIn.motionZ = 0.5;
			}
			if(playerIn.motionX <= -0.5) {
				playerIn.motionX = -0.5;
			}
			if(playerIn.motionZ <= -0.5) {
				playerIn.motionZ = -0.5;
			}
		}
	}
	
	/*@Override
	public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		/*BlockPos blockpos1 = new BlockPos(playerIn.posX, playerIn.posY - 1, playerIn.posZ);
		BlockPos blockpos2 = new BlockPos(playerIn.posX, playerIn.posY - 2, playerIn.posZ);
		boolean flag = worldIn.getBlockState(blockpos1).getBlock() != Blocks.air || worldIn.getBlockState(blockpos2).getBlock() != Blocks.air;
		if(playerIn.fallDistance >= 30 && flag) {
			playerIn.triggerAchievement(GadgetAchievements.risky_dives);
		}
		playerIn.fallDistance = 0;
		return stack;
	}*/
	
	/*@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }*/
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		String eliminatefalldamage = I18n.format("hang_glider.eliminate_fall_damage", new Object[0]);
		tooltip.add(eliminatefalldamage);
	}
}
