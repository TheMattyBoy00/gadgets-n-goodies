package themattyboy.gadgetsngoodies.items;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import themattyboy.gadgetsngoodies.achievements.GadgetAchievements;
import themattyboy.gadgetsngoodies.init.GadgetItems;

public class ItemFireballDispenser extends Item {
	
	public static int minExplosionPower;
	public static int maxExplosionPower;
	private int explosionPower = minExplosionPower;
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if(maxExplosionPower < minExplosionPower) {
			maxExplosionPower = minExplosionPower;
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		
		if (!playerIn.isSneaking() && playerIn.capabilities.isCreativeMode || !playerIn.isSneaking() && playerIn.inventory.hasItemStack(new ItemStack(Items.FIRE_CHARGE))) {
			worldIn.playSound((EntityPlayer)null, playerIn.getPosition(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
			double d1 = 32.5D;
			Vec3d vec3 = playerIn.getLook(1.0F);
			double d2 = playerIn.posX - (playerIn.posX - vec3.xCoord * d1);
			double d3 = playerIn.posY - (playerIn.posY - vec3.yCoord * d1);
			double d4 = playerIn.posZ - (playerIn.posZ - vec3.zCoord * d1);
		
			if (!worldIn.isRemote) {
				EntityLargeFireball entitylargefireball = new EntityLargeFireball(worldIn, playerIn, d2, d3, d4);
				entitylargefireball.explosionPower = this.explosionPower;
				entitylargefireball.posY = playerIn.posY + 1.0;
				worldIn.spawnEntityInWorld(entitylargefireball);
			}

			playerIn.addStat(StatList.getObjectUseStats(this));
			
			if(!playerIn.capabilities.isCreativeMode) {
				playerIn.inventory.clearMatchingItems(Items.FIRE_CHARGE, -1, 1, null);
			}
			stack.damageItem(this.explosionPower * 3 + 1, playerIn);
			
			boolean flag = playerIn.inventory.hasItemStack(new ItemStack(Items.COAL)) ||  playerIn.capabilities.isCreativeMode;
			if(playerIn.inventory.armorItemInSlot(2) != null) {
				if(this.explosionPower == 1 && playerIn.inventory.armorItemInSlot(2).getItem() == GadgetItems.jetpack && Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() && !playerIn.capabilities.isFlying && flag) {
					playerIn.addStat(GadgetAchievements.being_the_ghast);
				}
			}
		}
		else if(playerIn.isSneaking()) {
			worldIn.playSound((EntityPlayer)null, playerIn.getPosition(), SoundEvents.UI_BUTTON_CLICK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			if(!worldIn.isRemote) {
				this.explosionPower++;
				if(this.explosionPower > maxExplosionPower) {
					this.explosionPower = minExplosionPower;
				}
			}
		}
		return new ActionResult(EnumActionResult.SUCCESS, stack);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		String power = I18n.format("fireball_dispenser.power", new Object[0]);
		String powerchange = I18n.format("fireball_dispenser.power.change", new Object[0]);
		tooltip.add(power + ": " + explosionPower);
		tooltip.add(powerchange);
	}
}
