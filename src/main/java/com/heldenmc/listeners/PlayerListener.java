package com.heldenmc.listeners;

import com.heldenmc.config.UserFactory;
import com.heldenmc.utils.ProjectBase;
import com.heldenmc.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.IOException;

public class PlayerListener extends ProjectBase implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UserFactory factory = new UserFactory(player.getUniqueId());
        factory.check();
        if (!player.hasPlayedBefore()) {
            factory.addInitialTime(System.currentTimeMillis());
        }
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
    }
}
