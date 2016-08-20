package themattyboy.gadgetsngoodies.entity.ai;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAITrackAndMineOre extends EntityAIBase {
	
	private EntityCreature theCreature;
	private World theWorld;
	private double movementSpeed;
	private double oreX;
    private double oreY;
    private double oreZ;
	
	public EntityAITrackAndMineOre(EntityCreature entityCreature, double movementSpeed) {
		this.theCreature = entityCreature;
		this.theWorld = entityCreature.worldObj;
		this.movementSpeed = movementSpeed;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		Vec3d vec3 = this.findPossibleOre();

        if (vec3 == null)
        {
            return false;
        }
        else
        {
            this.oreX = vec3.xCoord;
            this.oreY = vec3.yCoord;
            this.oreZ = vec3.zCoord;
            return true;
        }
	}
	
	public void updateTask() {
		if(this.findPossibleOre() != null) {
			AxisAlignedBB axisalignedbb;
			if(this.oreY < this.theCreature.posY)
				axisalignedbb = new AxisAlignedBB(new BlockPos(this.theCreature.posX - 2, this.theCreature.posY - 1, this.theCreature.posZ - 2), new BlockPos(this.theCreature.posX + 2, this.theCreature.posY + 2, this.theCreature.posZ + 2));
			else {
				axisalignedbb = new AxisAlignedBB(new BlockPos(this.theCreature.posX - 2, this.theCreature.posY, this.theCreature.posZ - 2), new BlockPos(this.theCreature.posX + 2, this.theCreature.posY + 2, this.theCreature.posZ + 2));
			}
			int i = MathHelper.floor_double(axisalignedbb.minX);
			int j = MathHelper.floor_double(axisalignedbb.minY);
			int k = MathHelper.floor_double(axisalignedbb.minZ);
			int l = MathHelper.floor_double(axisalignedbb.maxX);
			int i1 = MathHelper.floor_double(axisalignedbb.maxY);
			int j1 = MathHelper.floor_double(axisalignedbb.maxZ);
			boolean flag = false;
			boolean flag1 = false;

			for (int k1 = i; k1 <= l; ++k1) {
				for (int l1 = j; l1 <= i1; ++l1) {
					for (int i2 = k; i2 <= j1; ++i2) {
						IBlockState state = this.theWorld.getBlockState(new BlockPos(k1, l1, i2));
						Block block = state.getBlock();

						if (!block.isAir(state, this.theWorld, new BlockPos(k1, l1, i2))) {
							if (/*block != Blocks.barrier && block != Blocks.bedrock && block != Blocks.end_portal && block != Blocks.end_portal_frame && block != Blocks.command_block && */block.getBlockHardness(state, theWorld, new BlockPos(k1, l1, i2)) != -1 && block.getBlockHardness(state, theWorld, new BlockPos(k1, l1, i2)) < 100.0F && this.theWorld.getGameRules().getBoolean("mobGriefing")) {
								flag1 = this.theWorld.destroyBlock(new BlockPos(k1, l1, i2), true) || flag1;
							}
							else {
								flag = true;
							}
						}
					}
				}
			}
		}
	}
	
	public boolean continueExecuting() {
        return this.findPossibleOre() != null /*!this.theCreature.getNavigator().noPath()*/;
    }

    public void startExecuting() {
    	this.theCreature.getNavigator().tryMoveToXYZ(this.oreX, this.oreY, this.oreZ, this.movementSpeed);
    	if(this.theCreature.getNavigator().noPath()) {
    		this.theCreature.motionX = (oreX - this.theCreature.posX) / 30;
    		this.theCreature.motionZ = (oreZ - this.theCreature.posZ) / 30;
    	}
    }
	
	private Vec3d findPossibleOre() {
        Random random = this.theCreature.getRNG();
        BlockPos blockpos = new BlockPos(this.theCreature.posX, this.theCreature.getEntityBoundingBox().minY, this.theCreature.posZ);

        for (int i = 0; i < 32; ++i) {
        	BlockPos blockpos1 = blockpos.add(random.nextInt(32) - 16, random.nextInt(6) - 3, random.nextInt(32) - 16);

        	if ((this.theWorld.getBlockState(blockpos1).getBlock() instanceof BlockOre || this.theWorld.getBlockState(blockpos1).getBlock() instanceof BlockRedstoneOre)) {
        		return new Vec3d((double)blockpos1.getX(), (double)blockpos1.getY(), (double)blockpos1.getZ());
        	}
        }

        return null;
    }

}
