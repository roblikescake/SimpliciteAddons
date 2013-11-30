package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

// Import SimpliciteAddons Classes

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;

// Import Bukkit Classes
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPlayerMOTD implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		// Was the sender a player? If not, send a no console message.
		if (!(sender instanceof Player)) {
			System.out.println(MessageManager.getNoConsoleMessage());
			return false;
		}

		// Now that we know it's not Console, cast the sender as a player.
		Player player = (Player) sender;
		// Now we need to grab the player's name to give the proper parameter to the getPlayerMOTDMessage method.
		final String playerName = player.getName();

		// Did the player give any arguments? If so, tell them the correct command usage.
		if (args.length != 0) {
			player.sendMessage(MessageManager.getCommandPlayerMOTDUsageMessage());
			return true;
		}

		// Everything is perfect, send the player the PlayerMOTD.
		player.sendMessage(MessageManager.getPlayerMOTDMessage(playerName));
		return true;
	}
}
