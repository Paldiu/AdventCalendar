package com.heldenmc.listeners;

import com.heldenmc.calendar.AbstractGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.UUID;

public class GUIListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) { return; }

        Player player = (Player) event.getWhoClicked();
        UUID pID = player.getUniqueId();
        UUID invUUID = AbstractGUI.getOpenInvs().get(pID);
        if (invUUID != null) {
            event.setCancelled(true);
            AbstractGUI gui = AbstractGUI.getInvByUUID().get(invUUID);
            AbstractGUI.GUIAction action = gui.getActions().get(event.getSlot());
            if (action != null) {
                action.click(player);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        UUID pID = player.getUniqueId();
        AbstractGUI.getOpenInvs().remove(pID);
    }
}
