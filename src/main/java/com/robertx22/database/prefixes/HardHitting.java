package com.robertx22.database.prefixes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.mods.flat.CriticalDamageFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class HardHitting extends Prefix {

	public HardHitting() {
	}

	@Override
	public String Name() {
		return "Hard Hitting";
	}

	@Override
	public List<StatMod> StatMods() {

		return Arrays.asList(new CriticalDamageFlat());

	}

}