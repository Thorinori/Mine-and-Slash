package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class LightningTotemEntity extends EntityBaseProjectile {

    public LightningTotemEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
    }

    public LightningTotemEntity(World worldIn) {
        super(EntityRegister.LIGHTNING_TOTEM, worldIn);

    }

    public LightningTotemEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.LIGHTNING_TOTEM, world);

    }

    @Override
    public double radius() {
        return getSpellData().configs.get(SC.RADIUS);
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.TOTEM_OF_UNDYING);
    }

    @Override
    public void onTick() {

        int tickRate = getSpellData().configs.get(SC.TICK_RATE)
            .intValue();

        if (this.ticksExisted % tickRate == 0) {
            if (!world.isRemote) {

                List<LivingEntity> entities = EntityFinder.start(getCaster(), LivingEntity.class, getPositionVector())
                        .radius(radius()).searchFor(EntityFinder.SearchFor.ENEMIES)
                        .build();

                entities.forEach(x -> {

                    SpellDamageEffect dmg = dealSpellDamageTo(x, new Options().activatesEffect(false)
                            .knockbacks(false));
                    this.playSound(SoundEvents.ENTITY_BAT_TAKEOFF, 1.4f, 2.5f);

                    dmg.Activate();


                });
            } else {

                this.playSound(ModSounds.THUNDER.get(), 0.75f, 1.8f);
                // TODO why isnt this being cast?

            }
        }
        if (this.ticksExisted % tickRate == 4) {
            if (!world.isRemote) {
                this.playSound(ModSounds.THUNDER.get(), 1f, 1.4f);
            }
        }

        if (this.inGround && world.isRemote) {

            for (int i = 0; i < 60; i++) {
                Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), (float) radius());
                ParticleUtils.spawn(ParticleRegister.THUNDER, world, p);

            }

        }

    }

}
