package themattyboy.gadgetsngoodies.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import themattyboy.gadgetsngoodies.achievements.GadgetAchievements;
import themattyboy.gadgetsngoodies.init.GadgetSounds;

public class ItemMusicalInstrument extends Item {

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		float rotationY = -playerIn.rotationPitch / 120 + 1.25F;
		worldIn.playSound((EntityPlayer)null, playerIn.getPosition(), SoundEvent.REGISTRY.getObject(new ResourceLocation("minecraft", GadgetSounds.musicalInstrumentSoundName)), SoundCategory.PLAYERS, 1.0F, (float) (rotationY));
		if(playerIn.rotationPitch >= 5.3 && playerIn.rotationPitch <= 7.1) {
			playerIn.addStat(GadgetAchievements.absolute_pitch);
		}
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		if(GadgetSounds.musicalInstrumentSoundName.equals("block.note.bass")) {
			player.addStat(GadgetAchievements.drop_the_bass);
			player.worldObj.playSound((EntityPlayer)null, player.getPosition(), GadgetSounds.bass_drop, SoundCategory.PLAYERS, 1.0F, 1.0F);
		}
		return true;
	}
}
