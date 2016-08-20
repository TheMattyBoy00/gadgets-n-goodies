package themattyboy.gadgetsngoodies.entity.renderer;

import themattyboy.gadgetsngoodies.entity.mob.EntityMineOSaur;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderMineOSaur extends RenderLiving {

	private static final ResourceLocation activatedTexture = new ResourceLocation("gadgetsngoodies:textures/entity/mine_o_saur.png");
	private static final ResourceLocation deactivatedTexture = new ResourceLocation("gadgetsngoodies:textures/entity/mine_o_saur_deactivated.png");
	
	public RenderMineOSaur(RenderManager rendermanager, ModelBase par1ModelBase, float par2) {
		super(rendermanager, par1ModelBase, par2);
	}
	
	protected ResourceLocation getEntityTexture(EntityMineOSaur entity) {
		return entity.isActivated ? activatedTexture : deactivatedTexture;
	}
	
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityMineOSaur)entity);
	}

}
