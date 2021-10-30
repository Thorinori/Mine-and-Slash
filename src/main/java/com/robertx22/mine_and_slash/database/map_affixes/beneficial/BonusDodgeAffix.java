package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusDodgeAffix extends BeneficialMapAffix {

    @Override
    public String GUID() {
        return "bonus_dodge";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new ArmorFlat().size(StatMod.Size.DOUBLE), percent));
    }

    @Override
    public float lootMulti() {
        return 1.4F;
    }

}