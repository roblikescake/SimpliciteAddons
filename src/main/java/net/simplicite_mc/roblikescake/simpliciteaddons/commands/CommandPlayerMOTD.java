package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPlayerMOTD implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println(MessageManager.getConsoleOnlyMessage());
			return false;
		}

		Player player = (Player) sender;
		final String playerName = player.getName();

		player.sendMessage(MessageManager.getPlayerMOTDMessage(playerName));
		return true;
	}
}
