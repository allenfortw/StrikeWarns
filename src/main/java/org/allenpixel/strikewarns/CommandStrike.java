package org.allenpixel.strikewarns;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStrike implements CommandExecutor {
    private final StrikeWarns plugin;

    public CommandStrike(StrikeWarns plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("strike.view")) {
                    int strikeCount = plugin.getStrikeDatabase().getStrike(player.getName());
                    player.sendMessage(ChatColor.YELLOW + "[Strike] 您的Strike : " + ChatColor.RED + strikeCount);
                } else {
                    player.sendMessage(ChatColor.RED + "您没有权限查看Strike数。");
                }
            } else {
                sender.sendMessage("该指令只能由玩家执行。");
            }
            return true;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "help":
                sender.sendMessage(ChatColor.GREEN + "=====" + ChatColor.GOLD + " StrikeWarns 帮助 " + ChatColor.GREEN + "=====");
                sender.sendMessage(ChatColor.AQUA + "/strike help" + ChatColor.WHITE + " - 查看指令列表");
                sender.sendMessage(ChatColor.AQUA + "/strike add <player> <count>" + ChatColor.WHITE + " - 增加玩家的Strike值 (默认为1)");
                sender.sendMessage(ChatColor.AQUA + "/strike set <player> <count>" + ChatColor.WHITE + " - 设置玩家的Strike值");
                sender.sendMessage(ChatColor.AQUA + "/strike reload" + ChatColor.WHITE + " - 重新载入插件配置");
                sender.sendMessage(ChatColor.GREEN + "==============================");
                break;
            case "add":
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "Usage: /strike add <player> <count>");
                    return false;
                }
                String player = args[1];
                int count = args.length > 2 ? Integer.parseInt(args[2]) : 1;
                int newStrikeValue = plugin.getStrikeDatabase().addStrike(player, count);
                sender.sendMessage(ChatColor.YELLOW + player + " 的Strike增加至: " + ChatColor.RED + newStrikeValue);
                plugin.getConfigManager().executeCommandIfThresholdMet(player, newStrikeValue);
                break;
            case "set":
                if (args.length < 3) {
                    sender.sendMessage(ChatColor.RED + "Usage: /strike set <player> <count>");
                    return false;
                }
                player = args[1];
                count = Integer.parseInt(args[2]);
                plugin.getStrikeDatabase().setStrike(player, count);
                sender.sendMessage(ChatColor.YELLOW + player + " 的Strike值設定為: " + ChatColor.RED + count);
                plugin.getConfigManager().executeCommandIfThresholdMet(player, count);
                break;
            case "reload":
                plugin.reloadConfig();
                plugin.getConfigManager().reload();
                sender.sendMessage(ChatColor.GREEN + "StrikeWarns 配置已重新加载。");
                break;
            default:
                sender.sendMessage(ChatColor.RED + "未知指令，请使用 " + ChatColor.AQUA + "/strike help" + ChatColor.RED + " 查看帮助。");
                break;
        }
        return true;
    }
}
