package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetSpawn implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println(MessageManager.getConsoleOnlyMessage());
			return false;
		}

		Player player = (Player) sender;

		if (!(args.length == 0)) {
			player.sendMessage(MessageManager.getCommandSetSpawnUsageMessage());
			return true;
		}

		if (!(player.isOp())) {
			return true;
		}

		int locationPlayerX = (int) player.getLocation().getX();
		int locationPlayerY = (int) player.getLocation().getY();
		int locationPlayerZ = (int) player.getLocation().getZ();

		SimpliciteAddons.p.getServer().getWorlds().get(0).setSpawnLocation(locationPlayerX, locationPlayerY, locationPlayerZ);
		return true;
	}
}
