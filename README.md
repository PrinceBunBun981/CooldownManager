# CooldownManager
Just a simple cooldown manager for Spigot, wasn't sure where I found it but hey, it worked and I'll continue to use it.

When using this, the `id` can be custom per cooldown allowing you to have multiple at once.
#
```java
isOnCooldown(UUID uuid, String id)
```
Returns a boolean whether the player is on cooldown or not.
#
```java
getUserCooldownSeconds(UUID uuid, String id)
```
Returns the amount of seconds as an integer for how long the player's cooldown has left.
#
```java
getUserCooldown(UUID uuid, String id)
```
Returns the player's cooldown as a long.
#
```java
setUserCooldown(UUID player, String id, long cooldown)
```
Sets the player's cooldown including the `id` used for it.
