package com.heldenmc.utils;

import com.heldenmc.config.UserFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;

public class PlaytimeTracker implements Consumer<BukkitTask> {
    @Override
    public void accept(BukkitTask t) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            UserFactory factory = new UserFactory(player);
            factory.updatePlaytime(System.currentTimeMillis());
        });
    }
}
