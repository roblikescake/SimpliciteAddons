/*
*  Credits to: forairan
*  https://github.com/forairan/ConvertUUID
*
*  Credits to: EDawg878
*  https://github.com/EDawg878/IdentifierAPI
*/

package net.simplicite_mc.roblikescake.simpliciteaddons.api.PlayerUUID;

import java.util.concurrent.atomic.AtomicBoolean;

import com.mojang.api.profiles.HttpProfileRepository;
import com.mojang.api.profiles.Profile;
import com.mojang.api.profiles.ProfileCriteria;

class ConversionJob implements Runnable {

	private static final HttpProfileRepository repository = new HttpProfileRepository();
	private static final String AGENT = "minecraft";
	private final String username;
	private String uuid;
	private final AtomicBoolean complete = new AtomicBoolean(false);

	public ConversionJob(String username) {
		this.username = username;
	}

	public void run() {
		Profile[] profiles = repository.findProfilesByCriteria(new ProfileCriteria(username, AGENT));
		if (profiles.length == 1) {
			uuid = profiles[0].getId();
		} else {
			uuid = "";
		}

		complete.set(true);
		profiles = null;
	}

	public String getUsername() {
		return username;
	}

	public String getUUID() {
		return uuid;
	}

	public boolean isComplete() {
		return complete.get();
	}
}
