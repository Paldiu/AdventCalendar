package com.heldenmc.calendar;

import org.bukkit.entity.Player;

public class CalendarGUI {
    private final Player player;
    private final GUI gui;

    public CalendarGUI(Player player) {
        this.player = player;
        this.gui = new GUI();
    }

    public void openGUI() {
        gui.setItem(0, new Day("day_1").getItem());
        gui.setItem(1, new Day("day_2").getItem());
    }
}
