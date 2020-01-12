package com.demeng7215.advancedperdition.listeners;

import com.demeng7215.advancedperdition.utils.PerditionAbility;
import com.demeng7215.advancedperdition.utils.PerditionClass;
import com.demeng7215.advancedperdition.utils.PerditionPlayer;
import com.demeng7215.advancedperdition.utils.PerditionSubclass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerFirstJoinEvent implements Listener {

	@EventHandler
	public void onPlayerFirstJoin(PlayerJoinEvent e) {

		final Player p = e.getPlayer();

		// On first join, set their class as NOTHING.
		if (PerditionPlayer.of(p.getUniqueId().toString()) == null) {
			new PerditionPlayer(p.getUniqueId().toString(), PerditionClass.NOTHING, PerditionSubclass.NOTHING,
					PerditionAbility.NOTHING, 0).save();
		}
	}
}
