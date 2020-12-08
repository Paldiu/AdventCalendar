package com.heldenmc.utils;

import com.heldenmc.config.UserFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;

public class PlaytimeTracker implements Consumer<BukkitTask> {
    @Override
    public void accept(BukkitTask t) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            UserFactory factory = new UserFactory(player.getUniqueId());
            factory.check();
            factory.updatePlaytime(System.currentTimeMillis());

            if (!Utilities.playTime.containsKey(player)) {
                Utilities.playTime.put(player, System.currentTimeMillis());
            } else {
                Utilities.playTime.replace(player, System.currentTimeMillis());
            }

            try {
                factory.save();
            } catch (IOException ex) {
                Bukkit.getLogger().severe(ex.getMessage());
            }
        });
    }
}
