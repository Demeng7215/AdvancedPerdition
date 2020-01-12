package com.demeng7215.advancedperdition.commands;

import com.demeng7215.advancedperdition.AdvancedPerdition;
import com.demeng7215.advancedperdition.preferences.Messages;
import com.demeng7215.advancedperdition.utils.PerditionSubclass;
import com.demeng7215.demlib.api.CustomCommand;
import com.demeng7215.demlib.api.messages.MessageUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import java.io.IOException;
import java.util.List;

public class SetSubclassNpcCmd extends CustomCommand {

	private AdvancedPerdition i;

	public SetSubclassNpcCmd(AdvancedPerdition i) {
		super("setsubclassnpc");

		this.i = i;

		setDescription("Sets the mentioned NPC as the NPC responsible for that specified subclass.");
	}

	@Override
	protected void run(CommandSender sender, String[] args) {

		if (!checkIsPlayer(sender, Messages.CONSOLE.getText())) return;

		if (!checkHasPerm("perdition.setsubclassnpc", sender,
				Messages.INSUFFICIENT_PERMS.getText())) return;

		if (!checkArgsStrict(args, 1, sender, Messages.INVALID_ARGS.getText())) return;

		final Player p = (Player) sender;

		final Entity e = getTargetEntity(p);

		if (e == null || !e.hasMetadata("NPC")) {
			MessageUtils.tell(p, Messages.NOT_NPC.getText());
			return;
		}

		NPC targetNPC = CitizensAPI.getNPCRegistry().getNPC(e);

		if (targetNPC == null || !targetNPC.isSpawned()) {
			MessageUtils.tell(p, Messages.NOT_NPC.getText());
			return;
		}

		if (!isSubclass(args[0])) {
			MessageUtils.tell(p, Messages.INVALID_ARGS.getText());
			return;
		}

		i.getData().set("subclass-npcs." + args[0].toUpperCase(), targetNPC.getId());
		try {
			i.getDataFile().saveConfig();
		} catch (final IOException ex) {
			MessageUtils.error(ex, 4, "Failed to save data.", false);
			return;
		}

		MessageUtils.tell(p, Messages.SUBCLASS_SET.getText());
	}

	private Entity getTargetEntity(Player p) {

		List<Entity> targetList = p.getNearbyEntities(10, 10, 10);
		BlockIterator bi = new BlockIterator(p, 10);
		Entity target = null;

		while (bi.hasNext()) {

			Block b = bi.next();
			int bx = b.getX();
			int by = b.getY();
			int bz = b.getZ();

			if (b.getType().isSolid()) {
				break;
			} else {

				for (Entity e : targetList) {

					Location l = e.getLocation();
					double ex = l.getX();
					double ey = l.getY();
					double ez = l.getZ();

					if ((bx - .75 <= ex && ex <= bx + 1.75) && (bz - .75 <= ez && ez <= bz + 1.75) && (by - 1 <= ey && ey <= by + 2.5))
						target = e;
				}
			}
		}
		return target;
	}

	private boolean isSubclass(String value) {
		for (PerditionSubclass subclass : PerditionSubclass.values())
			if (subclass.name().equals(value.toUpperCase())) return true;

		return false;
	}
}
