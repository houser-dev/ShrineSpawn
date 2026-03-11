package me.houserlab.shrineSpawn;

import me.houserlab.shrineSpawn.commands.ShrineCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.mvplugins.multiverse.core.MultiverseCoreApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ShrineSpawn extends JavaPlugin {

    private static ShrineSpawn instance;
    private MultiverseCoreApi mv;

    public static ShrineSpawn getInstance() {
        return instance;
    }

    public void debug(String message) {
        if (getConfig().getBoolean("debug")) {
            getLogger().info("[DEBUG] " + message);
        }
    }

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("ShrineSpawn starting...");

        try (InputStream in = getResource("build.properties")) {
            if (in != null) {
                Properties props = new Properties();
                props.load(in);
                getLogger().info("Build time: " + props.getProperty("build.time", "unknown"));
            }
        } catch (IOException e) {
            getLogger().warning("Could not read build.properties: " + e.getMessage());
        }

        saveDefaultConfig();

        getLogger().info("Hooking Multiverse-Core API");
        mv = MultiverseCoreApi.get();
        if (mv == null) {
            getLogger().severe("Failed to hook Multiverse-Core API! Disabling ShrineSpawn.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info("MultiverseCoreAPI Hooked successfully.");

        getLogger().info("Registering shrine command");
        var shrineCmd = getCommand("shrine");
        if (shrineCmd != null) {
            shrineCmd.setExecutor(new ShrineCommand());
        } else {
            getLogger().severe("Could not register 'shrine' command — is it declared in plugin.yml?");
        }

        getLogger().info("ShrineSpawn Finished Loading");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
