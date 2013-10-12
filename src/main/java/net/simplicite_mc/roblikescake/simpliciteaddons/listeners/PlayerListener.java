package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerListener implements Listener {
    public SimpliciteAddons pl;
    public PlayerListener(SimpliciteAddons i) {
        pl = i;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(ChatColor.BLACK + "[" + ChatColor.GREEN + "+" + ChatColor.BLACK + "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " is now" + ChatColor.GREEN + " Online" + ChatColor.GRAY + "!");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.BLACK + "[" + ChatColor.RED + "-" + ChatColor.BLACK + "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " is now" + ChatColor.RED + " Offline" + ChatColor.GRAY + "!");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        e.setLeaveMessage(ChatColor.BLACK + "[" + ChatColor.RED + "-" + ChatColor.BLACK + "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " is now" + ChatColor.RED + " Offline" + ChatColor.GRAY + "!");
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerUseAnimalCatcher(PlayerInteractEntityEvent e) {
        String plPrefix = ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Egg" + ChatColor.BLACK + "]";
        Player p = e.getPlayer();
        ItemStack itemStack = p.getItemInHand();

        if (!itemStack.isSimilar(getAnimalCatcher())) {
            return;
        }

        Entity entity = e.getRightClicked();

        short dataValue = getEntityShort(entity.getType());

        if (dataValue == 0) {
            e.setCancelled(true);
            return;
        }

        Location loc = entity.getLocation();

        p.getWorld().dropItemNaturally(loc, new ItemStack(Material.MONSTER_EGG, 1, dataValue));
        p.launchProjectile(Egg.class);
        entity.remove();
        loc.getWorld().playEffect(loc, Effect.SMOKE, 4);
        p.sendMessage(plPrefix + ChatColor.GREEN + " You caught a " + ChatColor.BLUE + entity.getType().name() + ChatColor.GREEN + "!");
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerUseSpawnEgg(CreatureSpawnEvent e) {
        if (!e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)) {
            return;
        }

        EntityType entityType = e.getEntityType();
        Entity ent = e.getEntity();
        Ageable ageableEntity = (Ageable) ent;

        if (entityType == EntityType.PIG || entityType == EntityType.SHEEP || entityType == EntityType.COW || entityType == EntityType.CHICKEN ||
                entityType == EntityType.WOLF || entityType == EntityType.MUSHROOM_COW || entityType == EntityType.OCELOT ||
                entityType == EntityType.HORSE || entityType == EntityType.VILLAGER) {
            ageableEntity.setBaby();
        } else {
            return;
        }
    }

    public short getEntityShort(EntityType entityType) {
        switch (entityType) {
            case PIG:
                return 90;
            case SHEEP:
                return 91;
            case COW:
                return 92;
            case CHICKEN:
                return 93;
            case SQUID:
                return 94;
            case WOLF:
                return 95;
            case MUSHROOM_COW:
                return 96;
            case OCELOT:
                return 98;
            case HORSE:
                return 100;
            case VILLAGER:
                return 120;
            default:
                return 0;
        }
    }

    public ShapedRecipe getAnimalCatcherRecipe() {
        ShapedRecipe AnimalCatcher = new ShapedRecipe(getAnimalCatcher());
        AnimalCatcher.shape(" X ", "XXX", " X ");
        AnimalCatcher.setIngredient('X', Material.EGG);

        return AnimalCatcher;
    }

    public ItemStack getAnimalCatcher() {
        ItemStack itemStack = new ItemStack(Material.EGG);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "AnimalCatcher");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        LivingEntity damagee = (LivingEntity)e.getEntity();
        if(e.getDamager() instanceof Player || e.getEntity() instanceof Player) {
            Location eyeLoc = damagee.getEyeLocation();
            Location loc = damagee.getLocation();
            eyeLoc.getWorld().playEffect(eyeLoc, Effect.STEP_SOUND, Material.REDSTONE_WIRE);
            loc.getWorld().playEffect(loc, Effect.STEP_SOUND, Material.REDSTONE_WIRE);
        }
    }
}