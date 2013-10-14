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
    public BloodEffectListener(SimpliciteAddons i) {
        pl = i;
    }


    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        LivingEntity entityDamaged = (LivingEntity) e.getEntity();
        LivingEntity entityDamagedBy = (LivingEntity) e.getDamager();

        if(e.getEntity() instanceof LivingEntity && e.getDamager() instanceof LivingEntity) {
            if(entityDamaged instanceof Player || entityDamagedBy instanceof Player) {
                Location eyeLoc = entityDamaged.getEyeLocation();
                Location baseLoc = entityDamaged.getLocation();
                eyeLoc.getWorld().playEffect(eyeLoc, Effect.STEP_SOUND, Material.REDSTONE_WIRE);
                baseLoc.getWorld().playEffect(baseLoc, Effect.STEP_SOUND, Material.REDSTONE_WIRE);
            }
        } else {
            return;
        }
    }
}
