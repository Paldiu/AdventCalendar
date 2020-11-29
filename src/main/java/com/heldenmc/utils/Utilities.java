package com.heldenmc.utils;

import org.bukkit.ChatColor;

public class Utilities {
    public static String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
