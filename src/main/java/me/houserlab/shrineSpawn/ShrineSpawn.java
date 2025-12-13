package me.houserlab.shrineSpawn;

import me.houserlab.shrineSpawn.commands.shrineCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.mvplugins.multiverse.core.MultiverseCoreApi;


import java.util.Objects;

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
        instance = this;
        getLogger().info("Hooking Multiverse-Core API");
        mv = MultiverseCoreApi.get();
        getLogger().info("MultiverseCoreAPI Hooked successfully.");

        getLogger().info("Registering shrine command");
        Objects.requireNonNull(getCommand("shrine")).setExecutor(new shrineCommand());


        getLogger().info("ShrineSpawn Finished Loading");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
