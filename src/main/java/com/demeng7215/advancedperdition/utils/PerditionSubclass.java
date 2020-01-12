package com.demeng7215.advancedperdition.utils;

public enum PerditionSubclass {

	DEMON_DAEVA("Daeva"), DEMON_TEMPTER("Tempter"), DEMON_HYBRID("Hybrid"),
	ANGEL_GUARDIAN("Guardian"), ANGEL_ARCHANGEL("ArchAngel"), ANGEL_SAVANT("Savant"),
	NOTHING("N/A");

	private String text;

	PerditionSubclass(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
