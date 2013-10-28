package net.simplicite_mc.roblikescake.simpliciteaddons.utilities;

import org.bukkit.ChatColor;

public class MessageManager {

    public static String getJoinMessage(String playerName) {
        String joinMessage = ChatColor.BLACK + "[" + ChatColor.GREEN + "+" + ChatColor.BLACK + "] " + ChatColor.AQUA + playerName + ChatColor.GRAY + " is now" + ChatColor.GREEN + " Online" + ChatColor.GRAY + "!";
        return joinMessage;
    }

    public static String getQuitMessage(String playerName) {
        String quitMessage = ChatColor.BLACK + "[" + ChatColor.RED + "-" + ChatColor.BLACK + "] " + ChatColor.AQUA + playerName + ChatColor.GRAY + " is now" + ChatColor.RED + " Offline" + ChatColor.GRAY + "!";
        return quitMessage;
    }

    public static String getKickMessage(String playerName) {
        String kickMessage = ChatColor.BLACK + "[" + ChatColor.RED + "-" + ChatColor.BLACK + "] " + ChatColor.AQUA + playerName + ChatColor.GRAY + " is now" + ChatColor.RED + " Offline" + ChatColor.GRAY + "!";
        return kickMessage;
    }

    public static String getAnimalCaughtMessage(String entityName) {
        String animalCaughtMessage = ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Egg" + ChatColor.BLACK + "] " + ChatColor.GREEN + "You caught a " + ChatColor.BLUE + entityName + ChatColor.GREEN + "!";
        return animalCaughtMessage;
    }

    public static String getPlayerBeheadedMessage(String playerName, String killerName) {
        String playerBehadedMessage = ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Heads" + ChatColor.BLACK + "] " + ChatColor.BLUE + playerName + ChatColor.GREEN + " was beheaded by " + ChatColor.BLUE + killerName + ChatColor.GREEN + "!";
        return playerBehadedMessage;
    }

    public static String getMobHeadDroppedMessage(String mobHeadDisplayName) {
        String mobHeadDroppedMessage = ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Heads" + ChatColor.BLACK + "] " + ChatColor.GREEN + "A " + ChatColor.BLUE + mobHeadDisplayName + ChatColor.GREEN + " dropped!";
        return mobHeadDroppedMessage;
    }
}