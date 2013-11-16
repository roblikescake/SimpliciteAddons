package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.Misc;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
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
}
