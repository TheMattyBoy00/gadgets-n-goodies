package themattyboy.gadgetsngoodies.main;

import themattyboy.gadgetsngoodies.init.GadgetItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class GadgetTab extends CreativeTabs {

	public GadgetTab(String label) {
		super(label);
		this.setBackgroundImageName("gadgetsngoodies.png");
	}

	@Override
	public Item getTabIconItem() {
		return GadgetItems.grappling_hook;
	}
	
	/*@Override
	public boolean hasSearchBar() {
		return true;
	}
	
	@Override
	public int getSearchbarWidth()
    {
        return 53;
    }*/

}
