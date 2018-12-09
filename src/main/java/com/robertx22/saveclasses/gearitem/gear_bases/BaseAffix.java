package com.robertx22.saveclasses.gearitem.gear_bases;

import java.util.List;

import com.robertx22.database.IGUID;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public abstract class BaseAffix implements IWeighted, IGUID {

    public BaseAffix() {
    }

    public abstract String GUID();

    public abstract String locName();

    @Override
    public int Weight() {
	return IWeighted.UncommonWeight;
    }

    public abstract List<StatMod> StatMods();

}
