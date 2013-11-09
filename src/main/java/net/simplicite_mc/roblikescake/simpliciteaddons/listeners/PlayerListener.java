package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Egg;
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

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.HeadManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.ItemManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.Misc;

public class PlayerListener implements Listener {

    /**
     * Check PlayerJoinEvents.
     * <p/>
     * These events are checked for the purpose of setting
     * the join messages for players.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();

        event.setJoinMessage(MessageManager.getPlayerJoinMessage(playerName));
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
}
