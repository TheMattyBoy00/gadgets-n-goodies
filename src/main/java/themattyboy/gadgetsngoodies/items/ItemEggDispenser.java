package themattyboy.gadgetsngoodies.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemEggDispenser extends Item {
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		EntityPlayer playerIn = (EntityPlayer)entityIn;
		if(Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown() && isSelected) {
			if (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItemStack(new ItemStack(Items.EGG))) {
				worldIn.playSound((EntityPlayer)null, playerIn.getPosition(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
				if (!worldIn.isRemote) {
					EntityEgg egg = new EntityEgg(worldIn, playerIn);
					egg.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
					worldIn.spawnEntityInWorld(egg);
				}
				playerIn.addStat(StatList.getObjectUseStats(this));
				if(!playerIn.capabilities.isCreativeMode) {
					playerIn.inventory.clearMatchingItems(Items.EGG, -1, 1, null);
				}
				stack.damageItem(1, playerIn);
			}
		}
	}
}
