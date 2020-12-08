package com.heldenmc.utils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static final Map<Player, Long> playTime = new HashMap<>();

    public static boolean checkDate(String key) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return new Date().after(fmt.parse(key));
        } catch (ParseException ex) {
            Bukkit.getLogger().severe(ex.getMessage());
            return false;
        }
    }

    public static class DateFormat {
        final int year, day, month;
        final String D, M, Y;
        final Calendar cal;

        public DateFormat(String date) {
            day = Integer.parseInt(StringUtils.split(date, "/")[0]);
            month = Integer.parseInt(StringUtils.split(date, "/")[1]);
            year = Integer.parseInt(StringUtils.split(date, "/")[2]);

            cal = new GregorianCalendar(year, month, day);

            DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());

            D = Integer.toString(day);
            M = symbols.getMonths()[month - 1];
            Y = Integer.toString(year);
        }

        public String getDay() {
            return D;
        }

        public String getMonth() {
            return M;
        }

        public String getYear() {
            return Y;
        }
    }
}
