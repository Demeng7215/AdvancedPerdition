package com.demeng7215.advancedperdition.utils;

import com.demeng7215.advancedperdition.AdvancedPerdition;
import com.demeng7215.advancedperdition.utils.nametags.NameTag;
import com.demeng7215.demlib.api.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.UUID;

public enum PerditionClass {

	ANGEL("Angels"), DEMON("Demons"), NOTHING("N/A");

	private String text;

	PerditionClass(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
