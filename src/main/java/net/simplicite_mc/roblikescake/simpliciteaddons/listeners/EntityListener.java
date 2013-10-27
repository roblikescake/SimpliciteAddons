package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.HeadManager;

public class EntityListener implements Listener {

    /**
     * Create Blood Effect on Damaged Entities
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof LivingEntity && event.getDamager() instanceof LivingEntity) {
            LivingEntity entityDamaged = (LivingEntity) event.getEntity();
            LivingEntity entityDamagedBy = (LivingEntity) event.getDamager();

            if (entityDamaged instanceof Player || entityDamagedBy instanceof Player) {
                Location baseLocation = entityDamaged.getLocation();
                Location eyeLocation = entityDamaged.getEyeLocation();

                baseLocation.getWorld().playEffect(baseLocation, Effect.STEP_SOUND, Material.NETHER_WARTS);
                eyeLocation.getWorld().playEffect(eyeLocation, Effect.STEP_SOUND, Material.NETHER_WARTS);
            }
        }
    }

    /**
     * Check EntityDeathEvents.
     * <p/>
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
        Player killer = event.getEntity().getKiller();

        HeadManager.dropHeads(entity, killer);
    }
}