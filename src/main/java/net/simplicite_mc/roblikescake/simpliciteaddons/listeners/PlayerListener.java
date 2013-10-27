package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import net.simplicite_mc.roblikescake.simpliciteaddons.datatypes.HeadData;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerListener implements Listener {
    public SimpliciteAddons pl;
    public PlayerListener(SimpliciteAddons pl) {
        this.pl = pl;
    }

    /** Join Message */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(ChatColor.BLACK + "[" + ChatColor.GREEN + "+" + ChatColor.BLACK + "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " is now" + ChatColor.GREEN + " Online" + ChatColor.GRAY + "!");
    }

    /** Quit Message */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.BLACK + "[" + ChatColor.RED + "-" + ChatColor.BLACK + "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " is now" + ChatColor.RED + " Offline" + ChatColor.GRAY + "!");
    }
    /** Kick Message */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        e.setLeaveMessage(ChatColor.BLACK + "[" + ChatColor.RED + "-" + ChatColor.BLACK + "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " is now" + ChatColor.RED + " Offline" + ChatColor.GRAY + "!");
    }

    /** When a player uses the Animal Catcher */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        String plPrefix = ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Egg" + ChatColor.BLACK + "]";
        Player player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        event.getPlayer().sendMessage("Interacted with an entity!");
        if (!itemStack.isSimilar(ItemManager.getAnimalCatcher())) {
            event.getPlayer().sendMessage("its not an animalcatcher, fuck da code!");
            return;
        }

        Entity entity = event.getRightClicked();
        Location location = entity.getLocation();
        EntityType entityType = entity.getType();

        if(isCatchable(entityType)) {
            event.getPlayer().sendMessage("its a catchable entity, to da code!");
            location.getWorld().dropItemNaturally(location, ItemManager.getAnimalSpawnEgg(entityType));
            event.getPlayer().sendMessage("egg dropped..");
            player.launchProjectile(Egg.class);
            entity.remove();
            location.getWorld().playEffect(location, Effect.SMOKE, 4);
            player.sendMessage(plPrefix + ChatColor.GREEN + " You caught a " + ChatColor.BLUE + entityType.name() + ChatColor.GREEN + "!");
        }
    }

    /** Set Entity to Baby if Ageable and Spawned By SpawnEgg */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {
            return;
        }

        if (event.getEntity() instanceof Ageable) {
            ((Ageable) event.getEntity()).setBaby();
        }
    }

    /** Check if I Want Entity Catchable */
    public boolean isCatchable(EntityType entityType) {
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

    /** Re-Apply Head Data on Pickup */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onHeadPickup(PlayerPickupItemEvent event) {
        if (event.getItem().getItemStack().getType().equals(Material.SKULL_ITEM)) {
            event.getPlayer().sendMessage("picked up skull");
            ItemStack skull = event.getItem().getItemStack();
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            for (HeadData h : ItemManager.headData.values()) {
                if (h.getOwner().equals(skullMeta.getOwner())) {
                    skullMeta.setDisplayName(h.getDisplayName());
                    skull.setItemMeta(skullMeta);
                }
            }
            event.getPlayer().sendMessage("re-applied skull data");
        }
    }
}