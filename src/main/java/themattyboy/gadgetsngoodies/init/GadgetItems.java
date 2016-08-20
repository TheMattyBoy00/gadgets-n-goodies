package themattyboy.gadgetsngoodies.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import themattyboy.gadgetsngoodies.items.ItemBlockOCopter;
import themattyboy.gadgetsngoodies.items.ItemEggDispenser;
import themattyboy.gadgetsngoodies.items.ItemFireballDispenser;
import themattyboy.gadgetsngoodies.items.ItemFlameThrower;
import themattyboy.gadgetsngoodies.items.ItemGrapplingHook;
import themattyboy.gadgetsngoodies.items.ItemHangGlider;
import themattyboy.gadgetsngoodies.items.ItemMineOSaur;
import themattyboy.gadgetsngoodies.items.ItemMusicalInstrument;
import themattyboy.gadgetsngoodies.items.ItemPortableTNTCannonEmpty;
import themattyboy.gadgetsngoodies.items.ItemPortableTNTCannonLoaded;
import themattyboy.gadgetsngoodies.items.ItemWaterGun;
import themattyboy.gadgetsngoodies.items.armor.ItemDivingArmor;
import themattyboy.gadgetsngoodies.items.armor.ItemFlippers;
import themattyboy.gadgetsngoodies.items.armor.ItemFlyArmor;
import themattyboy.gadgetsngoodies.main.GadgetsNGoodiesMod;
import themattyboy.gadgetsngoodies.main.Reference;

public class GadgetItems {
	
	public static int fireball_dispenser_durability;
	public static int egg_dispenser_durability;
	public static int block_o_copter_durability;
	public static int jetpack_durability;
	public static int grappling_hook_durability;
	public static int flame_thrower_durability;
	
	public static ArmorMaterial enumArmorMaterialDiving = EnumHelper.addArmorMaterial("DIVING", Reference.MOD_ID + ":diving", 50, new int[] {1, 0, 7, 0}, 5, null, 0);
	public static ArmorMaterial enumArmorMaterialFlippers = EnumHelper.addArmorMaterial("FLIPPERS", Reference.MOD_ID + ":diving", 50, new int[] {0, 0, 0, 0}, 5, null, 0);
	public static ArmorMaterial enumArmorMaterialFly = EnumHelper.addArmorMaterial("FLY", Reference.MOD_ID + ":fly", 50, new int[] {0, 0, 0, 0}, 0, null, 0);
	
	public static Item fireball_dispenser;
	public static Item musical_instrument;
	public static Item egg_dispenser;
	public static Item block_o_copter;
	public static Item diving_helmet;
	public static Item oxygen_tank;
	public static Item wetsuit;
	public static Item flippers;
	public static Item jetpack;
	public static Item grappling_hook;
	public static Item flame_thrower;
	public static Item portable_TNT_cannon_empty;
	public static Item portable_TNT_cannon_loaded;
	public static Item hang_glider;
	public static Item mine_o_saur;
	public static Item water_gun;
	
	public static Item mine_o_saur_eye;
	public static Item mine_o_saur_jaw;
	public static Item mine_o_saur_skull_half;
	public static Item mine_o_saur_skull;
	public static Item mine_o_saur_arm;
	public static Item mine_o_saur_arm_pair;
	public static Item mine_o_saur_leg;
	public static Item mine_o_saur_leg_pair;
	public static Item mine_o_saur_tail;
	public static Item mine_o_saur_body;
	public static Item mine_o_saur_head;
	
	public static void init() {
		fireball_dispenser = new ItemFireballDispenser().setUnlocalizedName("fireball_dispenser").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies).setMaxStackSize(1).setMaxDamage(fireball_dispenser_durability);
		musical_instrument = new ItemMusicalInstrument().setUnlocalizedName("musical_instrument").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies).setMaxStackSize(1);
		egg_dispenser = new ItemEggDispenser().setUnlocalizedName("egg_dispenser").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies).setMaxStackSize(1).setMaxDamage(egg_dispenser_durability);
		block_o_copter = new ItemBlockOCopter().setUnlocalizedName("block-o-copter").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies).setMaxStackSize(1).setMaxDamage(block_o_copter_durability);
		diving_helmet = new ItemDivingArmor(enumArmorMaterialDiving, 5, EntityEquipmentSlot.HEAD).setUnlocalizedName("diving_helmet").setCreativeTab(/*GadgetsNGoodiesMod.tabGadgetsNGoodies*/null);
		oxygen_tank = new ItemDivingArmor(enumArmorMaterialDiving, 5, EntityEquipmentSlot.CHEST).setUnlocalizedName("oxygen_tank").setCreativeTab(/*GadgetsNGoodiesMod.tabGadgetsNGoodies*/null);
		wetsuit = new ItemDivingArmor(enumArmorMaterialDiving, 5, EntityEquipmentSlot.LEGS).setUnlocalizedName("wetsuit").setCreativeTab(/*GadgetsNGoodiesMod.tabGadgetsNGoodies*/null);
		flippers = new ItemFlippers(enumArmorMaterialFlippers, 5, EntityEquipmentSlot.FEET).setUnlocalizedName("flippers").setCreativeTab(/*GadgetsNGoodiesMod.tabGadgetsNGoodies*/null);
		jetpack = new ItemFlyArmor(enumArmorMaterialFly, 5, EntityEquipmentSlot.CHEST).setUnlocalizedName("jetpack").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies).setMaxStackSize(1).setMaxDamage(jetpack_durability);
		grappling_hook = new ItemGrapplingHook().setUnlocalizedName("grappling_hook").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies).setMaxStackSize(1).setMaxDamage(grappling_hook_durability);
		flame_thrower = new ItemFlameThrower().setUnlocalizedName("flame_thrower").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies).setMaxStackSize(1).setMaxDamage(flame_thrower_durability);
		portable_TNT_cannon_empty = new ItemPortableTNTCannonEmpty().setUnlocalizedName("portable_TNT_cannon_empty").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies).setMaxStackSize(1);
		portable_TNT_cannon_loaded = new ItemPortableTNTCannonLoaded().setUnlocalizedName("portable_TNT_cannon_loaded").setCreativeTab(null).setMaxStackSize(1);
		hang_glider = new ItemHangGlider().setUnlocalizedName("hang_glider").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies).setMaxStackSize(1);
		mine_o_saur = new ItemMineOSaur().setUnlocalizedName("mine-o-saur").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
		water_gun = new ItemWaterGun().setUnlocalizedName("water_gun").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies).setMaxStackSize(1);
		
		mine_o_saur_eye = new Item().setUnlocalizedName("mine-o-saur_eye").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
		mine_o_saur_jaw = new Item().setUnlocalizedName("mine-o-saur_jaw").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
		mine_o_saur_skull_half = new Item().setUnlocalizedName("mine-o-saur_skull_half").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
		mine_o_saur_skull = new Item().setUnlocalizedName("mine-o-saur_skull").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
		mine_o_saur_arm = new Item().setUnlocalizedName("mine-o-saur_arm").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
		mine_o_saur_arm_pair = new Item().setUnlocalizedName("mine-o-saur_arm_pair").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
		mine_o_saur_leg = new Item().setUnlocalizedName("mine-o-saur_leg").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
		mine_o_saur_leg_pair = new Item().setUnlocalizedName("mine-o-saur_leg_pair").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
		mine_o_saur_tail = new Item().setUnlocalizedName("mine-o-saur_tail").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
		mine_o_saur_body = new Item().setUnlocalizedName("mine-o-saur_body").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
		mine_o_saur_head = new Item().setUnlocalizedName("mine-o-saur_head").setCreativeTab(GadgetsNGoodiesMod.tabGadgetsNGoodies);
	}
	
	public static void register() {
		GameRegistry.register(fireball_dispenser, new ResourceLocation(Reference.MOD_ID, fireball_dispenser.getUnlocalizedName().substring(5)));
		GameRegistry.register(musical_instrument, new ResourceLocation(Reference.MOD_ID, musical_instrument.getUnlocalizedName().substring(5)));
		GameRegistry.register(egg_dispenser, new ResourceLocation(Reference.MOD_ID, egg_dispenser.getUnlocalizedName().substring(5)));
		GameRegistry.register(block_o_copter, new ResourceLocation(Reference.MOD_ID, block_o_copter.getUnlocalizedName().substring(5)));
		/*GameRegistry.register(diving_helmet, new ResourceLocation(Reference.MOD_ID, diving_helmet.getUnlocalizedName().substring(5)));
		GameRegistry.register(oxygen_tank, new ResourceLocation(Reference.MOD_ID, oxygen_tank.getUnlocalizedName().substring(5)));
		GameRegistry.register(wetsuit, new ResourceLocation(Reference.MOD_ID, wetsuit.getUnlocalizedName().substring(5)));
		GameRegistry.register(flippers, new ResourceLocation(Reference.MOD_ID, flippers.getUnlocalizedName().substring(5)));*/
		GameRegistry.register(jetpack, new ResourceLocation(Reference.MOD_ID, jetpack.getUnlocalizedName().substring(5)));
		GameRegistry.register(grappling_hook, new ResourceLocation(Reference.MOD_ID, grappling_hook.getUnlocalizedName().substring(5)));
		GameRegistry.register(flame_thrower, new ResourceLocation(Reference.MOD_ID, flame_thrower.getUnlocalizedName().substring(5)));
		GameRegistry.register(portable_TNT_cannon_empty, new ResourceLocation(Reference.MOD_ID, portable_TNT_cannon_empty.getUnlocalizedName().substring(5)));
		GameRegistry.register(portable_TNT_cannon_loaded, new ResourceLocation(Reference.MOD_ID, portable_TNT_cannon_loaded.getUnlocalizedName().substring(5)));
		GameRegistry.register(hang_glider, new ResourceLocation(Reference.MOD_ID, hang_glider.getUnlocalizedName().substring(5)));
		GameRegistry.register(mine_o_saur, new ResourceLocation(Reference.MOD_ID, mine_o_saur.getUnlocalizedName().substring(5)));
		GameRegistry.register(water_gun, new ResourceLocation(Reference.MOD_ID, water_gun.getUnlocalizedName().substring(5)));
		
		GameRegistry.register(mine_o_saur_eye, new ResourceLocation(Reference.MOD_ID, mine_o_saur_eye.getUnlocalizedName().substring(5)));
		GameRegistry.register(mine_o_saur_jaw, new ResourceLocation(Reference.MOD_ID, mine_o_saur_jaw.getUnlocalizedName().substring(5)));
		GameRegistry.register(mine_o_saur_skull_half, new ResourceLocation(Reference.MOD_ID, mine_o_saur_skull_half.getUnlocalizedName().substring(5)));
		GameRegistry.register(mine_o_saur_skull, new ResourceLocation(Reference.MOD_ID, mine_o_saur_skull.getUnlocalizedName().substring(5)));
		GameRegistry.register(mine_o_saur_arm, new ResourceLocation(Reference.MOD_ID, mine_o_saur_arm.getUnlocalizedName().substring(5)));
		GameRegistry.register(mine_o_saur_arm_pair, new ResourceLocation(Reference.MOD_ID, mine_o_saur_arm_pair.getUnlocalizedName().substring(5)));
		GameRegistry.register(mine_o_saur_leg, new ResourceLocation(Reference.MOD_ID, mine_o_saur_leg.getUnlocalizedName().substring(5)));
		GameRegistry.register(mine_o_saur_leg_pair, new ResourceLocation(Reference.MOD_ID, mine_o_saur_leg_pair.getUnlocalizedName().substring(5)));
		GameRegistry.register(mine_o_saur_tail, new ResourceLocation(Reference.MOD_ID, mine_o_saur_tail.getUnlocalizedName().substring(5)));
		GameRegistry.register(mine_o_saur_body, new ResourceLocation(Reference.MOD_ID, mine_o_saur_body.getUnlocalizedName().substring(5)));
		GameRegistry.register(mine_o_saur_head, new ResourceLocation(Reference.MOD_ID, mine_o_saur_head.getUnlocalizedName().substring(5)));
	}
	
	public static void registerRenders() {
		registerRender(fireball_dispenser);
		registerRender(musical_instrument);
		registerRender(egg_dispenser);
		registerRender(block_o_copter);
		registerRender(diving_helmet);
		registerRender(oxygen_tank);
		registerRender(wetsuit);
		registerRender(flippers);
		registerRender(jetpack);
		registerRender(grappling_hook);
		registerRender(flame_thrower);
		registerRender(portable_TNT_cannon_empty);
		registerRender(portable_TNT_cannon_loaded);
		registerRender(hang_glider);
		registerRender(mine_o_saur);
		registerRender(water_gun);
		
		registerRender(mine_o_saur_eye);
		registerRender(mine_o_saur_jaw);
		registerRender(mine_o_saur_skull_half);
		registerRender(mine_o_saur_skull);
		registerRender(mine_o_saur_arm);
		registerRender(mine_o_saur_arm_pair);
		registerRender(mine_o_saur_leg);
		registerRender(mine_o_saur_leg_pair);
		registerRender(mine_o_saur_tail);
		registerRender(mine_o_saur_body);
		registerRender(mine_o_saur_head);
	}
	
	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
