package com.demeng7215.advancedperdition.commands;

import com.demeng7215.advancedperdition.preferences.Messages;
import com.demeng7215.advancedperdition.utils.PerditionPlayer;
import com.demeng7215.demlib.api.CustomCommand;
import com.demeng7215.demlib.api.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ProfileCmd extends CustomCommand {

	public ProfileCmd() {
		super("profile");

		setDescription("Get the class, subclass, and abilities of the player.");
		setAliases(Arrays.asList("class", "subclass", "perditioninfo"));
	}

	@Override
	protected void run(CommandSender sender, String[] args) {

		if (!checkHasPerm("perdition.profile", sender, Messages.INSUFFICIENT_PERMS.getText())) return;

		final OfflinePlayer target;

		if (sender instanceof Player && args.length == 0) {
			target = (Player) sender;
		} else {
			if (!checkArgsStrict(args, 1, sender, Messages.INVALID_ARGS.getText())) return;
			target = Bukkit.getServer().getOfflinePlayer(args[0]);
		}

		if (target.getUniqueId().version() == 3 ||
				PerditionPlayer.of(target.getUniqueId().toString()) == null) {
			MessageUtils.tell(sender, Messages.INVALID_PLAYER.getText());
			return;
		}

		PerditionPlayer perditionPlayer = PerditionPlayer.of(target.getUniqueId().toString());

		MessageUtils.tellWithoutPrefix(sender, Messages.PLAYER_INFO.getText()
				.replace("%target%", target.getName())
				.replace("%class%", perditionPlayer.getPlayerClass().getText())
				.replace("%subclass%", perditionPlayer.getPlayerSubclass().getText())
				.replace("%ability%", perditionPlayer.getPlayerAbility().getText())
				.replace("%xp%", String.valueOf(perditionPlayer.getXp())));
	}
}
