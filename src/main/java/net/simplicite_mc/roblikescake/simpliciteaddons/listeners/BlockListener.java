package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.Misc;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event) {
		Block blockPlaced = event.getBlockPlaced();

		if (!(blockPlaced.getType() == Material.SPONGE)) {
			return;
		}

		int spongeX = blockPlaced.getX();
		int spongeY = blockPlaced.getY();
		int spongeZ = blockPlaced.getZ();
		int spongeClearRadius = 2;
		World world = blockPlaced.getWorld();

		Misc.clearSpongeWater(world, spongeX, spongeY, spongeZ, spongeClearRadius);
	}

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onWaterUpdate(BlockFromToEvent event) {
		Block blockFrom = event.getBlock();
		Block blockTo = event.getToBlock();

		if (!((blockFrom.getType() == Material.WATER) && (blockTo.getType() == Material.AIR))) {
			return;
		}

		int blockX = blockFrom.getX();
		int blockY = blockFrom.getY();
		int blockZ = blockFrom.getZ();
		int radius = 2;
		World world = blockFrom.getWorld();

		for (int radiusX = -radius; radiusX <= radius; radiusX++) {
			for (int radiusY = -radius; radiusY <= radius; radiusY++) {
				for (int radiusZ = -radius; radiusZ <= radius; radiusZ++) {
					Block block = world.getBlockAt(blockX + radiusX, blockY + radiusY, blockZ + radiusZ);
					if (block.getType() == Material.SPONGE) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
