package net.simplicite_mc.roblikescake.simpliciteaddons.utilities;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

public class Misc {

	// Check if an EntityType is catchable or not.
	public static boolean isCatchable(EntityType entityType) {
		switch (entityType) {
			case CHICKEN:
			case COW:
			case OCELOT:
			case PIG:
			case SHEEP:
			case HORSE:
			case SQUID:
			case VILLAGER:
			case MUSHROOM_COW:
			case WOLF:
				return true;
			default:
				return false;
		}
	}

	// Check if a block is water or not.
	public static boolean isBlockWater(World world, int spongeX, int spongeY, int spongeZ) {
		Block block = world.getBlockAt(spongeX, spongeY, spongeZ);
		Material material = block.getType();
		return material == Material.WATER || material == Material.STATIONARY_WATER;
	}

	// Clear water in a 5x5 radius centered around the sponge.
	public static void clearSpongeWater(World world, int spongeX, int spongeY, int spongeZ, int spongeClearRadius) {
		for (int radiusX = -spongeClearRadius; radiusX <= spongeClearRadius; radiusX++) {
			for (int radiusY = -spongeClearRadius; radiusY <= spongeClearRadius; radiusY++) {
				for (int radiusZ = -spongeClearRadius; radiusZ <= spongeClearRadius; radiusZ++) {
					if (Misc.isBlockWater(world, spongeX + radiusX, spongeY + radiusY, spongeZ + radiusZ)) {
						world.getBlockAt(spongeX + radiusX, spongeY + radiusY, spongeZ + radiusZ).setType(Material.AIR);
					}
				}
			}
		}
	}
}
