package com.robertx22.mine_and_slash.database.stats.types.traits.atronachs;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.IncreasedProjSpeedFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class FireAtronach extends Trait implements IAffectsOtherStats {

    public static String GUID = "fire_atronach";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Fire).size(StatMod.Size.HALF_MORE)
                , new IncreasedProjSpeedFlat().size(StatMod.Size.NORMAL));

    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public String locNameForLangFile() {
        return "Fire Atronach";
    }
}
