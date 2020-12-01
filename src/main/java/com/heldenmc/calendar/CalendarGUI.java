package com.heldenmc.calendar;

import com.heldenmc.config.ConfigGetter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CalendarGUI {
    private final Player player;
    private final GUI gui;
    private final ConfigGetter getter;

    public CalendarGUI(Player player) {
        this.player = player;
        this.gui = new GUI();
        this.getter = new ConfigGetter();
    }

    public void openGUI() {
        Inventory inv = player.getInventory();
        int firstEmpty = inv.firstEmpty();
        int size = getter.getCalendarSize();

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
            Day day = new Day(x - 1);
            gui.setItem(x, new ItemStack(Material.PAPER, 1), onClick -> {
                inv.setItem(firstEmpty, day.getItem());
                day.runCommand(player);
            });
        }
    }

    private int sizer(int fromSize) {
        if (fromSize <= 9) return 9;
        if (fromSize > 9 && fromSize <= 18) return 18;
        if (fromSize > 18 && fromSize <= 27) return 27;
        if (fromSize > 27 && fromSize <= 36) return 36;
        if (fromSize > 36 && fromSize <= 45) return 45;
        if (fromSize > 45 && fromSize <= 54) return 54;
        if (fromSize > 54) throw new IndexOutOfBoundsException("Size must be 54 or less!");

        return 54;
    }
}
