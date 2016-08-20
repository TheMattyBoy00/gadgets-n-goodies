package themattyboy.gadgetsngoodies.main;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.Metadata;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import themattyboy.gadgetsngoodies.achievements.GadgetAchievements;
import themattyboy.gadgetsngoodies.entity.GadgetEntityList;
import themattyboy.gadgetsngoodies.event.GadgetForgeEvents;
import themattyboy.gadgetsngoodies.init.GadgetBlocks;
import themattyboy.gadgetsngoodies.init.GadgetItems;
import themattyboy.gadgetsngoodies.init.GadgetSounds;
import themattyboy.gadgetsngoodies.items.ItemFireballDispenser;
import themattyboy.gadgetsngoodies.items.ItemFlameThrower;
import themattyboy.gadgetsngoodies.items.ItemWaterGun;
import themattyboy.gadgetsngoodies.items.armor.ItemFlyArmor;
import themattyboy.gadgetsngoodies.proxy.CommonProxy;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class GadgetsNGoodiesMod {
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@Metadata
    public static ModMetadata meta;
    
    @Instance(Reference.MOD_ID)
    public static GadgetsNGoodiesMod modInstance;
	
	public static final GadgetTab tabGadgetsNGoodies = new GadgetTab("tabGadgetsNGoodies");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		GadgetItems .fireball_dispenser_durability = config.getInt("fireball_dispenser_durability", "Durability", 199, 0, 1000000, "The durability of the Fireball Dispenser");
		GadgetItems.egg_dispenser_durability = config.getInt("egg_dispenser_durability", "Durability", 640, 0, 1000000, "The durability of the Egg Dispenser");
		GadgetItems.block_o_copter_durability = config.getInt("block-o-copter_durability", "Durability", 126, 0, 1000000, "The durability of the Block-o-Copter");
		GadgetItems.jetpack_durability = config.getInt("jetpack_durability", "Durability", 549, 0, 1000000, "The durability of the Jetpack");
		GadgetItems.grappling_hook_durability = config.getInt("grappling_hook_durability", "Durability", 99, 0, 1000000, "The durability of the Grappling Hook");
		GadgetItems.flame_thrower_durability = config.getInt("flame_thrower_durability", "Durability", 549, 0, 1000000, "The durability of the Flame Thrower");
		ItemFireballDispenser.maxExplosionPower = config.getInt("fireball_dispenser_max_explosion_strength", "Misc", 3, 0, 1000000, "The maximum explosion power of the Fireball Dispenser");
		ItemFireballDispenser.minExplosionPower = config.getInt("fireball_dispenser_min_explosion_strength", "Misc", 0, 0, 1000000, "The minimum explosion power of the Fireball Dispenser");
		ItemWaterGun.maxWaterLevel = config.getInt("water_gun_max_water_level", "Misc", 100, 0, 1000000, "The maximum water level of the Water Gun");
		GadgetSounds.musicalInstrumentSoundName = config.getString("musical_instrument_sound", "Misc", "block.note.harp", "The sound the Musical Instrument makes");
		ItemFlyArmor.maxCoalTimer = config.getFloat("jetpack_coal_timer", "Misc", 5, 0.05F, 1000000, "How many seconds it takes for a jetpack to consume a coal");
		ItemFlameThrower.maxCoalTimer = config.getFloat("flame_thrower_coal_timer", "Misc", 1, 0.05F, 1000000, "How many seconds it takes for a flame thrower to consume a coal");
		config.save();
		
		GadgetBlocks.init();
		GadgetBlocks.register();
		GadgetItems.init();
		GadgetItems.register();
		GadgetEntityList.mainRegistry();
		GadgetSounds.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenders();
		
		GadgetAchievements.init();
		
		MinecraftForge.EVENT_BUS.register(new GadgetForgeEvents());
		
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.fireball_dispenser), "121", "345", " 67", '1', Items.IRON_INGOT, '2', Blocks.GOLD_BLOCK, '3', Blocks.DISPENSER, '4', Items.REPEATER, '5', Items.REDSTONE, '6', Blocks.STONE_BUTTON, '7', Items.GOLD_INGOT /*fireballDispenserCrafting1, fireballDispenserCrafting2, fireballDispenserCrafting3, '1', new ItemStack(Item.getItemById(fireballDispenserCraftingMaterial1), 1, fireballDispenserCraftingMaterial1Data), '2', new ItemStack(Item.getItemById(fireballDispenserCraftingMaterial2), 1, fireballDispenserCraftingMaterial2Data), '3', new ItemStack(Item.getItemById(fireballDispenserCraftingMaterial3), 1, fireballDispenserCraftingMaterial3Data), '4', new ItemStack(Item.getItemById(fireballDispenserCraftingMaterial4), 1, fireballDispenserCraftingMaterial4Data), '5', new ItemStack(Item.getItemById(fireballDispenserCraftingMaterial5), 1, fireballDispenserCraftingMaterial5Data), '6', new ItemStack(Item.getItemById(fireballDispenserCraftingMaterial6), 1, fireballDispenserCraftingMaterial6Data), '7', new ItemStack(Item.getItemById(fireballDispenserCraftingMaterial7), 1, fireballDispenserCraftingMaterial7Data), '8', new ItemStack(Item.getItemById(fireballDispenserCraftingMaterial8), 1, fireballDispenserCraftingMaterial8Data), '9', new ItemStack(Item.getItemById(fireballDispenserCraftingMaterial9), 1, fireballDispenserCraftingMaterial9Data)*/);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.musical_instrument), "1", "2", '1', Blocks.NOTEBLOCK, '2', Items.STICK /*musicalInstrumentCrafting1, musicalInstrumentCrafting2, '1', new ItemStack(Item.getItemById(musicalInstrumentCraftingMaterial1), 1, musicalInstrumentCraftingMaterial1Data), '2', new ItemStack(Item.getItemById(musicalInstrumentCraftingMaterial2), 1, musicalInstrumentCraftingMaterial2Data), '3', new ItemStack(Item.getItemById(musicalInstrumentCraftingMaterial3), 1, musicalInstrumentCraftingMaterial3Data), '4', new ItemStack(Item.getItemById(musicalInstrumentCraftingMaterial4), 1, musicalInstrumentCraftingMaterial4Data), '5', new ItemStack(Item.getItemById(musicalInstrumentCraftingMaterial5), 1, musicalInstrumentCraftingMaterial5Data), '6', new ItemStack(Item.getItemById(musicalInstrumentCraftingMaterial6), 1, musicalInstrumentCraftingMaterial6Data), '7', new ItemStack(Item.getItemById(musicalInstrumentCraftingMaterial7), 1, musicalInstrumentCraftingMaterial7Data), '8', new ItemStack(Item.getItemById(musicalInstrumentCraftingMaterial8), 1, musicalInstrumentCraftingMaterial8Data), '9', new ItemStack(Item.getItemById(musicalInstrumentCraftingMaterial9), 1, musicalInstrumentCraftingMaterial9Data)*/);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.egg_dispenser), "121", "345", " 61", '1', Items.IRON_INGOT, '2', Blocks.IRON_BLOCK, '3', Blocks.DISPENSER, '4', new ItemStack(Items.DYE, 1, 4), '5', Items.REDSTONE, '6', Blocks.STONE_BUTTON /*eggDispenserCrafting1, eggDispenserCrafting2, eggDispenserCrafting3, '1', new ItemStack(Item.getItemById(eggDispenserCraftingMaterial1), 1, eggDispenserCraftingMaterial1Data), '2', new ItemStack(Item.getItemById(eggDispenserCraftingMaterial2), 1, eggDispenserCraftingMaterial2Data), '3', new ItemStack(Item.getItemById(eggDispenserCraftingMaterial3), 1, eggDispenserCraftingMaterial3Data), '4', new ItemStack(Item.getItemById(eggDispenserCraftingMaterial4), 1, eggDispenserCraftingMaterial4Data), '5', new ItemStack(Item.getItemById(eggDispenserCraftingMaterial5), 1, eggDispenserCraftingMaterial5Data), '6', new ItemStack(Item.getItemById(eggDispenserCraftingMaterial6), 1, eggDispenserCraftingMaterial6Data), '7', new ItemStack(Item.getItemById(eggDispenserCraftingMaterial7), 1, eggDispenserCraftingMaterial7Data), '8', new ItemStack(Item.getItemById(eggDispenserCraftingMaterial8), 1, eggDispenserCraftingMaterial8Data), '9', new ItemStack(Item.getItemById(eggDispenserCraftingMaterial9), 1, eggDispenserCraftingMaterial9Data)*/);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.block_o_copter), "sis", "iLi", "ibi", 's', Items.STICK, 'i', Items.IRON_INGOT, 'L', Blocks.REDSTONE_LAMP, 'b', Blocks.STONE_BUTTON);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.grappling_hook), "iei", "BsD", " bi", 'i', Items.IRON_INGOT, 'B', Blocks.IRON_BARS, 's', Items.STRING, 'D', Blocks.DISPENSER, 'b', Blocks.STONE_BUTTON, 'e', Items.EMERALD);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.jetpack), " i ", "iFi", "rIr", 'i', Items.IRON_INGOT, 'F', Blocks.FURNACE, 'r', Items.FIREWORKS, 'I', Blocks.IRON_BLOCK);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.flame_thrower), "iri", "bDF", "sBi", 'i', Items.IRON_INGOT, 'r', Items.REDSTONE, 'b', Items.BLAZE_POWDER, 'D', Blocks.DISPENSER, 'F', Blocks.FURNACE, 's', Items.STRING, 'B', Blocks.STONE_BUTTON);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.portable_TNT_cannon_empty), "RrB", "P b", "FOO", 'P', Blocks.WOODEN_PRESSURE_PLATE, 'R', Items.REPEATER, 'r', Items.REDSTONE, 'F', Blocks.OAK_FENCE, 'b', Items.WATER_BUCKET, 'O', Blocks.OBSIDIAN, 'B', Blocks.STONE_BUTTON);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.hang_glider), "WsW", "WsW", "sss", 'W', Blocks.WOOL, 's', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.water_gun), "iIw", "Db ", " Bi", 'i', Items.IRON_INGOT, 'I', Blocks.IRON_BLOCK, 'w', new ItemStack(Items.POTIONITEM, 1, 0), 'D', Blocks.DISPENSER, 'b', Items.BUCKET, 'B', Blocks.STONE_BUTTON);
		
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.mine_o_saur_eye), "GlG", "lel", "GlG", 'G', Blocks.GLASS, 'l', new ItemStack(Items.DYE, 1, 4), 'e', Items.ENDER_EYE);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.mine_o_saur_skull_half), "iie", "iii", "ppp", 'i', Items.IRON_INGOT, 'e', GadgetItems.mine_o_saur_eye, 'p', Items.DIAMOND_PICKAXE);
		GameRegistry.addShapelessRecipe(new ItemStack(GadgetItems.mine_o_saur_skull), GadgetItems.mine_o_saur_skull_half, GadgetItems.mine_o_saur_skull_half);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.mine_o_saur_jaw), "ppp", "iiP", 'p', Items.DIAMOND_PICKAXE, 'i', Items.IRON_INGOT, 'P', Blocks.STICKY_PISTON);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.mine_o_saur_head), "s", "j", 's', GadgetItems.mine_o_saur_skull, 'j', GadgetItems.mine_o_saur_jaw);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.mine_o_saur_arm), "iPi", "iPi", " p ", 'i', Items.IRON_INGOT, 'P', Blocks.STICKY_PISTON, 'p', Items.DIAMOND_PICKAXE);
		GameRegistry.addShapelessRecipe(new ItemStack(GadgetItems.mine_o_saur_arm_pair), GadgetItems.mine_o_saur_arm, GadgetItems.mine_o_saur_arm);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.mine_o_saur_leg), "iPi", "IPi", "ppi", 'i', Items.IRON_INGOT, 'P', Blocks.STICKY_PISTON, 'I', Blocks.IRON_BLOCK, 'p', Items.DIAMOND_PICKAXE);
		GameRegistry.addShapelessRecipe(new ItemStack(GadgetItems.mine_o_saur_leg_pair), GadgetItems.mine_o_saur_leg, GadgetItems.mine_o_saur_leg);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.mine_o_saur_tail), "Iii", "PPP", "Iii", 'I', Blocks.IRON_BLOCK, 'i', Items.IRON_INGOT, 'P', Blocks.STICKY_PISTON);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.mine_o_saur_body), "III", "IRI", "III", 'I', Blocks.IRON_BLOCK, 'R', Blocks.REDSTONE_BLOCK);
		GameRegistry.addShapedRecipe(new ItemStack(GadgetItems.mine_o_saur), "h  ", "abt", " l ", 'h', GadgetItems.mine_o_saur_head, 'a', GadgetItems.mine_o_saur_arm_pair, 'b', GadgetItems.mine_o_saur_body, 't', GadgetItems.mine_o_saur_tail, 'l', GadgetItems.mine_o_saur_leg_pair);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
