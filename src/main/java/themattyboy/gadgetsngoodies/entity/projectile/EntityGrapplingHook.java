package themattyboy.gadgetsngoodies.entity.projectile;

import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGrapplingHook extends Entity implements IProjectile {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private int inData;
    private boolean inGround;
    public int canBePickedUp;
    public int hookShake;
    public Entity shootingEntity;
    private String shootingEntityUUID;
    private int ticksInAir;
	
	public double destdistX;
	public double destdistY;
	public double destdistZ;

    public EntityGrapplingHook(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
    }

    public EntityGrapplingHook(World worldIn, double x, double y, double z) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
        this.setPosition(x, y, z);
    }

    public EntityGrapplingHook(World worldIn, EntityLivingBase shooter, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_) {
        super(worldIn);
        this.shootingEntity = shooter;

        if (shooter instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }

        this.posY = shooter.posY + (double)shooter.getEyeHeight() - 0.10000000149011612D;
        double d0 = p_i1755_3_.posX - shooter.posX;
        double d1 = p_i1755_3_.getEntityBoundingBox().minY + (double)(p_i1755_3_.height / 3.0F) - this.posY;
        double d2 = p_i1755_3_.posZ - shooter.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D) {
            float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(shooter.posX + d4, this.posY, shooter.posZ + d5, f2, f3);
            float f4 = (float)(d3 * 0.20000000298023224D);
            this.setThrowableHeading(d0, d1 + (double)f4, d2, p_i1755_4_, p_i1755_5_);
        }
    }

    public EntityGrapplingHook(World worldIn, EntityLivingBase shooter, float p_i1756_3_) {
        super(worldIn);
        this.shootingEntity = shooter;

        if (shooter instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }

        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(shooter.posX, shooter.posY + (double)shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, p_i1756_3_ * 1.5F, 1.0F);
    }

    public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
        float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
        x /= (double)f2;
        y /= (double)f2;
        z /= (double)f2;
        x += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)inaccuracy;
        y += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)inaccuracy;
        z += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)inaccuracy;
        x *= (double)velocity;
        y *= (double)velocity;
        z *= (double)velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f3 = MathHelper.sqrt_double(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, (double)f3) * 180.0D / Math.PI);
    }

    @SideOnly(Side.CLIENT)
    public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
        this.setPosition(p_180426_1_, p_180426_3_, p_180426_5_);
        this.setRotation(p_180426_7_, p_180426_8_);
    }

    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt_double(x * x + z * z);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, (double)f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        }
    }
    
    public void setShootingEntityUUID(String uuid) {
    	this.shootingEntityUUID = uuid;
    }
    
    public String getShootingEntityUUID() {
    	return this.shootingEntityUUID;
    }

    public void onUpdate() {
        super.onUpdate();
        
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
        }

        BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
        IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
        Block block = iblockstate.getBlock();

        if (iblockstate.getMaterial() != Material.AIR) {
            AxisAlignedBB axisalignedbb = iblockstate.getSelectedBoundingBox(this.worldObj, blockpos);

            if (axisalignedbb != null && axisalignedbb.isVecInside(new Vec3d(this.posX, this.posY, this.posZ)))
            {
                this.inGround = true;
            }
        }

        if (this.hookShake > 0) {
            --this.hookShake;
        }

        if (this.inGround) {
        	int j = block.getMetaFromState(iblockstate);

            if(block != this.inTile && j != this.inData) {
                this.inGround = false;
                this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
                this.ticksInAir = 0;
            }
            if(shootingEntity != null) {
				destdistX = this.getPosition().getX() - this.shootingEntity.getPosition().getX();
				destdistY = (this.getPosition().getY() - 1) - this.shootingEntity.getPosition().getY();
				destdistZ = this.getPosition().getZ() - this.shootingEntity.getPosition().getZ();
				if(this.destdistX <= 32 && this.destdistX >= -32 && this.destdistY <= 32 && this.destdistY >= -32 && this.destdistZ <= 32 && this.destdistZ >= -32 && this.shootingEntity != null) {
					EntityPlayer player = (EntityPlayer)this.shootingEntity;
					player.motionX = this.destdistX / 5;
					player.motionY = this.destdistY / 5;
					player.motionZ = this.destdistZ / 5;
					player.velocityChanged = true;
					player.fallDistance = 0;
				}
				else {
					this.setDead();
				}
			}
        }
        else {
            ++this.ticksInAir;
            Vec3d vec31 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult raytraceresult = this.worldObj.rayTraceBlocks(vec31, vec3, false, true, false);
            vec31 = new Vec3d(this.posX, this.posY, this.posZ);
            vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (raytraceresult != null) {
                vec3 = new Vec3d(raytraceresult.hitVec.xCoord, raytraceresult.hitVec.yCoord, raytraceresult.hitVec.zCoord);
            }

            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            int i;
            float f1;

            for (i = 0; i < list.size(); ++i) {
                Entity entity1 = (Entity)list.get(i);

                if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
                    f1 = 0.3F;
                    AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand((double)f1, (double)f1, (double)f1);
                    RayTraceResult raytraceresult1 = axisalignedbb1.calculateIntercept(vec31, vec3);

                    if (raytraceresult1 != null) {
                        double d1 = vec31.distanceTo(raytraceresult1.hitVec);

                        if (d1 < d0 || d0 == 0.0D) {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null) {
            	raytraceresult = new RayTraceResult(entity);
            }

            float f2;
            float f3;
            float f4;

            if (raytraceresult != null) {
            	if(raytraceresult.entityHit == null) {
                	BlockPos blockpos1 = raytraceresult.getBlockPos();
                	this.xTile = blockpos1.getX();
                	this.yTile = blockpos1.getY();
                	this.zTile = blockpos1.getZ();
                	iblockstate = this.worldObj.getBlockState(blockpos1);
                	this.inTile = iblockstate.getBlock();
                	this.inData = this.inTile.getMetaFromState(iblockstate);
                	this.motionX = (double)((float)(raytraceresult.hitVec.xCoord - this.posX));
                	this.motionY = (double)((float)(raytraceresult.hitVec.yCoord - this.posY));
                	this.motionZ = (double)((float)(raytraceresult.hitVec.zCoord - this.posZ));
                	f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                	this.posX -= this.motionX / (double)f3 * 0.05000000074505806D;
                	this.posY -= this.motionY / (double)f3 * 0.05000000074505806D;
                	this.posZ -= this.motionZ / (double)f3 * 0.05000000074505806D;
                	this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                	this.inGround = true;
                	this.hookShake = 7;

                	if (iblockstate.getMaterial() != Material.AIR) {
                		this.inTile.onEntityCollidedWithBlock(this.worldObj, blockpos1, iblockstate, this);
                	}
            	}
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

            for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f2) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
                ;
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            f3 = 0.99F;
            f1 = 0.05F;

            if (this.isInWater()) {
                for (int l = 0; l < 4; ++l) {
                    f4 = 0.25F;
                    this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double)f4, this.posY - this.motionY * (double)f4, this.posZ - this.motionZ * (double)f4, this.motionX, this.motionY, this.motionZ, new int[0]);
                }

                f3 = 0.6F;
            }

            if (this.isWet()) {
                this.extinguish();
            }

            this.motionX *= (double)f3;
            this.motionY *= (double)f3;
            this.motionZ *= (double)f3;
            this.motionY -= (double)f1;
            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }
        if(this.shootingEntity != null) {
        	this.setShootingEntityUUID(this.shootingEntity.getUniqueID().toString());
        }
        if(this.shootingEntity == null && this.shootingEntityUUID != null) {
        	this.shootingEntity = this.worldObj.getPlayerEntityByUUID(UUID.fromString(shootingEntityUUID));
        }
    }

    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        tagCompound.setShort("xTile", (short)this.xTile);
        tagCompound.setShort("yTile", (short)this.yTile);
        tagCompound.setShort("zTile", (short)this.zTile);
        ResourceLocation resourcelocation = (ResourceLocation)Block.REGISTRY.getNameForObject(this.inTile);
        tagCompound.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
        tagCompound.setByte("inData", (byte)this.inData);
        tagCompound.setByte("shake", (byte)this.hookShake);
        tagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        tagCompound.setByte("pickup", (byte)this.canBePickedUp);
        if (this.getShootingEntityUUID() != null) {
        	tagCompound.setString("ShootingEntityUUID", this.getShootingEntityUUID());
        }
    }

    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        this.xTile = tagCompound.getShort("xTile");
        this.yTile = tagCompound.getShort("yTile");
        this.zTile = tagCompound.getShort("zTile");

        if (tagCompound.hasKey("inTile", 8)) {
            this.inTile = Block.getBlockFromName(tagCompound.getString("inTile"));
        }
        else {
            this.inTile = Block.getBlockById(tagCompound.getByte("inTile") & 255);
        }

        this.inData = tagCompound.getByte("inData") & 255;
        this.hookShake = tagCompound.getByte("shake") & 255;
        this.inGround = tagCompound.getByte("inGround") == 1;

        if (tagCompound.hasKey("pickup", 99)) {
            this.canBePickedUp = tagCompound.getByte("pickup");
        }
        else if (tagCompound.hasKey("player", 99)) {
            this.canBePickedUp = tagCompound.getBoolean("player") ? 1 : 0;
        }
        
        if(tagCompound.hasKey("ShootingEntityUUID")) {
			this.setShootingEntityUUID(tagCompound.getString("ShootingEntityUUID"));
		}
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public boolean canAttackWithItem() {
        return false;
    }
    
    public boolean getInGround() {
    	return this.inGround;
    }

	@Override
	protected void entityInit() {}
    
    /*public EntityPlayer getShootingPlayerEntity() {
    	try
        {
            UUID uuid = UUID.fromString(this.getShootingEntityUUID());
            return uuid == null ? null : this.worldObj.getPlayerEntityByUUID(uuid);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return null;
        }
    }*/
}

/*package themattyboy.gadgetsngoodies.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntityGrapplingHook extends EntityArrow {
	
	public static boolean hasHitBlock = false;
	public static boolean hasHitEntity = false;
	
	public double destdistX;
	public double destdistY;
	public double destdistZ;
	public Entity hitentity;
	
	public boolean inGround = false;
	
	public EntityGrapplingHook(World worldIn) {
		super(worldIn);
		this.setSize(0.15F, 0.15F);
	}

	public EntityGrapplingHook(World worldIn, EntityLivingBase shooterIn, float velocity) {
		super(worldIn, shooterIn, velocity);
		this.setSize(0.15F, 0.15F);
	}
	
	public EntityGrapplingHook(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.setSize(0.15F, 0.15F);
	}
	
	@Override
	public void onUpdate() {
		if(hasHitBlock) {
			hasHitBlock = false;
		}
		if(hasHitEntity) {
			hasHitEntity = false;
		}
		
		System.out.println(this.getEntityData().hasKey("inGround", 0));
		if(this.getEntityData().getByte("inGround") == 1) {
			System.out.println("Hello World!");
		}
		
		super.onUpdate();
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {}

	/*@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		EntityLivingBase shooter = this.getThrower();
		if(movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && shooter != null) {
			if(!this.worldObj.isRemote) {
				this.hasHitBlock = true;
			
				destdistX = this.getPosition().getX() - shooter.getPosition().getX();
				destdistY = this.getPosition().getY() - shooter.getPosition().getY();
				destdistZ = this.getPosition().getZ() - shooter.getPosition().getZ();
			
				this.setDead();
			}
		}
		else if(movingobjectposition.entityHit != null) {
			System.out.println("entity hit.");
			hitentity = movingobjectposition.entityHit;
			hitentity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 1);
			destdistX = shooter.getPosition().getX() - this.getPosition().getX();
			destdistY = shooter.getPosition().getY() - this.getPosition().getY();
			destdistZ = shooter.getPosition().getZ() - this.getPosition().getZ();
			hitentity.motionX = destdistX;
			hitentity.motionY = destdistY;
			hitentity.motionZ = destdistZ;
			System.out.println(hitentity.motionX);
			this.setDead();
		}
	}

}*/
