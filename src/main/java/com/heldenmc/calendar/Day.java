package com.heldenmc.calendar;

import com.heldenmc.config.ConfigGetter;
import com.heldenmc.utils.ProjectBase;
import com.heldenmc.utils.Utilities;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Day extends ProjectBase {
    private final ConfigGetter getter;
    private final ItemStack item;
    private final ItemMeta meta;
    private final List<String> lore;

    public Day(int index) {
        getter = new ConfigGetter().search(index);
        item = new ItemStack(getter.getItem(), getter.getAmount());
        meta = item.getItemMeta();
        lore = new ArrayList<>();
        if (!getter.getLore().isEmpty() || getter.getLore() != null) {
            getter.getLore().forEach(lorem -> {
                lore.add(Utilities.colorize(lorem));
            });
        }

        meta.setDisplayName(Utilities.colorize(getter.getName()));
        meta.setLore(lore);
        if (!getter.enchantmentMap().isEmpty() || getter.enchantmentMap() != null) {
            getter.enchantmentMap().forEach((enchantment, integer) -> {
                if (enchantment != null) {
                    meta.addEnchant(enchantment, integer, false);
                }
            });
        }
        item.setItemMeta(meta);
    }

    public String getDay() {
        return getter.getDay().getCurrentPath().split("\\.")[1];
    }

    public String getFormatDay() {
        return getter.getFormattedDate();
    }

    public String getMonth() { return getter.getMonth(); }

    public ItemStack getItem() {
        return item;
    }
    
    public void runCommand(Player player) {
        if (getter.getCommands(player) == null || getter.getCommands(player).isEmpty()) {
            return;
        }

        getter.getCommands(player).forEach(command -> {
            server.dispatchCommand(server.getConsoleSender(), command);
        });
    }
}
