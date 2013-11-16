package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandSponge implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println(MessageManager.getConsoleOnlyMessage());
			return false;
		}

		Player player = (Player) sender;

		if (!(args.length == 0)) {
			player.sendMessage(MessageManager.getCommandGetSpongeUsageMessage());
			return true;
		}

		player.getInventory().addItem(new ItemStack(Material.SPONGE, 1));
		return true;
	}
}
