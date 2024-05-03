package p.commandblocker.utils;

import org.bukkit.Bukkit;

public class VersionUtils {

    public static int getServerVersion() {
        String version = Bukkit.getServer().getBukkitVersion().split("-")[0];
        return Integer.parseInt(version.split("\\.")[1]);
    }
}