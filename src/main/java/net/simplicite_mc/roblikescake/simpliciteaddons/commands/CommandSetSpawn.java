package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

// Import SimpliciteAddons Classes

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;

// Import Bukkit Classes
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetSpawn implements CommandExecutor {
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
			player.sendMessage(MessageManager.getCommandSetSpawnUsageMessage());
			return true;
		}

		// Is the player an OP? If not, don't allow them to use the command. **Will change when Permissions are added**
		if (!(player.isOp())) {
			return true;
		}

		// Now we need to grab the X, Y, and Z coordinates of the player.
		int locationPlayerX = (int) player.getLocation().getX();
		int locationPlayerY = (int) player.getLocation().getY();
		int locationPlayerZ = (int) player.getLocation().getZ();

		// Everything is perfect, set the players location as the spawn.
		SimpliciteAddons.p.getServer().getWorlds().get(0).setSpawnLocation(locationPlayerX, locationPlayerY, locationPlayerZ);
		return true;
	}
}
