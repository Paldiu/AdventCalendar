package com.heldenmc.calendar;

import com.heldenmc.utils.ProjectBase;
import com.heldenmc.utils.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Command_adventcalendar extends ProjectBase implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (args.length != 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be run by players!");
                return true;
            }

            if (sender.hasPermission("adventcalendar.use")) {
                new CalendarGUI((Player)sender).openGUI();
                return true;
            }
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("adventcalendar.reload")) {
                    getPlugin().users.reload();
                    getPlugin().config.reload();
                    sender.sendMessage(ChatColor.GREEN + "Plugin configuration reloaded!");
                } else {
                    sender.sendMessage("You cannot do that!");
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("debug") && (sender instanceof ConsoleCommandSender)) {
                sender.sendMessage("This is only to be used VIA IDE Debugger.");
                sender.sendMessage(new Day(1).getFormatDay());
                sender.sendMessage(new Day(5).getFormatDay());
                return true;
            }
        }

        return true;
    }
}
