package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

// Import SimpliciteAddons Classes

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;

// Import Bukkit Classes
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBed implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		// Was the sender a player? If not, send a no console message.
		if (!(sender instanceof Player)) {
			System.out.println(MessageManager.getNoConsoleMessage());
			return false;
		}

		// Now that we know it's not Console, cast the sender as a player.
		Player player = (Player) sender;

		// Did the player give any arguments? If so, tell them the correct command usage.
		if (args.length != 0) {
			player.sendMessage(MessageManager.getCommandBedUsageMessage());
			return true;
		}

		// Great, they have the correct arguments, define the player's bed location.
		Location locationBed = player.getBedSpawnLocation();

		// If the bed location is null (Bed never placed, or broken), tell the player he cannot use the command.
		if (locationBed == null) {
			player.sendMessage(MessageManager.getBedLocationNullMessage());
			return true;
		}

		// Everything is perfect, teleport the player to the bed location.
		player.teleport(locationBed);
		return true;
	}
}
