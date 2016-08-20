package themattyboy.gadgetsngoodies.achievements;

import themattyboy.gadgetsngoodies.init.GadgetItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class GadgetAchievements {

	public static Achievement high_flier;
	public static Achievement absolute_pitch;
	public static Achievement july4th;
	public static Achievement flying_pig;
	//public static Achievement higher_flier;
	public static Achievement being_the_ghast;
	//public static Achievement risky_dives;
	public static Achievement farmer;
	public static Achievement drop_the_bass;
	
	public static void init() {
		//high_flier = new Achievement("achievement.high_flier", "high_flier", 0, 0, GadgetItems.grappling_hook, (Achievement)null).initIndependentStat().registerStat();
		absolute_pitch = new Achievement("achievement.absolute_pitch", "absolute_pitch", -2, 1, GadgetItems.musical_instrument, (Achievement)null).initIndependentStat().registerStat();
		july4th = new Achievement("achievement.july4th", "july4th", 2, -3, Blocks.TNT, (Achievement)null).initIndependentStat().registerStat();
		flying_pig = new Achievement("achievement.flying_pig", "flying_pig", 3, -1, GadgetItems.block_o_copter, (Achievement)null).initIndependentStat().registerStat();
		high_flier = new Achievement("achievement.high_flier", "high_flier", 0, 0, GadgetItems.jetpack, high_flier).initIndependentStat().registerStat();
		being_the_ghast = new Achievement("achievement.being_the_ghast", "being_the_ghast", -3, -2, GadgetItems.fireball_dispenser, (Achievement)null).initIndependentStat().registerStat();
		farmer = new Achievement("achievement.farmer", "farmer", -1, 2, GadgetItems.water_gun, (Achievement)null).initIndependentStat().registerStat();
		drop_the_bass = new Achievement("achievement.drop_the_bass", "drop_the_bass", 3, 2, Item.getItemFromBlock(Blocks.NOTEBLOCK), (Achievement)null).initIndependentStat().registerStat();
		//risky_dives = new Achievement("achievement.risky_dives", "risky_dives", 2, 1, GadgetItems.hang_glider, (Achievement)null).initIndependentStat().registerStat();
		
		AchievementPage.registerAchievementPage(new AchievementPage("Gadgets n' Goodies", new Achievement[]{high_flier, absolute_pitch, july4th, flying_pig, /*higher_flier, */being_the_ghast, /*risky_dives*/ farmer, drop_the_bass}));
	}
}
