package p.commandblocker;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import p.commandblocker.commands.CommandBlockerCMD;
import p.commandblocker.listeners.PlayerCommandPreprocessListener;
import p.commandblocker.listeners.PlayerCommandSendListener;
import p.commandblocker.utils.ChatUtils;
import p.commandblocker.utils.VersionUtils;

import java.util.Objects;

public final class CommandBlocker extends JavaPlugin {
    private BukkitAudiences adventure;
    private SettingsManager settingsManager;

    @Override
    public void onEnable() {

        this.adventure = BukkitAudiences.create(this);
        ChatUtils.setAdventure(adventure);

        settingsManager = new SettingsManager(this);

        Objects.requireNonNull(getCommand("commandblocker")).setExecutor(new CommandBlockerCMD(this));
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(this), this);

        if(VersionUtils.getServerVersion() >= 13) {
            getServer().getPluginManager().registerEvents(new PlayerCommandSendListener(this), this);
        }
    }

    @Override
    public void onDisable() {
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    public BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("¡Intenté acceder a Adventure cuando el complemento estaba deshabilitado!");
        }
        return this.adventure;
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }
}