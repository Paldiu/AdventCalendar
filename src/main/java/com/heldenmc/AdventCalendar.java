package com.heldenmc;

import com.heldenmc.config.YamlUser;
import com.heldenmc.utils.PlaytimeTracker;
import com.heldenmc.utils.ProjectBase;
import com.heldenmc.config.YamlConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class AdventCalendar extends JavaPlugin {
    public AdventCalendar plugin;
    public ProjectBase base;
    public YamlConfig config;
    public YamlUser users;

    @Override
    public void onLoad() {
        plugin = this;
        base = ProjectBase.getInstance();
    }

    @Override
    public void onEnable() {
        config = new YamlConfig(this);
        users = new YamlUser(this);
        base.registerCommands();
        base.registerListeners();
        base.getScheduler().runTaskTimer(this, new PlaytimeTracker(), 20L * 60 * 5, 20L * 60 * 5);
    }

    @Override
    public void onDisable() {
        base.getScheduler().cancelTasks(this);
        try {
            config.save();
            users.save();
        } catch (IOException ex) {
            Bukkit.getLogger().severe(ex.getMessage());
        }
    }
}
