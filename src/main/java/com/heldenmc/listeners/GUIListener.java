package com.heldenmc.listeners;

import com.heldenmc.calendar.AbstractGUI;
import com.heldenmc.config.ConfigGetter;
import com.heldenmc.config.UserFactory;
import com.heldenmc.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GUIListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) { return; }

        Player player = (Player) event.getWhoClicked();
        UserFactory factory = new UserFactory(player);
        UUID pID = player.getUniqueId();
        UUID invUUID = AbstractGUI.getOpenInvs().get(pID);

        if (invUUID != null) {
            event.setCancelled(true);
            AbstractGUI gui = AbstractGUI.getInvByUUID().get(invUUID);
            AbstractGUI.GUIAction action = gui.getActions().get(event.getSlot());
            if (action != null) {
                if ((Utilities.calc(factory.getInitialTime(), factory.getPlayTime()) > 30)) {
                    action.click(player);
                    factory.setSlotUsed(event.getSlot(), true);
                } else {
                    player.sendMessage("You have not played long enough to warrant usage of the calendar!");
                    return;
                }
            }
        }
    }

    public boolean checkDate(String key) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return new Date().after(fmt.parse(key));
        } catch (ParseException ex) {
            Bukkit.getLogger().severe(ex.getMessage());
            return false;
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        UUID pID = player.getUniqueId();
        AbstractGUI.getOpenInvs().remove(pID);
    }
}
