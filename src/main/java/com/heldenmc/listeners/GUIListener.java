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
                if ((Utilities.calc(factory.getInitialTime(), factory.getPlayTime()) > 30)
                && !factory.isSlotUsed(event.getSlot())) {
                    if (!checkDate(new ConfigGetter().search(event.getSlot()).getDay().getName())) {
                        player.sendMessage("You cannot open that day yet!");
                        return;
                    }
                    action.click(player);
                    factory.setSlotUsed(event.getSlot(), true);
                }
            }
        }
    }

    public boolean checkDate(String key) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return fmt.format(new Date()).equals(fmt.parse(key));
        } catch (ParseException ex) {
            Bukkit.getLogger().severe(ex.getMessage());
            return false;
        }
    }

    public void onOpen(InventoryOpenEvent event) {
        if (!(event.getPlayer() instanceof Player)) { return; }

        Player player = (Player) event.getPlayer();
        UserFactory factory = new UserFactory(player);
        UUID pID = player.getUniqueId();
        UUID invUUID = AbstractGUI.getOpenInvs().get(pID);
        if (invUUID != null) {
            AbstractGUI gui = AbstractGUI.getInvByUUID().get(invUUID);
            for (int x = 0; x < 27; x++) {
                if (factory.isSlotUsed(x)) {
                    gui.getInventory().setItem(x, new ItemStack(Material.MAP, 1));
                }
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
