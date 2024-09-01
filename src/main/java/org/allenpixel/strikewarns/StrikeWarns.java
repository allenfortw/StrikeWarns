package org.allenpixel.strikewarns;

import org.bukkit.plugin.java.JavaPlugin;

public class StrikeWarns extends JavaPlugin {
    private StrikeDatabase strikeDatabase;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        this.strikeDatabase = new StrikeDatabase(this);
        this.configManager = new ConfigManager(this);

        getCommand("strike").setExecutor(new CommandStrike(this));
        getCommand("strike").setTabCompleter(new StrikeTabCompleter());  // 注册TabCompleter

        getLogger().info("StrikeWarns 插件已启用");
    }

    @Override
    public void onDisable() {
        getLogger().info("StrikeWarns 插件已禁用");
    }

    public StrikeDatabase getStrikeDatabase() {
        return strikeDatabase;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
