package me.houserlab.shrineSpawn;

import org.bukkit.plugin.java.JavaPlugin;
import org.mvplugins.multiverse.core.MultiverseCoreApi;

public final class ShrineSpawn extends JavaPlugin {

    private MultiverseCoreApi mv;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("ShrineSpawn starting...");
        getLogger().info("Hooking Multiverse-Core API");
        mv = MultiverseCoreApi.get();
        getLogger().info("MultiverseCoreAPI Hooked successfully.");


        getLogger().info("ShrineSpawn Finished Loading");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
