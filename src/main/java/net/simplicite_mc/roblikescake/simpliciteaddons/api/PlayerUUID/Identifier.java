/*
*  Credits to: forairan
*  https://github.com/forairan/ConvertUUID
*
*  Credits to: EDawg878
*  https://github.com/EDawg878/IdentifierAPI
*/

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
