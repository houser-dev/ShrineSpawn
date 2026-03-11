package me.houserlab.shrineSpawn;

import me.houserlab.shrineSpawn.commands.shrineCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.mvplugins.multiverse.core.MultiverseCoreApi;

public final class ShrineSpawn extends JavaPlugin {

    private static ShrineSpawn instance;
    private MultiverseCoreApi mv;

    public static ShrineSpawn getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
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

        instance = this;
        saveDefaultConfig();

        getLogger().info("Hooking Multiverse-Core API");
        mv = MultiverseCoreApi.get();
        getLogger().info("MultiverseCoreAPI Hooked successfully.");

        getLogger().info("Registering shrine command");
        getCommand("shrine").setExecutor(new shrineCommand());


        getLogger().info("ShrineSpawn Finished Loading");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
