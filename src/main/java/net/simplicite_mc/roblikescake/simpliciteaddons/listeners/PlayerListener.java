package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.HeadManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.ItemManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.Misc;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

	// Check PlayerJoinEvent for the purpose of setting a custom PlayerJoinMessage and sending the PlayerMOTD.
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		player.sendRawMessage("{\"text\":\"\",\"extra\":[{\"text\":\"SwordPVP\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://www.swordpvp.com\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to go to website!\"}}]}");
		event.setJoinMessage(MessageManager.getPlayerJoinMessage(playerName));
		sendPlayerMOTD(player);
		System.out.println(MessageManager.getPlayerJoinConsoleMessage(playerName));
	}

	// Check PlayerQuitEvent for the purpose of setting a custom PlayerQuitMessage.
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event) {
		String playerName = event.getPlayer().getName();

		event.setQuitMessage(MessageManager.getPlayerQuitMessage(playerName));
		System.out.println(MessageManager.getPlayerQuitConsoleMessage(playerName));
	}

	// Check PlayerKickEvent for the purpose of setting a custom PlayerKickMessage.
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent event) {
		String playerName = event.getPlayer().getName();

		event.setLeaveMessage(MessageManager.getPlayerQuitMessage(playerName));
		System.out.println(MessageManager.getPlayerQuitConsoleMessage(playerName));
	}

	// Check PlayerInteractEntityEvent for the purpose of using the AnimalCatcher item.
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

		short entityShort = entityType.getTypeId();
		String entityName = entityType.name();
		Location location = entity.getLocation();

		location.getWorld().dropItemNaturally(location, ItemManager.getAnimalSpawnEgg(entityShort));
		entity.remove();
		location.getWorld().playEffect(location, Effect.SMOKE, 4);
		player.sendMessage(MessageManager.getAnimalCaughtMessage(entityName));
	}

	// Check CreatureSpawnEvent for the purpose of forcing Ageable Entities to spawn as babies if spawned via SpawnEgg.
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

	// Check PlayerPickupItemEvent for the purpose of re-applying head data to mob heads.
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		Item item = event.getItem();

		HeadManager.applyHeadData(item);
	}

	// Scheduler used for sending a player the PlayerMOTD.
	public void sendPlayerMOTD(final Player player) {
		SimpliciteAddons.p.getServer().getScheduler().runTaskLater(SimpliciteAddons.p, new Runnable() {
			@Override
			public void run() {
				player.sendMessage(MessageManager.getPlayerMOTDMessage(player));
			}
		}, 5L);
	}
}
