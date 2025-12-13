package me.houserlab.shrineSpawn.commands;

import me.houserlab.shrineSpawn.ShrineSpawn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class shrineCommand implements CommandExecutor {

    private final Map<String, String> helpMessages = Map.of(
            "tp", "Teleport the player to the main shrine location.",
            "set", "Sets various locations, respawn point, shrine drop location, etc.",
            "remove", "Removes a previously set location.",
            "list", "Lists any current shrine locations"
    );

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        ShrineSpawn plugin = ShrineSpawn.getInstance();

        // General test command, will have debug features built in and use dialog menus.
        // only for players with shrinepawn.admin permission node.

        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be executed by players.");
            plugin.getLogger().warning(sender.getName() + " Just attempted to run the command: '" + label + "' But they are not a player!");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("Need arguments!");
            return false;
        }


        plugin.getLogger().info("Checking arguments for shrine command");
        switch (args[0].toLowerCase()) {

            case "help" -> {
                plugin.getLogger().info("Found 'help' argument");
                plugin.getLogger().info("Checking if there are arguments");
                if (args.length == 1) {
                    plugin.getLogger().info("No args found, sending basic help menu");
                    player.sendMessage("ShrineSpawn Help Menu:");
                    player.sendMessage("Available commands:");
                    helpMessages.forEach((cmd, desc) -> player.sendMessage(cmd));
                    return true;
                } else if (args.length == 2) {
                    String cmd = args[1].toLowerCase();
                    if (helpMessages.containsKey(cmd)) {
                        player.sendMessage(cmd + " - " + helpMessages.get(cmd));
                    } else {
                        player.sendMessage("No help available for command: " + cmd);
                    }
                    return true;


                }


            }
        }


        return false;


    }


}
