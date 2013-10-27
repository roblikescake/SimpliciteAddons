package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import net.simplicite_mc.roblikescake.simpliciteaddons.datatypes.HeadData;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.ItemManager;

public class EntityListener implements Listener {
    public SimpliciteAddons pl;

    public EntityListener(SimpliciteAddons pl) {
        this.pl = pl;
    }

    /** Create Blood Effect on Damaged Entities */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof LivingEntity && event.getDamager() instanceof LivingEntity) {
            LivingEntity entityDamaged = (LivingEntity) event.getEntity();
            LivingEntity entityDamagedBy = (LivingEntity) event.getDamager();

            if(entityDamaged instanceof Player || entityDamagedBy instanceof Player) {
                Location baseLocation = entityDamaged.getLocation();
                Location eyeLocation = entityDamaged.getEyeLocation();

                baseLocation.getWorld().playEffect(baseLocation, Effect.STEP_SOUND, Material.NETHER_WARTS);
                eyeLocation.getWorld().playEffect(eyeLocation, Effect.STEP_SOUND, Material.NETHER_WARTS);
            }
        }
    }

    /**
     * Check EntityDeathEvents.
     * <p>
     * These events are checked for the purpose of dropping
     * mob or player heads after they are killed.
     *
     * @param event The event to check
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) {
            return;
        }

        Entity entity = event.getEntity();
        EntityType entityType = event.getEntityType();
        Player killer = event.getEntity().getKiller();

        HeadData headData = ItemManager.headData.get(entityType);

        if (headData == null) {
            return;
        }

        int lootBonus = 0;

        if (killer.getItemInHand() != null) {
            lootBonus = killer.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) * 2;
        }

        Location entityLocation = entity.getLocation();
        Random random = new Random();
        int diceRoll = random.nextInt(100);
        String plPrefix = ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Heads" + ChatColor.BLACK + "] ";

        if (entity instanceof Player) {
            if (diceRoll <= (10 + lootBonus)) {
                String playerName = ((Player) entity).getName();
                entityLocation.getWorld().dropItemNaturally(entityLocation, ItemManager.getPlayerHead(playerName));
                pl.getServer().broadcastMessage(plPrefix + ChatColor.BLUE + playerName + ChatColor.GREEN + " was beheaded by " + ChatColor.BLUE + killer.getName() + ChatColor.GREEN  + "!");
            }
        } else {
            if (diceRoll <= (ItemManager.headData.get(entityType).getDropChance() + lootBonus)) {
                entityLocation.getWorld().dropItemNaturally(entityLocation, ItemManager.getMobHead(entityType));
                killer.sendMessage(plPrefix + ChatColor.GREEN + "A " + ChatColor.BLUE + ItemManager.headData.get(entityType).getDisplayName() + ChatColor.GREEN + " dropped!");
            }
        }
    }
}