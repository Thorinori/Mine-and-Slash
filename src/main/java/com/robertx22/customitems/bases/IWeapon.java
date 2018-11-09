package com.robertx22.customitems.bases;

import com.robertx22.saveclasses.Unit;

import net.minecraft.entity.EntityLivingBase;

public interface IWeapon {
	public int GetEnergyCost();

	public boolean Attack(EntityLivingBase source, EntityLivingBase target, Unit unitsource);
}
