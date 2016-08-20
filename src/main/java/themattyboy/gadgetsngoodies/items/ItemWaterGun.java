package themattyboy.gadgetsngoodies.items;

import java.util.List;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import themattyboy.gadgetsngoodies.entity.projectile.EntityWaterGunDrop;

public class ItemWaterGun extends Item {

	public static int maxWaterLevel;
	private int waterLevel = maxWaterLevel;
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		EntityPlayer player = (EntityPlayer)entityIn;
		if(Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown() && isSelected && (waterLevel > 0 || player.capabilities.isCreativeMode)) {
			worldIn.playSound((EntityPlayer)null, player.getPosition(), SoundEvents.ENTITY_GENERIC_SWIM, SoundCategory.NEUTRAL, 0.4F, 1.0F);
			stack.damageItem(1, player);
			if (!worldIn.isRemote) {
				EntityWaterGunDrop drop = new EntityWaterGunDrop(worldIn, player);
				drop.setHeadingFromThrower(entityIn, entityIn.rotationPitch, entityIn.rotationYaw, 0.0F, 0.8F, 1.0F);
				worldIn.spawnEntityInWorld(drop);
			}
			if(!player.capabilities.isCreativeMode) {
				waterLevel--;
			}
		}
	}
	
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

        if (raytraceresult == null) {
        	return new ActionResult(EnumActionResult.SUCCESS, stack);
        }
        else {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
                BlockPos blockpos = raytraceresult.getBlockPos();

                if (!playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, stack)) {
                	return new ActionResult(EnumActionResult.SUCCESS, stack);
                }

                IBlockState iblockstate = worldIn.getBlockState(blockpos);
                Material material = iblockstate.getMaterial();

                if (material == Material.WATER && ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0) {
                    worldIn.setBlockToAir(blockpos);
                    playerIn.addStat(StatList.getObjectUseStats(this));
                    this.waterLevel = maxWaterLevel;
                }

                BlockPos blockpos1 = blockpos.offset(raytraceresult.sideHit);

                if (!playerIn.canPlayerEdit(blockpos1, raytraceresult.sideHit, stack)) {
                	return new ActionResult(EnumActionResult.SUCCESS, stack);
                }
            }

            return new ActionResult(EnumActionResult.SUCCESS, stack);
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		String waterlevel1 = I18n.format("water_gun.water_level", new Object[0]);
		//String waterlevel2 = I18n.format("water_gun.water_level_percent", new Object[0]);
		tooltip.add(waterlevel1 + " " + this.waterLevel + "/" + this.maxWaterLevel);
	}
}
