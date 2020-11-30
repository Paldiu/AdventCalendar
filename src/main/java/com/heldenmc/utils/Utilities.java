package com.heldenmc.utils;

import org.bukkit.ChatColor;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Utilities {
    public static String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static Date getFromMillis(long timeInMillis) {
        return Date.from(Instant.ofEpochMilli(timeInMillis));
    }

    public static int calc(long time1, long time2) {
        return (int) TimeUnit.MILLISECONDS.toMinutes(time2 - time1);
    }
}
