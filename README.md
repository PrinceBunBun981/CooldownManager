# CooldownManager
Just a simple cooldown manager for Spigot, wasn't sure where I found it but hey, it worked and I'll continue to use it.

When using this, the `id` can be custom per cooldown allowing you to have multiple at once.
#
```java
public static boolean isOnCooldown(UUID uuid, String id) {
	return (System.currentTimeMillis() < getUserCooldown(uuid, id));
}
```
Returns a boolean whether the player is on cooldown or not.
#
```java
public static int getUserCooldownSeconds(UUID uuid, String id) {
	return (int) ((getUserCooldown(uuid, id) - System.currentTimeMillis() + 1000) / 1000);
}
```
Returns the amount of seconds as an integer for how long the player's cooldown has left.
#
```java
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
```
Returns the player's cooldown as a long.
#
```java
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
```
Sets the player's cooldown including the `id` used for it.
