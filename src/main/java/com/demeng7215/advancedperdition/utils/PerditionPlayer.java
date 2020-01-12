package com.demeng7215.advancedperdition.utils;

import com.demeng7215.advancedperdition.AdvancedPerdition;
import com.demeng7215.advancedperdition.utils.nametags.NameTag;
import com.demeng7215.demlib.api.messages.MessageUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class PerditionPlayer {

	@Getter
	@Setter
	private String uuid;
	@Getter
	@Setter
	private PerditionClass playerClass;
	@Getter
	@Setter
	private PerditionSubclass playerSubclass;
	@Getter
	@Setter
	private PerditionAbility playerAbility;
	@Getter
	@Setter
	private int xp;

	public PerditionPlayer(String uuid, PerditionClass playerClass, PerditionSubclass playerSubclass,
						   PerditionAbility playerAbility, int xp) {
		this.uuid = uuid;
		this.playerClass = playerClass;
		this.playerSubclass = playerSubclass;
		this.playerAbility = playerAbility;
		this.xp = xp;
	}

	public static PerditionPlayer of(String targetUuid) {

		PerditionClass targetClass;
		PerditionSubclass targetSubclass;
		PerditionAbility targetAbility;
		int targetXp;

		try {
			targetClass = Objects.requireNonNull(
					PerditionClass.valueOf(AdvancedPerdition.getInstance().getData()
							.getString("player-data." + targetUuid + ".class")));

			targetSubclass = Objects.requireNonNull(
					PerditionSubclass.valueOf(AdvancedPerdition.getInstance().getData()
							.getString("player-data." + targetUuid + ".subclass")));

			targetAbility = Objects.requireNonNull(
					PerditionAbility.valueOf(AdvancedPerdition.getInstance().getData()
							.getString("player-data." + targetUuid + ".ability")));
			targetXp = AdvancedPerdition.getInstance().getData().getInt("player-data." + targetUuid + ".xp");

		} catch (NullPointerException ex) {
			return null;
		}

		return new PerditionPlayer(targetUuid, targetClass, targetSubclass, targetAbility, targetXp);
	}

	public void save() {

		AdvancedPerdition.getInstance().getData().set("player-data." + uuid + ".class", playerClass.name());
		AdvancedPerdition.getInstance().getData().set("player-data." + uuid + ".subclass", playerSubclass.name());
		AdvancedPerdition.getInstance().getData().set("player-data." + uuid + ".ability", playerAbility.name());
		AdvancedPerdition.getInstance().getData().set("player-data." + uuid + ".xp", xp);

		Player p = Bukkit.getServer().getPlayer(UUID.fromString(uuid));

		if (p != null)
			switch (playerClass) {
				case DEMON:
					NameTag.of("§c" + p.getName()).applyTo(p);
				case ANGEL:
					NameTag.of("§b" + p.getName()).applyTo(p);
				case NOTHING:
					NameTag.of("§f" + p.getName()).applyTo(p);
			}

		try {
			AdvancedPerdition.getInstance().getDataFile().saveConfig();
		} catch (final IOException ex) {
			MessageUtils.error(ex, 4, "Failed to save data.", false);
		}
	}
}
