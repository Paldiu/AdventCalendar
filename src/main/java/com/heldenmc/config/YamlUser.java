package com.heldenmc.config;

import org.bukkit.plugin.java.JavaPlugin;

public class YamlUser extends AbstractConfig {
    public YamlUser(JavaPlugin plugin) {
        super(plugin, "users.yml", plugin.getDataFolder(), "users.yml");
    }
}
