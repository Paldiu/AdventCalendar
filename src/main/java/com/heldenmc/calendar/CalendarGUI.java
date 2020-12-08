package com.heldenmc.calendar;

import com.heldenmc.config.ConfigGetter;
import com.heldenmc.config.UserFactory;
import com.heldenmc.utils.ProjectBase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarGUI extends ProjectBase {
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
        int slot = 0;
        ItemStack EMPTYSLOT = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
        ItemStack OPENEDSLOT = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1);
        ItemStack READYSLOT = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemStack TODAYSLOT = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1);
        ItemStack NOTREADY = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta OPENED = OPENEDSLOT.getItemMeta();
        ItemMeta READY = READYSLOT.getItemMeta();
        ItemMeta TODAY = TODAYSLOT.getItemMeta();
        ItemMeta NOT = NOTREADY.getItemMeta();

        for (int x = 0; x < 9; x++) {
            gui.setItem(x, EMPTYSLOT);
        }

        gui.setItem(9, EMPTYSLOT);
        gui.setItem(10, EMPTYSLOT);

        slot = 0;
        for (int x = 11; x < 16; x++) {
            Day day = new Day(slot);
            slot++;

            READY.setDisplayName(ChatColor.DARK_GREEN + day.getFormatDay());
            TODAY.setDisplayName(ChatColor.BLUE + day.getFormatDay());
            OPENED.setDisplayName(ChatColor.GRAY + day.getFormatDay());
            NOT.setDisplayName(ChatColor.RED + day.getFormatDay());

            TODAYSLOT.setItemMeta(TODAY);
            READYSLOT.setItemMeta(READY);
            OPENEDSLOT.setItemMeta(OPENED);
            NOTREADY.setItemMeta(NOT);

            if (!checkDate(day.getDay())) {
                gui.setItem(x, NOTREADY, onClick -> {
                    player.sendMessage("You cannot open that day yet!");
                    gui.close(player);
                });
                continue;
            }

            if (new UserFactory(player.getUniqueId()).isSlotUsed(x)) {
                gui.setItem(x, OPENEDSLOT, onClick -> {
                    player.sendMessage("You have already claimed for this day!");
                    gui.close(player);
                });
                continue;
            }

            if (today(day.getDay())) {
                gui.setItem(x, TODAYSLOT, onClick -> {
                    inv.setItem(firstEmpty, day.getItem());
                    day.runCommand(player);
                    gui.close(player);
                });
                continue;
            }

            gui.setItem(x, READYSLOT, onClick -> {

                inv.setItem(firstEmpty, day.getItem());
                day.runCommand(player);
                gui.close(player);
            });
        }

        gui.setItem(16, EMPTYSLOT);
        gui.setItem(17, EMPTYSLOT);
        gui.setItem(18, EMPTYSLOT);
        gui.setItem(19, EMPTYSLOT);

        slot = 5;
        for (int x = 20; x < 25; x++) {
            Day day = new Day(slot);
            slot++;

            READY.setDisplayName(ChatColor.DARK_GREEN + day.getFormatDay());
            TODAY.setDisplayName(ChatColor.BLUE + day.getFormatDay());
            OPENED.setDisplayName(ChatColor.GRAY + day.getFormatDay());
            NOT.setDisplayName(ChatColor.RED + day.getFormatDay());

            TODAYSLOT.setItemMeta(TODAY);
            READYSLOT.setItemMeta(READY);
            OPENEDSLOT.setItemMeta(OPENED);
            NOTREADY.setItemMeta(NOT);

            if (!checkDate(day.getDay())) {
                gui.setItem(x, NOTREADY, onClick -> {
                    player.sendMessage("You cannot open that day yet!");
                    gui.close(player);
                });
                continue;
            }

            if (new UserFactory(player.getUniqueId()).isSlotUsed(x)) {
                gui.setItem(x, OPENEDSLOT, onClick -> {
                    player.sendMessage("You have already claimed for this day!");
                    gui.close(player);
                });
                continue;
            }

            if (today(day.getDay())) {
                gui.setItem(x, TODAYSLOT, onClick -> {
                    inv.setItem(firstEmpty, day.getItem());
                    day.runCommand(player);
                    gui.close(player);
                });
                continue;
            }

            gui.setItem(x, READYSLOT, onClick -> {

                inv.setItem(firstEmpty, day.getItem());
                day.runCommand(player);
                gui.close(player);
            });
        }

        gui.setItem(25, EMPTYSLOT);
        gui.setItem(26, EMPTYSLOT);
        gui.setItem(27, EMPTYSLOT);
        gui.setItem(28, EMPTYSLOT);

        slot = 10;
        for (int x = 29; x < 34; x++) {
            Day day = new Day(slot);
            slot++;

            READY.setDisplayName(ChatColor.DARK_GREEN + day.getFormatDay());
            TODAY.setDisplayName(ChatColor.BLUE + day.getFormatDay());
            OPENED.setDisplayName(ChatColor.GRAY + day.getFormatDay());
            NOT.setDisplayName(ChatColor.RED + day.getFormatDay());

            TODAYSLOT.setItemMeta(TODAY);
            READYSLOT.setItemMeta(READY);
            OPENEDSLOT.setItemMeta(OPENED);
            NOTREADY.setItemMeta(NOT);

            if (!checkDate(day.getDay())) {
                gui.setItem(x, NOTREADY, onClick -> {
                    player.sendMessage("You cannot open that day yet!");
                    gui.close(player);
                });
                continue;
            }

            if (new UserFactory(player.getUniqueId()).isSlotUsed(x)) {
                gui.setItem(x, OPENEDSLOT, onClick -> {
                    player.sendMessage("You have already claimed for this day!");
                    gui.close(player);
                });
                continue;
            }

            if (today(day.getDay())) {
                gui.setItem(x, TODAYSLOT, onClick -> {
                    inv.setItem(firstEmpty, day.getItem());
                    day.runCommand(player);
                    gui.close(player);
                });
                continue;
            }

            gui.setItem(x, READYSLOT, onClick -> {

                inv.setItem(firstEmpty, day.getItem());
                day.runCommand(player);
                gui.close(player);
            });
        }

        gui.setItem(34, EMPTYSLOT);
        gui.setItem(35, EMPTYSLOT);
        gui.setItem(36, EMPTYSLOT);
        gui.setItem(37, EMPTYSLOT);

        slot = 15;
        for (int x = 38; x < 43; x++) {
            Day day = new Day(slot);
            slot++;

            READY.setDisplayName(ChatColor.DARK_GREEN + day.getFormatDay());
            TODAY.setDisplayName(ChatColor.BLUE + day.getFormatDay());
            OPENED.setDisplayName(ChatColor.GRAY + day.getFormatDay());
            NOT.setDisplayName(ChatColor.RED + day.getFormatDay());

            TODAYSLOT.setItemMeta(TODAY);
            READYSLOT.setItemMeta(READY);
            OPENEDSLOT.setItemMeta(OPENED);
            NOTREADY.setItemMeta(NOT);

            if (!checkDate(day.getDay())) {
                gui.setItem(x, NOTREADY, onClick -> {
                    player.sendMessage("You cannot open that day yet!");
                    gui.close(player);
                });
                continue;
            }

            if (new UserFactory(player.getUniqueId()).isSlotUsed(x)) {
                gui.setItem(x, OPENEDSLOT, onClick -> {
                    player.sendMessage("You have already claimed for this day!");
                    gui.close(player);
                });
                continue;
            }

            if (today(day.getDay())) {
                gui.setItem(x, TODAYSLOT, onClick -> {
                    inv.setItem(firstEmpty, day.getItem());
                    day.runCommand(player);
                    gui.close(player);
                });
                continue;
            }

            gui.setItem(x, READYSLOT, onClick -> {

                inv.setItem(firstEmpty, day.getItem());
                day.runCommand(player);
                gui.close(player);
            });
        }

        gui.setItem(43, EMPTYSLOT);
        gui.setItem(44, EMPTYSLOT);
        gui.setItem(45, EMPTYSLOT);
        gui.setItem(46, EMPTYSLOT);

        slot = 20;
        for (int x = 47; x < 52; x++) {
            Day day = new Day(slot);
            slot++;

            READY.setDisplayName(ChatColor.DARK_GREEN + day.getDay());
            TODAY.setDisplayName(ChatColor.BLUE + day.getDay());
            OPENED.setDisplayName(ChatColor.GRAY + day.getDay());
            NOT.setDisplayName(ChatColor.RED + day.getDay());

            TODAYSLOT.setItemMeta(TODAY);
            READYSLOT.setItemMeta(READY);
            OPENEDSLOT.setItemMeta(OPENED);
            NOTREADY.setItemMeta(NOT);

            if (!checkDate(day.getDay())) {
                gui.setItem(x, NOTREADY, onClick -> {
                    player.sendMessage("You cannot open that day yet!");
                    gui.close(player);
                });
                continue;
            }

            if (new UserFactory(player.getUniqueId()).isSlotUsed(x)) {
                gui.setItem(x, OPENEDSLOT, onClick -> {
                    player.sendMessage("You have already claimed for this day!");
                    gui.close(player);
                });
                continue;
            }

            if (today(day.getDay())) {
                gui.setItem(x, TODAYSLOT, onClick -> {
                    inv.setItem(firstEmpty, day.getItem());
                    day.runCommand(player);
                    gui.close(player);
                });
                continue;
            }

            gui.setItem(x, READYSLOT, onClick -> {

                inv.setItem(firstEmpty, day.getItem());
                day.runCommand(player);
                gui.close(player);
            });
        }

        gui.setItem(52, EMPTYSLOT);
        gui.setItem(53, EMPTYSLOT);

        gui.open(player);
    }

    private boolean today(String key) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return new Date().equals(fmt.parse(key));
        } catch (ParseException ex) {
            return false;
        }
    }

    private boolean checkDate(String key) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return new Date().after(fmt.parse(key));
        } catch (ParseException ex) {
            Bukkit.getLogger().severe(ex.getMessage());
            return false;
        }
    }
}
