package net.simplicite_mc.roblikescake.simpliciteaddons.api.PlayerUUID;

import org.bukkit.plugin.java.JavaPlugin;

public class IdentifierAPI extends JavaPlugin implements Identifier {

	@Override
	public String getPlayerUUID(String playerName) {
		ConversionJob job = new ConversionJob(playerName);
		job.run();
		return job.getUUID();
	}
}
