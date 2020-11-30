package com.heldenmc.calendar;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CalendarGUI {
    private final Player player;
    private final GUI gui;

    public CalendarGUI(Player player) {
        this.player = player;
        this.gui = new GUI();
    }

    public void openGUI() {
        Inventory inv = player.getInventory();
        int firstEmpty = inv.firstEmpty();

        for (int x = 0; x < 18; x++) {
            Day day = new Day(x);
            gui.setItem(x, new ItemStack(Material.PAPER, 1), onClick -> {
                inv.setItem(firstEmpty, day.getItem());
                day.runCommand(player);
            });
        }

        gui.setItem(18, new ItemStack(Material.RED_STAINED_GLASS_PANE, 1));
        gui.setItem(26, new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1));

        for (int x = 19; x < 26; x++) {
            Day day = new Day(x);
            gui.setItem(x, new ItemStack(Material.PAPER, 1), onClick -> {
                inv.setItem(firstEmpty, day.getItem());
                day.runCommand(player);
            });
        }
    }
}
