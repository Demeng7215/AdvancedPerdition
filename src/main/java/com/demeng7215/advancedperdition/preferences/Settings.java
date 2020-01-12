package com.demeng7215.advancedperdition.preferences;

import com.demeng7215.advancedperdition.AdvancedPerdition;

public enum Settings {

	KILL_PLAYER_XP(AdvancedPerdition.getInstance().getSettings().getString("amount-of-xp-per-player-kill")),
	KILL_ENTITY_XP(AdvancedPerdition.getInstance().getSettings().getString("amount-of-xp-per-entity-kill"));

	private String text;

	Settings(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
