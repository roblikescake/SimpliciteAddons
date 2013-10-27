package net.simplicite_mc.roblikescake.simpliciteaddons.utilities;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import net.simplicite_mc.roblikescake.simpliciteaddons.datatypes.HeadData;

public class HeadManager {

    public static void dropHeads(Entity entity, Player killer) {
        EntityType entityType = entity.getType();
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
                SimpliciteAddons.p.getServer().broadcastMessage(plPrefix + ChatColor.BLUE + playerName + ChatColor.GREEN + " was beheaded by " + ChatColor.BLUE + killer.getName() + ChatColor.GREEN + "!");
            }
        }
        else {
            if (diceRoll <= (headData.getDropChance() + lootBonus)) {
                entityLocation.getWorld().dropItemNaturally(entityLocation, ItemManager.getMobHead(entityType));
                killer.sendMessage(plPrefix + ChatColor.GREEN + "A " + ChatColor.BLUE + headData.getDisplayName() + ChatColor.GREEN + " dropped!");
            }
        }
    }
}
