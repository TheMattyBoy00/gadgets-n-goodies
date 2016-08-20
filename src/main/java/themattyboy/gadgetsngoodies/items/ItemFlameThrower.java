package themattyboy.gadgetsngoodies.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import themattyboy.gadgetsngoodies.entity.projectile.EntityFlameThrowerFlame;

public class ItemFlameThrower extends Item {
	
	private int coalTimer = 0;
	
	public static float maxCoalTimer;
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		EntityPlayer player = (EntityPlayer)entityIn;
		if(Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown() && isSelected && (player.inventory.hasItemStack(new ItemStack(Items.COAL)) ||  player.capabilities.isCreativeMode)) {
			worldIn.playSound((EntityPlayer)null, player.getPosition(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.2F, 0.8F);
			stack.damageItem(1, player);
			if (!worldIn.isRemote) {
				EntityFlameThrowerFlame flame = new EntityFlameThrowerFlame(worldIn, player);
				flame.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 0.5F, 1.0F);
				worldIn.spawnEntityInWorld(flame);
			}
			if(!worldIn.isRemote) {
				if(coalTimer < this.maxCoalTimer * 20) {
					++coalTimer;
				}
			}
			if(coalTimer >= this.maxCoalTimer * 20 && !player.capabilities.isCreativeMode) {
				player.inventory.clearMatchingItems(Items.COAL, -1, 1, null);
				coalTimer = 0;
			}
		}
	}

}
