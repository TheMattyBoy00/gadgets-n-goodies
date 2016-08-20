/*package themattyboy.gadgetsngoodies.entity.ai;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityAITrackAndMineOre2 extends EntityAIMoveToBlock {
	
	private EntityCreature theCreature;
	private World theWorld;
	private double movementSpeed;

	public EntityAITrackAndMineOre2(EntityCreature entityCreature, double movementSpeed) {
		super(entityCreature, movementSpeed, 16);
		this.theCreature = entityCreature;
		this.theWorld = entityCreature.worldObj;
		this.movementSpeed = movementSpeed;
		this.setMutexBits(1);
	}
	
	public boolean shouldExecute()
    {
        if (this.field_179496_a <= 0)
        {
            if (!this.theCreature.worldObj.getGameRules().getBoolean("mobGriefing"))
            {
                return false;
            }
        }
        
        if(this.theWorld.getBlockState(destinationBlock).getBlock() instanceof BlockOre || this.theWorld.getBlockState(destinationBlock).getBlock() instanceof BlockRedstoneOre) {
        	return true;
        }
        
        else {
        	return false;
        }
    }
	
	public boolean continueExecuting() {
		return this.theWorld.getBlockState(destinationBlock).getBlock() instanceof BlockOre || this.theWorld.getBlockState(destinationBlock).getBlock() instanceof BlockRedstoneOre;
	}
	
	public void startExecuting() {
        super.startExecuting();
    }

    public void resetTask() {
        super.resetTask();
    }

	@Override
	protected boolean func_179488_a(World worldIn, BlockPos p_179488_2_) {
		return false;
	}
	
	public void updateTask() {
		if(!(this.theWorld.getBlockState(destinationBlock).getBlock() instanceof BlockOre) || !(this.theWorld.getBlockState(destinationBlock).getBlock() instanceof BlockRedstoneOre)) {
			Random random = this.theCreature.getRNG();
	        BlockPos blockpos = new BlockPos(this.theCreature.posX, this.theCreature.getEntityBoundingBox().minY, this.theCreature.posZ);

	        for (int i = 0; i < 64; ++i) {
	        	BlockPos blockpos1 = blockpos.add(random.nextInt(32) - 16, random.nextInt(6) - 3, random.nextInt(32) - 16);

	        	if ((this.theWorld.getBlockState(blockpos1).getBlock() instanceof BlockOre || this.theWorld.getBlockState(blockpos1).getBlock() instanceof BlockRedstoneOre) && this.theCreature.func_180484_a(blockpos1) < 0.0F) {
	        		this.destinationBlock = blockpos1;
	        	}
	        }
		}
		
		if(this.theWorld.getBlockState(destinationBlock).getBlock() instanceof BlockOre || this.theWorld.getBlockState(destinationBlock).getBlock() instanceof BlockRedstoneOre) {
			AxisAlignedBB axisalignedbb;
			if(this.destinationBlock.getY() < this.theCreature.posY)
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
						Block block = this.theWorld.getBlockState(new BlockPos(k1, l1, i2)).getBlock();

						if (!block.isAir(theWorld, new BlockPos(k1, l1, i2))) {
							if (block != Blocks.barrier && block != Blocks.bedrock && block != Blocks.end_portal && block != Blocks.end_portal_frame && block != Blocks.command_block && this.theWorld.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
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
}
*/