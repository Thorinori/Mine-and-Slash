package com.robertx22.mine_and_slash.database.spells.synergies.ocean;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.TidalWaveSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TidalWaveFrostSynergy extends OnDamageDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Tidal Waves"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("If the target is Chilled, deal extra damage: "));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 4);
        c.set(SC.ENERGY_COST, 1, 2);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 4, 10);
        c.setMaxLevel(6);
        return c;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return TidalWaveSpell.getInstance();
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {
        if (PotionEffectUtils.has(ctx.target, FrostEffect.INSTANCE)) {

            /*int num = getCalcVal(ctx.source);

            getSynergyDamage(ctx, num)
                    .Activate();*/

            int num = getPreCalcConfig().getCalc(Load.spells(ctx.source), this)
                    .getCalculatedValue(ctx.sourceData, Load.spells(ctx.source), this);

            DamageEffect dmg = new DamageEffect(
                    null, ctx.source, ctx.target, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
            dmg.element = getSpell()
                    .getElement();
            dmg.Activate();

        }
    }

    @Override
    public String locNameForLangFile() {
        return "Freezing Waves";
    }
}
