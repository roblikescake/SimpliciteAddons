package net.simplicite_mc.roblikescake.simpliciteaddons.utilities;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

public class Misc {

	/**
	 * Check if an entity is catchable or not.
	 * <p/>
	 *
	 * @param entityType The entity to check
	 * @return true if catchable, false otherwise
	 */
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

	public static boolean isBlockWater(World world, int spongeX, int spongeY, int spongeZ) {
		Block block = world.getBlockAt(spongeX, spongeY, spongeZ);
		int id = block.getTypeId();
		return id == 8 || id == 9;
	}
}
