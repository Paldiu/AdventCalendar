package com.heldenmc;

import com.heldenmc.utils.ProjectBase;
import com.heldenmc.config.YamlConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class AdventCalendar extends JavaPlugin {
    public AdventCalendar plugin;
    public ProjectBase base;
    public YamlConfig config;

    @Override
    public void onLoad() {
        plugin = this;
        base = new ProjectBase();
    }

    @Override
    public void onEnable() {
        config = new YamlConfig(this);
    }

    @Override
    public void onDisable() {

    }
}
