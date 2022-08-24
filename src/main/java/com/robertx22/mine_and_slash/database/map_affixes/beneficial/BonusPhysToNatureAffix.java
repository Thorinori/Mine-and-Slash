package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysToFireConvFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysToNatureConvFlat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusPhysToNatureAffix extends BeneficialMapAffix {

    @Override
    public String GUID() {
        return "bonus_phys_to_nature";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new PhysToNatureConvFlat().size(StatMod.Size.NORMAL), percent));
    }

    @Override
    public float lootMulti() {
        return 1.3F;
    }

}