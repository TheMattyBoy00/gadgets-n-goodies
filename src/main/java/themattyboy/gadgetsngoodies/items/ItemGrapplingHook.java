package themattyboy.gadgetsngoodies.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import themattyboy.gadgetsngoodies.entity.projectile.EntityGrapplingHook;

import com.google.common.base.Predicate;

public class ItemGrapplingHook extends Item {
	
	private boolean isShot = false;
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		worldIn.playSound((EntityPlayer)null, playerIn.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if(stack.getTagCompound() != null) {
			if(stack.getTagCompound().hasKey("HookInfo")) {
				for(EntityGrapplingHook hook : (List<EntityGrapplingHook>)worldIn.getEntities(EntityGrapplingHook.class, new Predicate() {public boolean func_180094_a(Entity p_180094_1_) {return p_180094_1_ instanceof EntityGrapplingHook;} public boolean apply(Object p_apply_1_) {return this.func_180094_a((Entity)p_apply_1_);}})) {
					if(hook.getUniqueID().toString().equals(stack.getTagCompound().getCompoundTag("HookInfo").getString("HookID"))) {
						hook.setDead();
					}
				}
			}
		}
		if(!this.isShot) {
			EntityGrapplingHook hook = new EntityGrapplingHook(worldIn, playerIn, 1.5F);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setString("HookID", hook.getUniqueID().toString());
			stack.setTagInfo("HookInfo", nbt);
			if(!worldIn.isRemote) {
				worldIn.spawnEntityInWorld(hook);
				this.isShot = true;
			}
			stack.damageItem(1, playerIn);
		}
		else {
			if(!worldIn.isRemote) {
				this.isShot = false;
			}
		}
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
		if(stack.getTagCompound() != null) {
			if(stack.getTagCompound().hasKey("HookInfo")) {
				for(EntityGrapplingHook hook : (List<EntityGrapplingHook>)player.worldObj.getEntities(EntityGrapplingHook.class, new Predicate() {public boolean func_180094_a(Entity p_180094_1_) {return p_180094_1_ instanceof EntityGrapplingHook;} public boolean apply(Object p_apply_1_) {return this.func_180094_a((Entity)p_apply_1_);}})) {
					if(hook.getUniqueID().toString().equals(stack.getTagCompound().getCompoundTag("HookInfo").getString("HookID"))) {
						hook.setDead();
						this.isShot = false;
					}
				}
			}
		}
		return super.onDroppedByPlayer(stack, player);
	}
}