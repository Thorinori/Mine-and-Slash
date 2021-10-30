package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.DoTSteal;
import com.robertx22.mine_and_slash.database.stats.types.resources.SpellSteal;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class DoTStealFlat extends StatMod {

    public DoTStealFlat() {
    }

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 8;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return DoTSteal.getInstance();
    }

}