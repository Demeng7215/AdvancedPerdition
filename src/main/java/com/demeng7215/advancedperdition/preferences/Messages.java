package com.demeng7215.advancedperdition.preferences;

import com.demeng7215.advancedperdition.AdvancedPerdition;

public enum Messages {

	PREFIX(AdvancedPerdition.getInstance().getLanguage().getString("prefix")),
	INSUFFICIENT_PERMS(AdvancedPerdition.getInstance().getLanguage().getString("insufficient-permissions")),
	CONSOLE(AdvancedPerdition.getInstance().getLanguage().getString("player-only-command")),
	RELOADED(AdvancedPerdition.getInstance().getLanguage().getString("configs-reloaded")),
	INVALID_ARGS(AdvancedPerdition.getInstance().getLanguage().getString("invalid-arguments")),
	INVALID_PLAYER(AdvancedPerdition.getInstance().getLanguage().getString("target-does-not-exist")),
	PROFILE_EDITED(AdvancedPerdition.getInstance().getLanguage().getString("profile-edited-successfully")),
	PLAYER_INFO(AdvancedPerdition.getInstance().getLanguage().getString("player-info")),
	NOT_NPC(AdvancedPerdition.getInstance().getLanguage().getString("not-looking-at-npc")),
	SUBCLASS_SET(AdvancedPerdition.getInstance().getLanguage().getString("subclass-successfully-bound-to-npc")),
	SUBCLASS_ALREADY_SET(AdvancedPerdition.getInstance().getLanguage().getString("subclass-already-set"));

	private String text;

	Messages(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
