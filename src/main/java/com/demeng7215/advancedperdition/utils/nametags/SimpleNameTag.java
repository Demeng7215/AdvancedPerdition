package com.demeng7215.advancedperdition.utils.nametags;

import com.demeng7215.demlib.api.messages.MessageUtils;

public class SimpleNameTag implements NameTag {

	private String text;

	SimpleNameTag(String text) {
		this.text = MessageUtils.colorize(text);
	}

	public String getText() {
		return this.text;
	}
}