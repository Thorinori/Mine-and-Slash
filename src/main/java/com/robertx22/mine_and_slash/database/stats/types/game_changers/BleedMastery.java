package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.BleedMasteryEffect;
import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.potion_effects.all.BleedPotion;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.List;
import java.util.stream.Collectors;

public class BleedMastery extends BaseGameChangerTrait implements IStatEffects {

    private BleedMastery() {
    }

    public static final BleedMastery INSTANCE = new BleedMastery();

    static int LOSE_PERC = 25;

    @Override
    public String getIconPath() {
        return "game_changers/bleed_mastery";
    }

    @Override
    public String locDescForLangFile() {
        return "Your basic attacks inflict bleed stacks (max 8) which each deal " + BleedPotion.CALC.getMultiAsPercent() + " percent of your physical damage."
            ;
    }

    @Override
    public String locNameForLangFile() {
        return "Bleed Mastery";
    }

    @Override
    public String GUID() {
        return "bleed_mastery_trait";
    }

    @Override
    public IStatEffect getEffect() {
        return BleedMasteryEffect.INSTANCE;
    }

    @Override
    public List<ExactStatData> getExactStats() {

        List<ExactStatData> list = new AllElementalDamage(Elements.Nature).generateAllSingleVariations()
                .stream()
                .map(x -> new ExactStatData(-25, StatModTypes.Flat, x))
                .collect(Collectors.toList());
        ;

        list.add(new ExactStatData(-10, StatModTypes.Multi, PhysicalDamage.getInstance()));

        return list;
    }

}


