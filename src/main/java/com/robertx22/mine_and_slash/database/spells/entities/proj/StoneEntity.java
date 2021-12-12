package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.ImbueSpell;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.potion_effects.ranger.ImbueEffect;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class StoneEntity extends EntityBaseProjectile {

    public StoneEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
    }

    public StoneEntity(World worldIn) {
        super(EntityRegister.STONE_ENTITY, worldIn);
    }

    public StoneEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.STONE_ENTITY, world);
    }

    @Override
    public void initSpellEntity() {
        this.setDeathTime(80);
    }

    @Override
    public double radius() {
        return 0.5F;
    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 2; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.1F);
                    ParticleUtils.spawn(ParticleTypes.EFFECT, world, p);
                }
            }
        }
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {

        RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            this.onImpact(raytraceResultIn);
            this.playSound(SoundEvents.ENTITY_SHULKER_BULLET_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

        } else if (raytraceresult$type == RayTraceResult.Type.BLOCK && this.getTicksInAir() > 40) {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceResultIn;
            BlockState blockstate = this.world.getBlockState(blockraytraceresult.getPos());

            Vec3d vec3d = blockraytraceresult.getHitVec()
                    .subtract(this.posX, this.posY, this.posZ);
            this.setMotion(vec3d);
            Vec3d vec3d1 = vec3d.normalize()
                    .scale((double) 0.05F);
            this.posX -= vec3d1.x;
            this.posY -= vec3d1.y;
            this.posZ -= vec3d1.z;
            this.inGround = true;

            this.onImpact(blockraytraceresult);

            blockstate.onProjectileCollision(this.world, blockstate, blockraytraceresult, this);

        }
    }

    public void onHit(LivingEntity entity) {

        try {

            LivingEntity caster = getCaster();

            BaseSpell spell = getSpellData().getSpell();

            SpellDamageEffect dmg = this.getSetupSpellDamage(entity);
            ParticleUtils.spawn(ParticleTypes.EXPLOSION, world, entity.getPositionVector());
            SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.1F);

            dmg.Activate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onImpact(RayTraceResult result) {
        try {
            LivingEntity entityHit = getEntityHit(result, 0.5D);

            if (entityHit != null) {
                if (world.isRemote) {
                    SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 1F, 0.9F);
                }

                if (!entityHit.world.isRemote) {
                    onHit(entityHit);
                }

            } else {
                if (world.isRemote) {
                    SoundUtils.playSound(this, SoundEvents.BLOCK_STONE_HIT, 1.0F, 0.9F);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.remove();

    }


    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.STONE);
    }

}
