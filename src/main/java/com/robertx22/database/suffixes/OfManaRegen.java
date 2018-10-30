package com.robertx22.database.suffixes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.percent.ManaRegenPercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public class OfManaRegen extends Suffix {

	public OfManaRegen() {
	}

	@Override
	public String Name() {
		return "Of Mana Regen";
	}

	@Override
	public List<StatMod> StatMods() {
		return Arrays.asList(new ManaRegenPercent());
	}

}
