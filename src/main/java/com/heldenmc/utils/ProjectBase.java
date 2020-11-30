package com.heldenmc.utils;

import com.heldenmc.AdventCalendar;
import com.heldenmc.calendar.Command_adventcalendar;
import com.heldenmc.listeners.GUIListener;
import com.heldenmc.listeners.PlayerListener;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class ProjectBase {
    protected final AdventCalendar plugin = JavaPlugin.getPlugin(AdventCalendar.class);
    protected final Server server = plugin.getServer();
    protected final PluginManager manager = server.getPluginManager();
    protected final BukkitScheduler scheduler = server.getScheduler();

    public ProjectBase() {}

    public AdventCalendar getPlugin() {
        return plugin;
    }

    public Server getServer() {
        return server;
    }

    public PluginManager getManager() {
        return manager;
    }

    public BukkitScheduler getScheduler() {
        return scheduler;
    }

    public void registerCommands() {
        getPlugin().getCommand("adventcalendar").setExecutor(new Command_adventcalendar());
    }

    public void registerListeners() {
        getManager().registerEvents(new GUIListener(), plugin);
        getManager().registerEvents(new PlayerListener(), plugin);
    }
}
