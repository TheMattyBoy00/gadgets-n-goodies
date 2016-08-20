package themattyboy.gadgetsngoodies.items.armor;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import themattyboy.gadgetsngoodies.init.GadgetItems;
import themattyboy.gadgetsngoodies.main.Reference;

public class ItemFlippers extends ItemArmor {
	private String [] armourTypes = new String [] {"", "", "", "flippers"};
	
	public ItemFlippers(ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot slot) {
		super(armorMaterial, renderIndex, slot);
	}
	
	/*@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
		if(stack.getItem().equals(GadgetItems.flippers)) {
			return Reference.MOD_ID + ":textures/armor/diving_1.png";
		}
		
		else return null;
	}*/
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		ItemStack equippedFeet = player.inventory.armorItemInSlot(0);
		if(equippedFeet != null) {
			if(equippedFeet.getItem() == GadgetItems.flippers) {
				if(!player.isInWater()) {
					if(player.onGround) {
						player.motionX *= 0.5;
						player.motionZ *= 0.5;
					}
				}
				else if(player.isInWater() && !player.capabilities.isFlying) {
					player.motionX *= 1.2;
					player.motionY = 0;
					if(Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {
						player.motionY = 0.2;
					}
					if(player.isSneaking()) {
						player.motionY = -0.2;
					}
					player.motionZ *= 1.2;
				}
			}
		}
	}
}
