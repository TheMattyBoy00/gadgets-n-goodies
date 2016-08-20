package themattyboy.gadgetsngoodies.items.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import themattyboy.gadgetsngoodies.achievements.GadgetAchievements;
import themattyboy.gadgetsngoodies.init.GadgetItems;
import themattyboy.gadgetsngoodies.main.Reference;

public class ItemFlyArmor extends ItemArmor {
	
	private String [] armorTypes = new String [] {"", "jetpack", "", ""};
	
	private int coalTimer = 0;
	
	public static float maxCoalTimer;
	
	public ItemFlyArmor(ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
		super(material, renderIndex, slot);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		ItemStack equippedHelmet = player.inventory.armorItemInSlot(3);
		ItemStack equippedBody = player.inventory.armorItemInSlot(2);
		ItemStack equippedLeg = player.inventory.armorItemInSlot(1);
		ItemStack equippedFeet = player.inventory.armorItemInSlot(0);
		if((equippedHelmet != null || player.capabilities.isCreativeMode) && equippedBody != null) {
			if(equippedBody.getItem() == GadgetItems.jetpack) {
				boolean flag = player.inventory.hasItemStack(new ItemStack(Items.COAL)) || player.capabilities.isCreativeMode;
				if(Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() && !player.capabilities.isFlying && flag) {
					player.motionY += 0.2;
					if(player.motionY >= 0.5) {
						player.motionY = 0.5;
					}
					player.motionX *= 1.1;
					player.motionZ *= 1.1;
					if(player.motionX >= 0.5) {
						player.motionX = 0.5;
					}
					if(player.motionZ >= 0.5) {
						player.motionZ = 0.5;
					}
					if(player.motionX <= -0.5) {
						player.motionX = -0.5;
					}
					if(player.motionZ <= -0.5) {
						player.motionZ = -0.5;
					}
					player.fallDistance = 0;
					for (int i = 0; i < 16; ++i) {
						world.spawnParticle(EnumParticleTypes.FLAME, player.posX, player.posY + 1, player.posZ, 0.01D, -1.0D, 0.0D, new int[0]);
			            world.spawnParticle(EnumParticleTypes.FLAME, player.posX - (0.25 * MathHelper.sin(player.rotationYaw  * 0.017453292F - (float)Math.PI / 2)), player.posY + 1, player.posZ + (0.25 * MathHelper.cos(player.rotationYaw  * 0.017453292F - (float)Math.PI / 2)), 0.01D, -1.0D, 0.0D, new int[0]);
			            world.spawnParticle(EnumParticleTypes.FLAME, player.posX + (0.25 * MathHelper.sin(player.rotationYaw  * 0.017453292F - (float)Math.PI / 2)), player.posY + 1, player.posZ - (0.25 * MathHelper.cos(player.rotationYaw  * 0.017453292F - (float)Math.PI / 2)), 0.01D, -1.0D, 0.0D, new int[0]);
			        }
					world.playSound((EntityPlayer)null, player.getPosition(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 0.2F, 0.8F);
					if(player.posY >= 1000) {
						player.addStat(GadgetAchievements.high_flier);
					}
					if(!world.isRemote) {
						if(coalTimer < this.maxCoalTimer * 20) {
							++coalTimer;
						}
					}
					if(coalTimer >= this.maxCoalTimer * 20 && !player.capabilities.isCreativeMode) {
						player.inventory.clearMatchingItems(Items.COAL, -1, 1, null);
						stack.damageItem(1, player);
						coalTimer = 0;
					}
				}
				else if(Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed() && !player.capabilities.isFlying && !flag) {
					player.addChatMessage(new TextComponentTranslation("out.of.fuel", new Object[0]));
				}
			}
		}
		if((equippedHelmet == null && !player.capabilities.isCreativeMode) && equippedBody != null) {
			if(equippedBody.getItem() == GadgetItems.jetpack) {
				if(Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed() && !player.capabilities.isFlying) {
					player.addChatMessage(new TextComponentTranslation("safety.protocol.violation", new Object[0]));
				}
			}
		}
	}
}
