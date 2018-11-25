package com.robertx22.dimensions.world_providers;

import javax.annotation.Nullable;

import com.robertx22.dimensions.IWP;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.DimensionManager;

public abstract class BaseWorldProvider extends WorldProviderSurface implements IWP {

	public BaseWorldProvider() {

	}

	BlockPos spawn = null;

	@Override
	public DimensionType getDimensionType() {
		DimensionType type = null;

		try {
			type = DimensionManager.getProviderType(this.getDimension());
		} catch (IllegalArgumentException e) {
		}

		return type != null ? type : super.getDimensionType();
	}

	/**
	 * 
	 * Do not override this.
	 * 
	 * Returns true on clients (to allow rendering of sky etc, maybe even clouds).
	 * Returns false on servers (to disable Nether Portal mob spawning and sleeping
	 * in beds).
	 */
	@Override
	public boolean isSurfaceWorld() {
		return (this.world == null) ? false : this.world.isRemote;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	@Nullable
	public BlockPos getSpawnCoordinate() {
		return findSpawn();
	}

	private BlockPos findSpawn() {

		if (world == null) {
			return null;
		}

		BlockPos spawn = world.getTopSolidOrLiquidBlock(new BlockPos(0, 0, 0));

		world.setSpawnPoint(spawn);

		return spawn;

	}

	@Override
	public BlockPos getSpawnPoint() {
		return findSpawn();

	}

	@Override
	public String getSaveFolder() {
		return "MineAndSlash_MapWorld" + world.provider.getDimension();
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorOverworld(world, world.rand.nextLong(), true, "");

	}
}