package com.heldenmc.config;

import com.heldenmc.utils.ProjectBase;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.UUID;

public class UserFactory extends ProjectBase {
    private final Player player;
    private final UUID uuid;
    private final String name;
    private long playTime;

    public UserFactory(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.playTime = System.currentTimeMillis();
    }

    public UserFactory(UUID uuid) {
        this.player = (Player)Bukkit.getOfflinePlayer(uuid);
        this.uuid = uuid;
        this.name = Bukkit.getOfflinePlayer(uuid).getName();
        this.playTime = System.currentTimeMillis();
    }

    public ConfigurationSection get() {
        return getUsers().getConfigurationSection(getUuid().toString());
    }

    public final void addInitialTime(long time) {
        get().set("joined", time);
    }

    public final long getInitialTime() {
        return get().getLong("joined");
    }

    public void updatePlaytime(long time) {
        get().set("playtime", time);
        playTime = time;
    }

    public boolean check() {
        if (!getUsers().isConfigurationSection(getUuid().toString()))
            create();
        return true;
    }

    protected ConfigurationSection getUsers() {
        return plugin.config.getConfigurationSection("users");
    }

    protected void create() {
        getUsers().createSection(getUuid().toString());
    }

    public void setSlotUsed(Integer i, Boolean bool) {
        get().set(i.toString(), bool);
    }

    public boolean isSlotUsed(Integer i) {
        if (get().isBoolean(i.toString())) {
            return get().getBoolean(i.toString());
        } else return false;
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public long getPlayTime() {
        return playTime;
    }
}
