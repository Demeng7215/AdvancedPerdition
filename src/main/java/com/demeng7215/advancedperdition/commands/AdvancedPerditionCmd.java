package com.demeng7215.advancedperdition.commands;

import com.demeng7215.advancedperdition.AdvancedPerdition;
import com.demeng7215.advancedperdition.preferences.Messages;
import com.demeng7215.demlib.api.Common;
import com.demeng7215.demlib.api.CustomCommand;
import com.demeng7215.demlib.api.messages.MessageUtils;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class AdvancedPerditionCmd extends CustomCommand {

	private AdvancedPerdition i;

	public AdvancedPerditionCmd(AdvancedPerdition i) {
		super("advancedperdition");

		this.i = i;

		setDescription("The main command for AdvancedPerdition.");
		setAliases(Collections.singletonList("perdition"));
	}

	@Override
	protected void run(CommandSender sender, String[] args) {

		if (args.length < 1 || args[0].equalsIgnoreCase("info")) {
			MessageUtils.tellWithoutPrefix(sender, "&5&lRunning AdvancedPerdition v" + Common.getVersion() + ".",
					"&5View a list of commands with &b/perdition help&4.");
			return;
		}

		switch (args[0]) {

			case "help":
				MessageUtils.tellWithoutPrefix(sender, "&5&lList of Commands for AdvancedPerdition",
						"&4/perdition info &8- &bDisplays basic plugin info.",
						"&4/perdition help &8- &bDisplays a list of commands.",
						"&4/perdition reload &8- &bReload configuration files.",
						"&4/profile <player> &8- &bDisplays player's class, subclass, abilities info.",
						"&4/setprofile <player> <class> <subclass> <ability> <xp> &8- &bUpdates the player's profile.",
						"&4/setsubclassnpc <subclass> &8- &bBinds the NPC you are looking at to the specified NPC.");
				return;

			case "reload":
				if (!checkHasPerm("perdition.reload", sender, Messages.INSUFFICIENT_PERMS.getText())) return;

				i.getSettingsFile().reloadConfig();
				i.getLanguageFile().reloadConfig();
				i.getDataFile().reloadConfig();

				MessageUtils.tell(sender, Messages.RELOADED.getText());
				return;

			default:
				MessageUtils.tellWithoutPrefix(sender, "&5&lRunning AdvancedPerdition v" + Common.getVersion() + ".",
						"&5View a list of commands with &b/perdition help&4.");
		}
	}


}
