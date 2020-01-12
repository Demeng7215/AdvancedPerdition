package com.demeng7215.advancedperdition.listeners;

import com.demeng7215.advancedperdition.preferences.Settings;
import com.demeng7215.advancedperdition.utils.PerditionPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class XpGainEvent implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPvpXpEvent(PlayerDeathEvent e) {

		if (e.getEntity().getKiller() == null) return;

		PerditionPlayer perditionPlayer = PerditionPlayer.of(e.getEntity().getKiller().getUniqueId().toString());
		perditionPlayer.setXp(perditionPlayer.getXp() + Integer.parseInt(Settings.KILL_PLAYER_XP.getText()));
		perditionPlayer.save();
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPveXpEvent(EntityDeathEvent e) {

		if (e.getEntity().getKiller() == null) return;

		PerditionPlayer perditionPlayer = PerditionPlayer.of(e.getEntity().getKiller().getUniqueId().toString());
		perditionPlayer.setXp(perditionPlayer.getXp() + Integer.parseInt(Settings.KILL_ENTITY_XP.getText()));
		perditionPlayer.save();
	}
}
