package com.heldenmc.calendar;

import com.heldenmc.config.ConfigGetter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private final ConfigGetter getter;
    private final ItemStack item;
    private final ItemMeta meta;
    private final List<String> lore;

    public Day(String day) {
        getter = new ConfigGetter(day);
        item = new ItemStack(getter.getItem(), getter.getAmount());
        meta = item.getItemMeta();
        lore = new ArrayList<>(getter.getLore());

        meta.setDisplayName(getter.getName());
        meta.setLore(lore);
        getter.enchantmentMap().forEach((enchantment, integer) -> {
            meta.addEnchant(enchantment, integer, false);
        });
        item.setItemMeta(meta);
    }

    public ItemStack getItem() {
        return item;
    }
}
