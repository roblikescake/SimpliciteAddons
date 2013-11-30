package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

// Import SimpliciteAddons Classes

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;

// Import Bukkit Classes
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGetSponge implements CommandExecutor {
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
			player.sendMessage(MessageManager.getCommandGetSpongeUsageMessage());
			return true;
		}

		// Everything is perfect, give the player a sponge.
		player.getInventory().addItem(new ItemStack(Material.SPONGE, 1));
		return true;
	}
}
