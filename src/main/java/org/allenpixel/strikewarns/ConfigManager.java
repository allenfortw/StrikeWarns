package org.allenpixel.strikewarns;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private final StrikeWarns plugin;

    public ConfigManager(StrikeWarns plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
    }

    public void executeCommandIfThresholdMet(String player, int strikeCount) {
        FileConfiguration config = plugin.getConfig();
        String command = config.getString("strike_count." + strikeCount);

        if (command == null && strikeCount >= config.getConfigurationSection("strike_count").getKeys(false).size()) {
            // 如果没有对应strikeCount的命令，并且strikeCount达到最大值
            command = config.getString("strike_count.max");
        }

        if (command != null) {
            command = command.replace("%player%", player);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            plugin.getLogger().info(player + " 达到Strike值 " + strikeCount + "，执行命令: " + command);
            logAction(player, strikeCount, command);
        }
    }

    private void logAction(String player, int strikeCount, String command) {
        // 实现日志记录
    }
}
