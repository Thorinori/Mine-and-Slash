package com.robertx22.database.prefixes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.flat.DodgeFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class Evasive extends Prefix {

	public Evasive() {
	}

	@Override
	public String Name() {
		return "Evasive";
	}

	@Override
	public List<StatMod> StatMods() {
		return Arrays.asList(new DodgeFlat());
	}

}
