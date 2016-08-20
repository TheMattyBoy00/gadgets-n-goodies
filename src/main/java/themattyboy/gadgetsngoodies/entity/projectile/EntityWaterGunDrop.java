package themattyboy.gadgetsngoodies.entity.projectile;

import net.minecraft.block.BlockCrops;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import themattyboy.gadgetsngoodies.achievements.GadgetAchievements;

public class EntityWaterGunDrop extends EntityThrowable {

	public EntityWaterGunDrop(World worldIn) {
		super(worldIn);
		this.setSize(0.15F, 0.15F);
	}

	public EntityWaterGunDrop(World worldIn, EntityLivingBase shooterIn) {
		super(worldIn, shooterIn);
		this.setSize(0.15F, 0.15F);
	}
	
	public EntityWaterGunDrop(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.setSize(0.15F, 0.15F);
	}
	
	@Override
	public void onEntityUpdate() {
		for (int i = 0; i < 16; ++i) {
            this.worldObj.spawnParticle(EnumParticleTypes.WATER_WAKE, this.posX, this.posY + 0.1, this.posZ, this.motionX * 0.2, this.motionY * 0.2, this.motionZ * 0.2, new int[0]);
        }
		super.onEntityUpdate();
	}

	@Override
	protected void onImpact(RayTraceResult raytraceresult) {
		if(raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
			EnumFacing side = raytraceresult.sideHit;
			BlockPos blockpos = raytraceresult.getBlockPos().offset(side);
			if(this.worldObj.getBlockState(blockpos).getBlock() == Blocks.FIRE) {
				this.worldObj.setBlockState(blockpos, Blocks.AIR.getDefaultState());
				this.worldObj.playSound(blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
				for (int i = 0; i < 8; ++i) {
		            this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, blockpos.getX() + 0.5, blockpos.getY(), blockpos.getZ() + 0.5, 0.0D, 0.0D, 0.0D, new int[0]);
		        }
			}
			if(this.worldObj.getBlockState(this.getPosition()).getBlock() instanceof BlockCrops) {
				BlockCrops wheat = (BlockCrops)this.worldObj.getBlockState(this.getPosition()).getBlock();
				int randchance = this.rand.nextInt(20);
				int i = ((Integer)this.worldObj.getBlockState(this.getPosition()).getValue(wheat.AGE)).intValue();
				if(randchance == 0 && i < 7) {
					i++;
					this.worldObj.setBlockState(this.getPosition(), wheat.getDefaultState().withProperty(wheat.AGE, i));
					if(i == 7 && this.getThrower() instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer)this.getThrower();
						player.addStat(GadgetAchievements.farmer);
					}
				}
			}
		}
		if(raytraceresult.entityHit != null) {
			if(raytraceresult.entityHit instanceof EntityBlaze || raytraceresult.entityHit instanceof EntityEnderman || raytraceresult.entityHit instanceof EntitySnowman) {
				raytraceresult.entityHit.attackEntityFrom(DamageSource.drown, 1.0F);
			}
		}
		if(!this.worldObj.isRemote) {
			this.setDead();
		}
	}

}
