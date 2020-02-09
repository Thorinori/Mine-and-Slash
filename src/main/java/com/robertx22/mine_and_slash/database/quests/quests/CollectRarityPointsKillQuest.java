package com.robertx22.mine_and_slash.database.quests.quests;

import com.robertx22.mine_and_slash.database.quests.base.BaseCollectMobRarityKillQuest;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CollectRarityPointsKillQuest extends BaseCollectMobRarityKillQuest {

    public static CollectRarityPointsKillQuest INSTANCE = new CollectRarityPointsKillQuest();

    private CollectRarityPointsKillQuest() {

    }

    @Override
    public ResourceLocation icon() {
        return new ResourceLocation(Ref.MODID, "textures/gui/quest_icons/kill_mobs.png");
    }

    @Override
    public ITextComponent name() {
        return Words.KillMobsCollectRarityPoints.locName();
    }

    @Override
    public float amountRequired() {
        return 100;
    }

    @Override
    public int Weight() {
        return super.Weight() * 4;
    }

    @Override
    public int minutes() {
        return 35;
    }

    @Override
    public String GUID() {
        return "kill_mobs_collect_rarity_points";
    }
}
