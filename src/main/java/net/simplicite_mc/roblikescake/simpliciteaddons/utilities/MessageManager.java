package net.simplicite_mc.roblikescake.simpliciteaddons.utilities;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MessageManager {

	public static String getPluginVersionNumber() {
		return SimpliciteAddons.p.getDescription().getVersion();
	}

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

	public static String getNoConsoleMessage() {
		return "Console cannot execute this command!";
	}

	public static String getBedLocationNullMessage() {
		return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Bed" + ChatColor.BLACK + "] " + ChatColor.DARK_RED + "You haven't slept in a bed yet or your bed was broken!";
	}

	public static String getCommandBedUsageMessage() {
		return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Bed" + ChatColor.BLACK + "] " + ChatColor.RED + "Correct usage is: " + ChatColor.GREEN + "/bed";
	}

	public static String getCommandPlayerMOTDUsageMessage() {
		return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "MOTD" + ChatColor.BLACK + "] " + ChatColor.RED + "Correct usage is: " + ChatColor.GREEN + "/motd";
	}

	public static String getCommandSpawnUsageMessage() {
		return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Spawn" + ChatColor.BLACK + "] " + ChatColor.RED + "Correct usage is: " + ChatColor.GREEN + "/spawn";
	}

	public static String getCommandSetSpawnUsageMessage() {
		return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Spawn" + ChatColor.BLACK + "] " + ChatColor.RED + "Correct usage is: " + ChatColor.GREEN + "/setspawn";
	}

	public static String getCommandGetSpongeUsageMessage() {
		return ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Sponge" + ChatColor.BLACK + "] " + ChatColor.RED + "Correct usage is: " + ChatColor.GREEN + "/getsponge";
	}

	public static String[] getPlayerMOTDMessage(Player player) {
		UUID playerUUID = player.getUniqueId();
		return new String[]{
				ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + " -=-=-=-=-=-=-=-=-=-=-=-=-=-=-",
				ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "" + ChatColor.BOLD + "                 Simplicite" + ChatColor.BLUE + "" + ChatColor.BOLD + "MC",
				ChatColor.AQUA + "                    Barebones " + ChatColor.GOLD + getPluginVersionNumber() + "\n",
				ChatColor.DARK_GRAY + "                    Your PlayerUUID:\n" + ChatColor.GRAY + playerUUID,
				ChatColor.DARK_GREEN + "                     Latest Changes:",
				ChatColor.YELLOW + "- Sponges now remove water! /getsponge",
				ChatColor.YELLOW + "- Can now login via simplicite-mc.net",
				ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-",
		};
	}

	public static String getServerMOTDMessage() {
		return ChatColor.DARK_AQUA + "Simplicite" + ChatColor.BLUE + "MC " + ChatColor.AQUA + "Barebones " + ChatColor.GOLD + getPluginVersionNumber();
	}
}
