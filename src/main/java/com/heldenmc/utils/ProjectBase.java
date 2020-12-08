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

    // Protected access modifier so only inherited classes may access this resource.
    protected ProjectBase() {}

    // Instance initializer for external access.
    private static final ProjectBase instance = new ProjectBase();

    /**
     * Getter to ensure only one instance is ever available to outside resources.
     *
     * @return  This class
     */
    public static final ProjectBase getInstance() {
        return instance;
    }

    /**
     * @return  The providing plugin of this project base.
     */
    public AdventCalendar getPlugin() {
        return plugin;
    }

    /**
     * @return  The server currently running this plugin.
     */
    public Server getServer() {
        return server;
    }

    /**
     * @return  The Server's plugin manager.
     */
    public PluginManager getManager() {
        return manager;
    }

    /**
     * @return  The Server Scheduler Service
     */
    public BukkitScheduler getScheduler() {
        return scheduler;
    }

    /**
     * Registers the commands in a clean function to avoid messy onEnable boilerplate.
     */
    public void registerCommands() {
        getPlugin().getCommand("adventcalendar").setExecutor(new Command_adventcalendar());
    }

    /**
     * Registers the listeners with the plugin manager to avoid messy onEnable boilerplate.
     */
    public void registerListeners() {
        getManager().registerEvents(new GUIListener(), plugin);
        getManager().registerEvents(new PlayerListener(), plugin);
    }
}
