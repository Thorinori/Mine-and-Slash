package com.robertx22.mine_and_slash.database.stats.effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.math.MathHelper;

public class PhysicalDamageTakenEffect extends BaseDamageEffect {

    private PhysicalDamageTakenEffect() {
    }

    public static PhysicalDamageTakenEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        float damageIncrease = MathHelper.clamp(data.getAverageValue(), stat.minimumValue, stat.maximumValue);

        effect.number *=  (1 + damageIncrease / 100);

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.element.equals(Elements.Physical);
    }

    private static class SingletonHolder {
        private static final PhysicalDamageTakenEffect INSTANCE = new PhysicalDamageTakenEffect();
    }
}