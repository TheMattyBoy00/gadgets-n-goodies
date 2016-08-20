package themattyboy.gadgetsngoodies.entity.projectile;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFlameThrowerFlame extends EntityThrowable {

	public EntityFlameThrowerFlame(World worldIn) {
		super(worldIn);
		this.setSize(0.15F, 0.15F);
	}

	public EntityFlameThrowerFlame(World worldIn, EntityLivingBase shooterIn) {
		super(worldIn, shooterIn);
		this.setSize(0.15F, 0.15F);
	}
	
	public EntityFlameThrowerFlame(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.setSize(0.15F, 0.15F);
	}
	
	@Override
	protected float getGravityVelocity() {
        return 0.0F;
    }
	
	@Override
	public void onEntityUpdate() {
		for (int i = 0; i < 16; ++i) {
            this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY - 0.1, this.posZ, this.motionX * 0.9, this.motionY * 0.9, this.motionZ * 0.9, new int[0]);
        }
		if(this.ticksExisted >= 20) {
			this.setDead();
		}
		if(this.isInsideOfMaterial(Material.WATER)) {
			for (int i = 0; i < 8; ++i) {
	            this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY - 0.1, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
	        }
			this.worldObj.playSound(this.posX, this.posY, this.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			this.setDead();
		}
		super.onEntityUpdate();
	}

	@Override
	protected void onImpact(RayTraceResult raytraceresult) {
		if(raytraceresult.entityHit != null) {
			raytraceresult.entityHit.setFire(5);
		}
		if(raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
			EnumFacing side = raytraceresult.sideHit;
			BlockPos blockpos = raytraceresult.getBlockPos().offset(side);
			if(this.worldObj.getBlockState(blockpos).getBlock() == Blocks.AIR) {
				this.worldObj.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
			}
		}
		if(!this.worldObj.isRemote) {
			this.setDead();
		}
	}

}
