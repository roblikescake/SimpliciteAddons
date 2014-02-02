package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPlayerMOTD implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			System.out.println(MessageManager.getNoConsoleMessage());
			return false;
		}

		Player player = (Player) sender;

		if (args.length != 0) {
			player.sendMessage(MessageManager.getCommandPlayerMOTDUsageMessage());
			return true;
		}

		player.sendMessage(MessageManager.getPlayerMOTDMessage(player));
		return true;
	}
}
