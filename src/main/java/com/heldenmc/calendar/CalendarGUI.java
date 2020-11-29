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

        gui.setItem(0, new ItemStack(Material.PAPER, 1), onClick -> {
            inv.setItem(firstEmpty, new Day("day_1").getItem());
        });
        gui.setItem(1, new ItemStack(Material.PAPER, 1), onClick -> {
            inv.setItem(firstEmpty, new Day("day_2").getItem());
        });
    }
}
