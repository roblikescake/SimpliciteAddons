package net.simplicite_mc.roblikescake.simpliciteaddons.api.PlayerUUID;

public interface Identifier {

	/**
	 * Gets the player's Mojang Account UUID
	 *
	 * @param playerName Name of the player
	 * @return String UUID of the given player
	 */
	String getPlayerUUID(String playerName);
}
