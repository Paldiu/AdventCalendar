package com.heldenmc.config;

import com.heldenmc.utils.ProjectBase;
import com.heldenmc.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.UUID;

public class UserFactory extends ProjectBase {
    private final Player player;
    private final UUID uuid;
    private final String name;
    private final long startTime;
    private long playTime;

    public UserFactory(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.startTime = System.currentTimeMillis();
    }

    public UserFactory(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
        this.uuid = uuid;
        this.name = Bukkit.getPlayer(uuid).getName();
        this.startTime = System.currentTimeMillis();
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

    public final void save() throws IOException {
        plugin.users.save();
    }

    public void updatePlaytime(long time) {
        try {
            playTime = time;
            get().set("playtime", time);
            save();
        } catch (IOException ex) {
            Bukkit.getLogger().severe(ex.getMessage());
        }
    }

    public boolean check() {
        if (!getUsers().isConfigurationSection(getUuid().toString()))
            create();
        return true;
    }

    protected ConfigurationSection getUsers() {

        return plugin.users.getConfigurationSection("users");
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
        return Utilities.playTime.get(player);
    }
}
