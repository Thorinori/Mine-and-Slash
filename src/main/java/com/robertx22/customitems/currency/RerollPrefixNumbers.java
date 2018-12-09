package com.robertx22.customitems.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RerollPrefixNumbers extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
	return "reroll_prefix_numbers";
    }

    private static final String name = "reroll_prefix_numbers";

    @GameRegistry.ObjectHolder(Ref.MODID + ":reroll_prefix_numbers")
    public static final Item ITEM = null;

    public RerollPrefixNumbers() {

	super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new RerollPrefixNumbers());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack) {

	GearItemData gear = Gear.Load(stack);

	gear.prefix.RerollNumbers(gear);

	Gear.Save(stack, gear);

	return stack;
    }

    @Override
    public boolean CanItemBeModified(ItemStack stack) {
	GearItemData gear = Gear.Load(stack);

	return gear != null && gear.prefix != null;
    }

    @Override
    public int Tier() {
	return 15;
    }

    @Override
    public int Weight() {
	return this.EpicWeight;
    }
}