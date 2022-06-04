import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CooldownManager {
	private static HashMap<UUID, HashMap<String, Long>> cooldowns = new HashMap<>();

	public static boolean isOnCooldown(UUID uuid, String id) {
		return (System.currentTimeMillis() < getUserCooldown(uuid, id));
	}

	public static int getUserCooldownSeconds(UUID uuid, String id) {
		return (int) ((getUserCooldown(uuid, id) - System.currentTimeMillis() + 1000) / 1000);
	}

	public static long getUserCooldown(UUID uuid, String id) {
		HashMap<String, Long> userCooldowns = cooldowns.get(uuid);
		if (userCooldowns == null)
			return 0L;
		if (userCooldowns.containsKey(id)) {
			long cooldown = ((Long) userCooldowns.get(id)).longValue();
			if (cooldown <= 0L) {
				userCooldowns.remove(id);
				return 0L;
			}
			return cooldown;
		}
		return 0L;
	}

	public static void setUserCooldown(UUID player, String id, long cooldown) {
		long cooldownEnd = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(cooldown);
		HashMap<String, Long> userCooldowns = cooldowns.get(player);
		if (userCooldowns == null) {
			HashMap<String, Long> newCooldowns = new HashMap<>();
			cooldowns.put(player, newCooldowns);
			userCooldowns = newCooldowns;
		}
		userCooldowns.put(id, Long.valueOf(cooldownEnd));
	}
}
