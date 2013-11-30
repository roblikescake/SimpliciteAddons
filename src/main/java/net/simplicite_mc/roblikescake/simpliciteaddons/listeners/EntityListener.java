package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

// Import SimpliciteAddons Classes

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.HeadManager;

// Import Bukkit Classes
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityListener implements Listener {

	// Check EntityDamageByEntityEvent for the purpose of displaying a "blood" effect.
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

		if (!(entityDamaged instanceof Player || entityDamagedBy instanceof Player)) {
			return;
		}

		Location baseLocation = entityDamaged.getLocation();
		Location eyeLocation = entityDamaged.getEyeLocation();

		baseLocation.getWorld().playEffect(baseLocation, Effect.STEP_SOUND, Material.NETHER_WARTS);
		eyeLocation.getWorld().playEffect(eyeLocation, Effect.STEP_SOUND, Material.NETHER_WARTS);
	}

	// Check EntityDeathEvent for the purpose of dropping a mob/player head.
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity().getKiller() == null) {
			return;
		}

		Entity entity = event.getEntity();
		Player killer = event.getEntity().getKiller();

		HeadManager.dropHeads(entity, killer);
	}

	// Check EntityExplodeEvent for the purpose of preventing Creeper/Wither/EnderDragon block damage.
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityExplode(EntityExplodeEvent event) {
		EntityType entityType = event.getEntityType();

		if (!((entityType == EntityType.CREEPER) || (entityType == EntityType.ENDER_DRAGON) || (entityType == EntityType.WITHER))) {
			return;
		}

		event.setCancelled(true);
	}
}
