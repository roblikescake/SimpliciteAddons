package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String oldChatMessage = event.getMessage();

		String newChatMessage = ChatColor.DARK_GREEN + oldChatMessage;

		for (Player recipients : event.getRecipients()) {
			recipients.sendRawMessage(newChatMessage);
		}
	}
}
