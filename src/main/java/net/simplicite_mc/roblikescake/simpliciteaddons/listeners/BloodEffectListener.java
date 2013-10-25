package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BloodEffectListener implements Listener {
    public SimpliciteAddons pl;
    public BloodEffectListener(SimpliciteAddons pl) {
        this.pl = pl;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof LivingEntity && e.getDamager() instanceof LivingEntity) {
            LivingEntity entityDamaged = (LivingEntity) e.getEntity();
            LivingEntity entityDamagedBy = (LivingEntity) e.getDamager();

            if(entityDamaged instanceof Player || entityDamagedBy instanceof Player) {
                Location baseloc = entityDamaged.getLocation();
                Location eyeLoc = entityDamaged.getEyeLocation();

                baseloc.getWorld().playEffect(baseloc, Effect.STEP_SOUND, Material.NETHER_WARTS);
                eyeLoc.getWorld().playEffect(eyeLoc, Effect.STEP_SOUND, Material.NETHER_WARTS);
            }
        } else {
            return;
        }
    }
}