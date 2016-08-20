package themattyboy.gadgetsngoodies.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import themattyboy.gadgetsngoodies.init.GadgetItems;
import themattyboy.gadgetsngoodies.main.Reference;

public class ItemDivingArmor extends ItemArmor {
	private String [] armourTypes = new String [] {"diving_helmet", "oxygen_tank", "wetsuit", ""};
	
	public ItemDivingArmor(ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot slot) {
		super(armorMaterial, renderIndex, slot);
	}
	
	/*@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
		if(stack.getItem().equals(GadgetItems.diving_helmet) || stack.getItem().equals(GadgetItems.oxygen_tank)) {
			return Reference.MOD_ID + ":textures/armor/diving_1.png";
		}
		
		if(stack.getItem().equals(GadgetItems.wetsuit)) {
			return Reference.MOD_ID + ":textures/armor/diving_2.png";
		}
		
		else return null;
	}*/
	
	/*@Override
	public EnumRarity getRarity(ItemStack stack)
    {
        return stack.getItem() == GadgetItems.diving_helmet ? EnumRarity.COMMON : stack.isItemEnchanted() && stack.getItem() != GadgetItems.diving_helmet ? EnumRarity.RARE : EnumRarity.COMMON;
    }*/
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		ItemStack equippedHelmet = player.inventory.armorItemInSlot(3);
		ItemStack equippedBody = player.inventory.armorItemInSlot(2);
		ItemStack equippedLegs = player.inventory.armorItemInSlot(1);
		if(equippedBody != null && equippedHelmet != null) {
			if(equippedHelmet.getItem() == GadgetItems.diving_helmet && equippedBody.getItem() == GadgetItems.oxygen_tank) {
				//player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 2, 0, true, false));
			}
		}
		if(equippedHelmet != null) {
			if(equippedHelmet.getItem() == GadgetItems.diving_helmet) {
				/*if(EnchantmentHelper.getRespiration(player) == 0) {
					equippedHelmet.addEnchantment(Enchantment.respiration, 3);
				}*/
				if(player.isInWater()) {
					
				}
			}
		}
		/*if(equippedLegs != null) {
			if(equippedLegs.getItem() == GadgetItems.wetsuit) {
				player.isEntityInvulnerable(DamageSource.magic);
			}
		}*/
	}
}
