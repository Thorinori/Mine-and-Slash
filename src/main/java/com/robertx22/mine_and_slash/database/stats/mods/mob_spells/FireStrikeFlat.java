package com.robertx22.mine_and_slash.database.stats.mods.mob_spells;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.mob_spells.FireStrike;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDispersion;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class FireStrikeFlat extends StatMod {

    @Override
    public float Min() {
        return 100;

    }

    @Override
    public float Max() {
        return 100;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new FireStrike();
    }

}
