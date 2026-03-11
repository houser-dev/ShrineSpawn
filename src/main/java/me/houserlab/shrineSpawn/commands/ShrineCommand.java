package me.houserlab.shrineSpawn.commands;

import me.houserlab.shrineSpawn.ShrineSpawn;
import me.houserlab.shrineSpawn.menus.ShrineDialogMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ShrineCommand implements CommandExecutor, TabExecutor {

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
            sender.sendMessage(Component.text("This command can only be executed by players.", NamedTextColor.RED));
            plugin.getLogger().warning(sender.getName() + " attempted to run '" + label + "' but is not a player.");
            return true;
        }

        if (!player.hasPermission("shrinespawn.admin")) {
            plugin.debug(player.getName() + " attempted '" + label + "' without shrinespawn.admin permission.");
            player.sendMessage(Component.text("You do not have permission to do that.", NamedTextColor.RED));
            return true;
        }

        if (args.length == 0) {
            return false;
        }

        switch (args[0].toLowerCase()) {

            case "help" -> {
                if (args.length == 1) {
                    player.sendMessage(Component.text("ShrineSpawn Help Menu:", NamedTextColor.GREEN));
                    helpMessages.forEach((cmd, desc) ->
                            player.sendMessage(Component.text("  /" + label + " " + cmd + " - ", NamedTextColor.GREEN)
                                    .append(Component.text(desc, NamedTextColor.GRAY))));
                    return true;
                } else if (args.length == 2) {
                    String cmd = args[1].toLowerCase();
                    String desc = helpMessages.get(cmd);
                    if (desc != null) {
                        player.sendMessage(Component.text("/" + label + " " + cmd + " - ", NamedTextColor.DARK_GREEN)
                                .append(Component.text(desc, NamedTextColor.GRAY)));
                    } else {
                        player.sendMessage(Component.text("No help available for command: " + cmd, NamedTextColor.RED));
                    }
                    return true;
                }
            }
            case "tp" -> {

            }
            case "menu" -> {
                ShrineDialogMenu.openMainMenu(player);
                return true;
            }
        }


        return false;


    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1) {
            return Arrays.asList("help", "tp", "set", "remove", "list", "menu");
        } else if (args.length == 2 && args[0].equals("help")) {
            return Arrays.asList("tp", "set", "remove", "list");
        }
        return List.of();
    }
}
