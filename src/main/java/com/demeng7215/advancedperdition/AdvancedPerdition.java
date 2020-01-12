package com.demeng7215.advancedperdition;

import com.demeng7215.advancedperdition.commands.AdvancedPerditionCmd;
import com.demeng7215.advancedperdition.commands.ProfileCmd;
import com.demeng7215.advancedperdition.commands.SetProfileCmd;
import com.demeng7215.advancedperdition.commands.SetSubclassNpcCmd;
import com.demeng7215.advancedperdition.listeners.PlayerFirstJoinEvent;
import com.demeng7215.advancedperdition.listeners.SubclassNpcClickEvent;
import com.demeng7215.advancedperdition.listeners.XpGainEvent;
import com.demeng7215.advancedperdition.preferences.Messages;
import com.demeng7215.demlib.DemLib;
import com.demeng7215.demlib.api.BlacklistSystem;
import com.demeng7215.demlib.api.Common;
import com.demeng7215.demlib.api.DeveloperNotifications;
import com.demeng7215.demlib.api.Registerer;
import com.demeng7215.demlib.api.files.CustomConfig;
import com.demeng7215.demlib.api.messages.MessageUtils;
import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class AdvancedPerdition extends JavaPlugin {

	/* ERROR CODES
	1: Failed to load configuration files.
	2: Failed to hook into Citizens.
	3: Failed to authenticate (connect to auth servers).
	4: Failed to save data.
	 */

	/* PERMISSIONS
	perdition.reload: Ability to reload configuration files.
	perdition.setprofile: Ability to set other player's classes, subclasses, abilities, and XP.
	perdition.profile: Ability to view a player's class, subclass, abilities, and XP.
	perdition.setsubclassnpc: Ability to assign the NPC you are looking at to a specific subclass.
	 */

	private static AdvancedPerdition i;

	@Getter
	public CustomConfig settingsFile;

	@Getter
	public CustomConfig languageFile;

	@Getter
	public CustomConfig dataFile;

	@Override
	public void onEnable() {

		i = this;
		DemLib.setPlugin(this, "74gTEqf7ytVZjDSr");
		MessageUtils.setPrefix("&8[&5AdvancedPerdition&8] &r");

		try {
			if (BlacklistSystem.checkBlacklist()) return;
		} catch (final IOException ex) {
			MessageUtils.error(ex, 3, "Failed to authenticate.", false);
		}

		for(Player p : Bukkit.getOnlinePlayers()) p.kickPlayer("Disconnected");

		getLogger().info("Loading configuration files...");
		if (!loadConfigs()) return;
		MessageUtils.setPrefix(Messages.PREFIX.getText());

		getLogger().info("Registering commands...");
		Registerer.registerCommand(new AdvancedPerditionCmd(this));
		Registerer.registerCommand(new SetProfileCmd());
		Registerer.registerCommand(new ProfileCmd());
		Registerer.registerCommand(new SetSubclassNpcCmd(this));

		getLogger().info("Registering listeners...");
		Registerer.registerListeners(new PlayerFirstJoinEvent());
		Registerer.registerListeners(new XpGainEvent());
		Registerer.registerListeners(new SubclassNpcClickEvent(this));
		DeveloperNotifications.enableNotifications("ca19af04-a156-482e-a35d-3f5f434975b5");

		getLogger().info("Hooking into Citizens API...");
		if (!isCitizensLoaded()) return;

		MessageUtils.console("&aAdvancedPerdition v" + Common.getVersion() + " has been successfully enabled.");
	}

	@Override
	public void onDisable() {
		MessageUtils.console("&cAdvancedPerdition v" + Common.getVersion() + " has been successfully disabled.");
	}

	private boolean loadConfigs() {

		String fileName = "s";

		try {

			fileName = " settings.yml";
			this.settingsFile = new CustomConfig("settings.yml");

			fileName = " language.yml";
			this.languageFile = new CustomConfig("language.yml");

			fileName = " data.yml";
			this.dataFile = new CustomConfig("data.yml");

		} catch (final Exception ex) {
			MessageUtils.error(ex, 1, "Failed to load configuration file" + fileName + ".", true);
			return false;
		}

		return true;
	}

	private boolean isCitizensLoaded() {

		if (CitizensAPI.getPlugin() == null) {
			MessageUtils.error(null, 2, "Failed to hook into CitizensNPC API: Is it loaded?", true);
			return false;
		}

		return true;
	}

	public static AdvancedPerdition getInstance() {
		return i;
	}

	public FileConfiguration getSettings() {
		return this.settingsFile.getConfig();
	}

	public FileConfiguration getLanguage() {
		return this.languageFile.getConfig();
	}

	public FileConfiguration getData() {
		return this.dataFile.getConfig();
	}
}
