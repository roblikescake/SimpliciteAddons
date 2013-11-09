package net.simplicite_mc.roblikescake.simpliciteaddons.utilities;

import org.bukkit.ChatColor;

public class MessageManager {

    public static String getPlayerJoinMessage(String playerName) {
        return ChatColor.BLACK + "[" + ChatColor.GREEN + "+" + ChatColor.BLACK + "] " + ChatColor.AQUA + playerName + ChatColor.GRAY + " is now" + ChatColor.GREEN + " Online" + ChatColor.GRAY + "!";
    }

    public static String getPlayerJoinConsoleMessage(String playerName) {
        return "[+] " + playerName + " joined!";
    }

    public static String getPlayerQuitMessage(String playerName) {
        return ChatColor.BLACK + "[" + ChatColor.RED + "-" + ChatColor.BLACK + "] " + ChatColor.AQUA + playerName + ChatColor.GRAY + " is now" + ChatColor.RED + " Offline" + ChatColor.GRAY + "!";
    }

    public static String getPlayerQuitConsoleMessage(String playerName) {
        return "[-] " + playerName + " left!";
    }

    public static String getAnimalCaughtMessage(String entityName) {
        return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Egg" + ChatColor.BLACK + "] " + ChatColor.GREEN + "You caught a " + ChatColor.BLUE + entityName + ChatColor.GREEN + "!";
    }

    public static String getPlayerBeheadedMessage(String playerName, String killerName) {
        return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Heads" + ChatColor.BLACK + "] " + ChatColor.BLUE + playerName + ChatColor.GREEN + " was beheaded by " + ChatColor.BLUE + killerName + ChatColor.GREEN + "!";
    }

    public static String getMobHeadDroppedMessage(String mobHeadDisplayName) {
        return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Heads" + ChatColor.BLACK + "] " + ChatColor.GREEN + "A " + ChatColor.BLUE + mobHeadDisplayName + ChatColor.GREEN + " dropped!";
    }

    public static String getConsoleOnlyMessage() {
        return "Console cannot execute this command!";
    }

    public static String getBedLocationNullMessage() {
        return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Bed" + ChatColor.BLACK + "] " + ChatColor.DARK_RED + "You haven't slept in a bed yet!";
    }

    public static String getCommandBedUsageMessage() {
        return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Bed" + ChatColor.BLACK + "] " + ChatColor.RED + "Correct usage is: " + ChatColor.GREEN + "/bed";
    }

    public static String getCommandSpawnUsageMessage() {
        return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Spawn" + ChatColor.BLACK + "] " + ChatColor.RED + "Correct usage is: " + ChatColor.GREEN + "/spawn";
    }

    public static String getCommandSetSpawnUsageMessage() {
        return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Spawn" + ChatColor.BLACK + "] " + ChatColor.RED + "Correct usage is: " + ChatColor.GREEN + "/spawn";
    }
}
