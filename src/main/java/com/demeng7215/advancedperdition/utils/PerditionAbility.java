package com.demeng7215.advancedperdition.utils;

public enum PerditionAbility {

	DEMON_DAEVA_BLOOD_ARMOR("Blood Armor"),
	DEMON_DAEVA_DEMONS_HAND("Demon's Hand"),
	DEMON_DAEVA_CORRUPTION("Corruption"),

	DEMON_TEMPTER_BLOOD_SOAKED_BLADE("Blood Soaked Blade"),
	DEMON_TEMPTER_DARK_FIRE("Dark Fire"),
	DEMON_TEMPTER_ELECTRIC_BLAST("Electric Blast"),

	DEMON_HYBRID_BEASTMASTER("Beastmaster"),
	DEMON_HYBRID_DEMON_CLAWS("Demon Claws"),
	DEMON_HYBRID_RETREAT("Retreat"),

	ANGEL_GUARDIAN_STONESKIN("Stoneskin"),
	ANGEL_GUARDIAN_THORN_ARMOR("Thorn Armor"),
	ANGEL_GUARDIAN_STUN("Stun"),

	ANGEL_ARCHANGEL_AUTOAMMO("Autoammo"),
	ANGEL_ARCHANGEL_ANTIMATTER("Antimatter"),
	ANGEL_ARCHANGEL_BLESSED_BLADE("Blessed Blade"),

	ANGEL_SAVANT_CLOAK("Cloak"),
	ANGEL_SAVANT_POTION_GENERATOR("Potion Generator"),
	ANGEL_SAVANT_HEALING_AURA("Healing Aura"),

	NOTHING("N/A");

	private String text;

	PerditionAbility(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
