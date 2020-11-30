package com.heldenmc.listeners;

import com.heldenmc.config.UserFactory;
import com.heldenmc.utils.ProjectBase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerListener extends ProjectBase implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        UserFactory factory = new UserFactory(player);
        factory.check();
        factory.addInitialTime(System.currentTimeMillis());
        factory.updatePlaytime(System.currentTimeMillis());
    }
}
