package themattyboy.gadgetsngoodies.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import themattyboy.gadgetsngoodies.blocks.BlockPlacedTNTCannon;
import themattyboy.gadgetsngoodies.main.Reference;

public class GadgetBlocks {
	
	public static Block placed_portable_TNT_cannon;
	
	public static void init() {
		placed_portable_TNT_cannon = new BlockPlacedTNTCannon(Material.ROCK).setUnlocalizedName("placed_portable_TNT_cannon").setHardness(1.5F).setResistance(2000);
	}
	
	public static void register() {
		GameRegistry.registerBlock(placed_portable_TNT_cannon, placed_portable_TNT_cannon.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders() {
		registerRender(placed_portable_TNT_cannon);
	}
	
	public static void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
	
}
