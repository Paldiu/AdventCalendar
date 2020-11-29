package com.heldenmc.config;

import org.bukkit.plugin.java.JavaPlugin;

public class YamlConfig extends AbstractConfig {
    public YamlConfig(JavaPlugin plugin) {
        super(plugin, "config.yml", plugin.getDataFolder(), "config.yml");
    }
}
