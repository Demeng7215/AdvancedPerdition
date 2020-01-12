package com.demeng7215.advancedperdition.inventories;

import com.demeng7215.advancedperdition.utils.PerditionSubclass;
import com.demeng7215.demlib.api.gui.CustomInventory;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SubclassSelectionInv extends CustomInventory {

	public SubclassSelectionInv(PerditionSubclass subclass) {
		super(54, "&5&lSelect Your Subclass");

		setItem(4, new ItemStack(Material.NETHER_STAR), "&5Chosen Subclass: &4" + subclass.getText(),
				Arrays.asList("", "&bPlease confirm you would like to set &7" + subclass.getText() +
						" &bas your subclass.", "You will not be able to change your subclass later."), null);
	}
}
