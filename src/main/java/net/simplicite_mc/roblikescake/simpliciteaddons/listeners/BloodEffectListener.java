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
        LivingEntity damagee = (LivingEntity)e.getEntity();
        if(e.getDamager() instanceof Player || e.getEntity() instanceof Player) {
            Location eyeLoc = damagee.getEyeLocation();
            Location loc = damagee.getLocation();
            eyeLoc.getWorld().playEffect(eyeLoc, Effect.STEP_SOUND, Material.REDSTONE_WIRE);
            loc.getWorld().playEffect(loc, Effect.STEP_SOUND, Material.REDSTONE_WIRE);
        }
    }
}
