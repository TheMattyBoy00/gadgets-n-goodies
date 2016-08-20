package themattyboy.gadgetsngoodies.entity;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import themattyboy.gadgetsngoodies.entity.mob.EntityMineOSaur;
import themattyboy.gadgetsngoodies.entity.projectile.EntityFlameThrowerFlame;
import themattyboy.gadgetsngoodies.entity.projectile.EntityGrapplingHook;
import themattyboy.gadgetsngoodies.entity.projectile.EntityWaterGunDrop;
import themattyboy.gadgetsngoodies.main.GadgetsNGoodiesMod;

public class GadgetEntityList {
	
	public static void mainRegistry() {
		registerEntity();
	}
	
	public static void registerEntity() {
		createEntity(EntityGrapplingHook.class, "GrapplingHook", 1);
		createEntity(EntityFlameThrowerFlame.class, "FlameThrowerFlame", 2);
		createEntity(EntityWaterGunDrop.class, "WaterGunDrop", 3);
		
		createEntity(EntityMineOSaur.class, "Mine-o-Saur", 0xe9e9e9, 0x4aedd1, 4);
	}
	
	public static void createEntity(Class entityClass, String entityName, int id) {
		EntityRegistry.registerModEntity(entityClass, entityName, id, GadgetsNGoodiesMod.modInstance, 64, 1, true);
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor, int id) {
		EntityRegistry.registerModEntity(entityClass, entityName, id, GadgetsNGoodiesMod.modInstance, 64, 1, true, solidColor, spotColor);
	}
}
