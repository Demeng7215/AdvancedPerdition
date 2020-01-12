package com.demeng7215.advancedperdition.listeners;

import com.demeng7215.advancedperdition.AdvancedPerdition;
import com.demeng7215.advancedperdition.inventories.SubclassSelectionInv;
import com.demeng7215.advancedperdition.preferences.Messages;
import com.demeng7215.advancedperdition.utils.PerditionPlayer;
import com.demeng7215.advancedperdition.utils.PerditionSubclass;
import com.demeng7215.demlib.api.messages.MessageUtils;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Objects;

public class SubclassNpcClickEvent implements Listener {
	private AdvancedPerdition i;

	public SubclassNpcClickEvent(AdvancedPerdition i) {
		this.i = i;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onSubclassNpcClickEvent(NPCRightClickEvent e) {

		if (!PerditionPlayer.of(e.getClicker().getUniqueId().toString())
				.getPlayerSubclass().name().equals("NOTHING")) {
			MessageUtils.tell(e.getClicker(), Messages.SUBCLASS_ALREADY_SET.getText());
			return;
		}

		new SubclassSelectionInv(Objects.requireNonNull(getSubclassByNpcId(e.getNPC().getId())))
				.open(e.getClicker());
	}

	private PerditionSubclass getSubclassByNpcId(int npcId) {
		for (String key : i.getData().getConfigurationSection("subclass-npcs").getKeys(false)) {
			if (i.getData().getInt("subclass-npcs." + key) == npcId) return PerditionSubclass.valueOf(key);
		}
		return null;
	}
}
