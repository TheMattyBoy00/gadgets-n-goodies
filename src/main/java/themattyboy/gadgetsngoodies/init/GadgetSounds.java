package themattyboy.gadgetsngoodies.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import themattyboy.gadgetsngoodies.main.Reference;

public class GadgetSounds {
	
	public static String musicalInstrumentSoundName;
	public static SoundEvent bass_drop;
	
	public static void init() {
		final ResourceLocation bass_drop_location = new ResourceLocation(Reference.MOD_ID, "item.musical_instrument.drop_the_bass");
		
		bass_drop = GameRegistry.register(new SoundEvent(bass_drop_location).setRegistryName(bass_drop_location));
	}
}
