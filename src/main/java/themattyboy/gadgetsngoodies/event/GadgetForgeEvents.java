package themattyboy.gadgetsngoodies.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import themattyboy.gadgetsngoodies.init.GadgetItems;

public class GadgetForgeEvents {

	/*@SubscribeEvent
	public void onAttackTargetSet(LivingSetAttackTargetEvent event) {
		if(event.entityLiving instanceof EntityGuardian && event.target instanceof EntityPlayer) {
			EntityGuardian guardian = (EntityGuardian)event.entity;
			if(event.target.getCurrentArmor(1) != null) {
				if(event.target.getCurrentArmor(1).getItem() == GadgetItems.wetsuit) {
					guardian.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
				}
				else {
					guardian.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
				}
			}
			else {
				guardian.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
			}
		}
	}*/
	
	@SubscribeEvent
	public void onLivingRender(RenderLivingEvent.Pre event) {
		if(event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntity();
			if(player.getHeldItemMainhand() != null) {
				ItemStack stack = player.getHeldItemMainhand();
				if(stack.getItem() == GadgetItems.hang_glider) {
					ModelPlayer model = (ModelPlayer)event.getRenderer().getMainModel();
					if(Minecraft.getMinecraft().gameSettings.mainHand == EnumHandSide.RIGHT) {
						model.rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
					}
					else {
						model.leftArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
					}
				}
			}
		}
	}
}