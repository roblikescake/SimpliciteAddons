package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

// Import SimpliciteAddons Classes

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;

// Import Bukkit Classes
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListener implements Listener {

	// Check ServerListPingEvent for the purpose of auto-updating the ServerMOTD.
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onServerPing(ServerListPingEvent event) {
		event.setMotd(MessageManager.getServerMOTDMessage());
	}
}
