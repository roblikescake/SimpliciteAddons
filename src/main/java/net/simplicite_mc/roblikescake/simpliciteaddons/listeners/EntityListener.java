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
     * Check EntityDamageEvents.
     * <p/>
     * These events are checked for the purpose of applying
     * a "blood effect" on a mob or player when attacked.
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        if (!(event.getDamager() instanceof LivingEntity)) {
            return;
        }

        LivingEntity entityDamaged = (LivingEntity) event.getEntity();
        LivingEntity entityDamagedBy = (LivingEntity) event.getDamager();

        if (!(entityDamaged instanceof Player || entityDamagedBy instanceof  Player)) {
            return;
        }

        Location baseLocation = entityDamaged.getLocation();
        Location eyeLocation = entityDamaged.getEyeLocation();

        baseLocation.getWorld().playEffect(baseLocation, Effect.STEP_SOUND, Material.NETHER_WARTS);
        eyeLocation.getWorld().playEffect(eyeLocation, Effect.STEP_SOUND, Material.NETHER_WARTS);
    }

    /**
     * Check EntityDeathEvents.
     * <p/>
     * These events are checked for the purpose of dropping
     * mob or player heads after they are killed.
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
