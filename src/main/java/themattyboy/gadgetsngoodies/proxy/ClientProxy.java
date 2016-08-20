package themattyboy.gadgetsngoodies.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import themattyboy.gadgetsngoodies.entity.mob.EntityMineOSaur;
import themattyboy.gadgetsngoodies.entity.model.ModelMineOSaur;
import themattyboy.gadgetsngoodies.entity.projectile.EntityFlameThrowerFlame;
import themattyboy.gadgetsngoodies.entity.projectile.EntityGrapplingHook;
import themattyboy.gadgetsngoodies.entity.projectile.EntityWaterGunDrop;
import themattyboy.gadgetsngoodies.entity.renderer.RenderFlameThrowerFlame;
import themattyboy.gadgetsngoodies.entity.renderer.RenderGrapplingHook;
import themattyboy.gadgetsngoodies.entity.renderer.RenderMineOSaur;
import themattyboy.gadgetsngoodies.entity.renderer.RenderWaterGunDrop;
import themattyboy.gadgetsngoodies.init.GadgetBlocks;
import themattyboy.gadgetsngoodies.init.GadgetItems;


public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenders() {
		GadgetBlocks.registerRenders();
		GadgetItems.registerRenders();
		RenderingRegistry.registerEntityRenderingHandler(EntityGrapplingHook.class, new RenderGrapplingHook(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityFlameThrowerFlame.class, new RenderFlameThrowerFlame(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityMineOSaur.class, new RenderMineOSaur(Minecraft.getMinecraft().getRenderManager(), new ModelMineOSaur(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityWaterGunDrop.class, new RenderWaterGunDrop(Minecraft.getMinecraft().getRenderManager()));
		
	}
}
