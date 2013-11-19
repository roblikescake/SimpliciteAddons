/*
*  Credits to: forairan
*  https://github.com/forairan/ConvertUUID
*
*  Credits to: EDawg878
*  https://github.com/EDawg878/IdentifierAPI
*/

package net.simplicite_mc.roblikescake.simpliciteaddons.api.PlayerUUID;

public class IdentifierAPI implements Identifier {

	@Override
	public String getPlayerUUID(String playerName) {
		ConversionJob job = new ConversionJob(playerName);
		job.run();
		return job.getUUID();
	}
}
