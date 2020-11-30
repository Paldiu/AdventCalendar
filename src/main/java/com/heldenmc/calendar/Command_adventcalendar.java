package com.heldenmc.calendar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_adventcalendar implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by players!");
            return true;
        }

        if (sender.hasPermission("adventcalendar.use")) {
            new CalendarGUI((Player)sender).openGUI();
        }

        return true;
    }
}
