package com.demeng7215.advancedperdition.commands;

import com.demeng7215.advancedperdition.preferences.Messages;
import com.demeng7215.advancedperdition.utils.PerditionAbility;
import com.demeng7215.advancedperdition.utils.PerditionClass;
import com.demeng7215.advancedperdition.utils.PerditionPlayer;
import com.demeng7215.advancedperdition.utils.PerditionSubclass;
import com.demeng7215.demlib.api.CustomCommand;
import com.demeng7215.demlib.api.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class SetProfileCmd extends CustomCommand {

	public SetProfileCmd() {
		super("setprofile");

		setDescription("Sets the player's class, subclass, ability, and XP.");
		setAliases(Arrays.asList("setclass", "setsubclass", "setability", "setxp", "setdata"));
	}

	@Override
	protected void run(CommandSender sender, String[] args) {

		if (!checkHasPerm("perdition.setprofile", sender, Messages.INSUFFICIENT_PERMS.getText())) return;

		if (!checkArgsStrict(args, 5, sender, Messages.INVALID_ARGS.getText())) return;

		final OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);

		if (target.getUniqueId().version() == 3 ||
				PerditionPlayer.of(target.getUniqueId().toString()) == null) {
			MessageUtils.tell(sender, Messages.INVALID_PLAYER.getText());
			return;
		}

		String subclassEnum = args[1] + "_" + args[2];
		String abilityEnum = subclassEnum + "_" + args[3];

		PerditionSubclass subclass = PerditionSubclass.valueOf(subclassEnum.toUpperCase());
		PerditionAbility ability = PerditionAbility.valueOf(abilityEnum.toUpperCase());

		if (!isPerditionClass(args[1]) || !isPerditionSubclass(
				PerditionClass.valueOf(args[1].toUpperCase()), args[2]) ||
				!isPerditionAbility(subclass, args[3]) || !isNumber(args[4])) {
			MessageUtils.tell(sender, Messages.INVALID_ARGS.getText());
			return;
		}

		new PerditionPlayer(target.getUniqueId().toString(), PerditionClass.valueOf(args[1]),
				subclass, ability, Integer.parseInt(args[4])).save();

		MessageUtils.tell(sender, Messages.PROFILE_EDITED.getText()
				.replace("%target%", args[0]));
		Bukkit.dispatchCommand(sender, "profile " + args[0]);
	}

	private boolean isPerditionClass(String value) {
		for (PerditionClass e : PerditionClass.values())
			if (e.name().equals(value.toUpperCase())) return true;
		return false;
	}

	private boolean isPerditionSubclass(PerditionClass perditionClass, String value) {
		String subclassEnum = perditionClass.name() + "_" + value;
		for (PerditionSubclass e : PerditionSubclass.values())
			if (e.name().equals(subclassEnum.toUpperCase())) return true;
		return false;
	}

	private boolean isPerditionAbility(PerditionSubclass perditionSubclass, String value) {
		String abilityEnum = perditionSubclass.name() + "_" + value;
		for (PerditionAbility e : PerditionAbility.values())
			if (e.name().equals(abilityEnum.toUpperCase())) return true;
		return false;
	}

	private boolean isNumber(String value) {
		try {
			int n = Integer.parseInt(value);
		} catch (final NumberFormatException ex) {
			return false;
		}
		return true;
	}
}
