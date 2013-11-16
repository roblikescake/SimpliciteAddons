package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.HeadManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.ItemManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.Misc;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

	/**
	 * Check PlayerJoinEvents.
	 * <p/>
	 * These events are checked for the purpose of setting
	 * the join messages for players.
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();

		event.setJoinMessage(MessageManager.getPlayerJoinMessage(playerName));
		sendPlayerMOTD(player);
		System.out.println(MessageManager.getPlayerJoinConsoleMessage(playerName));
	}

	/**
	 * Check PlayerQuitEvents.
	 * <p/>
	 * These events are checked for the purpose of setting
	 * the quit messages for players.
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event) {
		String playerName = event.getPlayer().getName();

		event.setQuitMessage(MessageManager.getPlayerQuitMessage(playerName));
		System.out.println(MessageManager.getPlayerQuitConsoleMessage(playerName));
	}

	/**
	 * Check PlayerKickEvents.
	 * <p/>
	 * These events are checked for the purpose of setting
	 * the kick messages for players.
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent event) {
		String playerName = event.getPlayer().getName();

		event.setLeaveMessage(MessageManager.getPlayerQuitMessage(playerName));
		System.out.println(MessageManager.getPlayerQuitConsoleMessage(playerName));
	}

	/**
	 * Check PlayerInteractEntityEvents.
	 * <p/>
	 * These events are checked for the purpose of dropping
	 * a spawn egg when a player uses the AnimalCatcher item.
	 */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		ItemStack itemStack = player.getItemInHand();

		if (!itemStack.isSimilar(ItemManager.getAnimalCatcher())) {
			return;
		}

		Entity entity = event.getRightClicked();
		EntityType entityType = entity.getType();

		if (!Misc.isCatchable(entityType)) {
			return;
		}

		short entityShort = entity.getType().getTypeId();
		String entityName = entityType.name();
		Location location = entity.getLocation();

		location.getWorld().dropItemNaturally(location, ItemManager.getAnimalSpawnEgg(entityShort));
		player.launchProjectile(Egg.class);
		entity.remove();
		location.getWorld().playEffect(location, Effect.SMOKE, 4);
		player.sendMessage(MessageManager.getAnimalCaughtMessage(entityName));
	}

	/**
	 * Check CreatureSpawnEvents.
	 * <p/>
	 * These events are checked for the purpose of changing
	 * the entity spawned via SpawnEgg to a baby, if applicable.
	 */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if (!(event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)) {
			return;
		}

		if (!(event.getEntity() instanceof Ageable)) {
			return;
		}

		((Ageable) event.getEntity()).setBaby();
	}

	/**
	 * Checks PlayerPickupItemEvents.
	 * <p/>
	 * These events are checked for the purpose of re-applying
	 * the HeadData of a head, as it is lost on placing the Head.
	 */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		Item item = event.getItem();

		HeadManager.applyHeadData(item);
	}

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

		clearSpongeWater(world, spongeX, spongeY, spongeZ, spongeClearRadius);
	}

	public void sendPlayerMOTD(final Player player) {
		SimpliciteAddons.p.getServer().getScheduler().runTaskLater(SimpliciteAddons.p, new Runnable() {
			@Override
			public void run() {
				player.sendMessage(MessageManager.getPlayerMOTDMessage());
			}
		}, 5L);
	}

	public void clearSpongeWater(World world, int spongeX, int spongeY, int spongeZ, int spongeClearRadius) {
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
