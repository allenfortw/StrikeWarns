package org.allenpixel.strikewarns;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StrikeTabCompleter implements TabCompleter {
    private final List<String> subCommands = Arrays.asList("help", "add", "set", "reload");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            for (String subCommand : subCommands) {
                if (subCommand.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(subCommand);
                }
            }
            Collections.sort(completions);
            return completions;
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("set")) {
                List<String> players = new ArrayList<>();
                for (Player player : sender.getServer().getOnlinePlayers()) {
                    players.add(player.getName());
                }
                Collections.sort(players);
                return players;
            }
        }

        return Collections.emptyList();
    }
}
