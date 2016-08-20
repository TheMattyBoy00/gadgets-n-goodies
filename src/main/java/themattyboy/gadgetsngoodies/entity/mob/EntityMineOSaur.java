package themattyboy.gadgetsngoodies.entity.mob;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import themattyboy.gadgetsngoodies.entity.ai.EntityAITrackAndMineOre;

public class EntityMineOSaur extends EntityTameable {
	
	public boolean isActivated = true;

	public EntityMineOSaur(World par1World) {
		super(par1World);
		this.setSize(2.0F, 2.7F);
		this.tasks.addTask(0, new EntityAIWander(this, 0.5D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAISwimming(this));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 0.7D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAITrackAndMineOre(this, 1.0F));
	}
	
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0F);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
	}
	
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if(this.getOwner() != null) {
			this.setTamed(true);
		}
		this.setNoAI(!isActivated);
	}
	
	public float getEyeHeight() {
        return 2.4F;
    }
	
	protected SoundEvent getAmbientSound() {
        return isActivated ? SoundEvents.ENTITY_ENDERDRAGON_GROWL : null;
    }
	
	protected SoundEvent getHurtSound() {
        return SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR;
    }
	
	protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_ANVIL_LAND;
    }
	
	protected void playStepSound(BlockPos pos, Block block) {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 0.15F, 0.2F);
    }
	
	public boolean processInteract(EntityPlayer player, EnumHand hand, ItemStack stack) {
		if(this.getOwner() instanceof EntityPlayer && player == (EntityPlayer)this.getOwner()) {
			this.isActivated = !this.isActivated;
		}
		return super.processInteract(player, hand, stack);
	}
	
	public boolean attackEntityFrom(DamageSource source, float amount) {
		Entity entity;
		entity = source.getSourceOfDamage();
		if(entity instanceof EntityArrow) {
			return false;
		}
		else {
			return super.attackEntityFrom(source, amount);
		}
	}
	
	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
		int i = 32 + this.rand.nextInt(9);

        for (int j = 0; j < i; ++j)
        {
            this.dropItem(Items.DIAMOND, 1);
        }
        int k = 12 + this.rand.nextInt(4);

        for (int l = 0; l < k; ++l)
        {
            this.dropItem(Item.getItemFromBlock(Blocks.IRON_BLOCK), 1);
        }
        int m = 5 + this.rand.nextInt(3);

        for (int n = 0; n < m; ++n)
        {
            this.dropItem(Item.getItemFromBlock(Blocks.PISTON), 1);
        }
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

}