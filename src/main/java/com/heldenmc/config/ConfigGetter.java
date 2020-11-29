package com.heldenmc.config;

import com.heldenmc.utils.ProjectBase;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.*;

public class ConfigGetter extends ProjectBase {
    private final ConfigurationSection calendar;
    private final ConfigurationSection currentDay;
    private final ConfigurationSection enchants;

    public ConfigGetter(String day) {
        calendar = plugin.config.getConfigurationSection("calendar");
        currentDay = calendar.getConfigurationSection(day);
        enchants = currentDay.getConfigurationSection("enchantments");
    }

    protected ConfigurationSection getCalendar() {
        return calendar;
    }

    public ConfigurationSection getCurrentDay() {
        return currentDay;
    }

    public ConfigurationSection getEnchants() {
        return enchants;
    }

    public Set<String> enchantmentSet() {
        return getEnchants().getKeys(false);
    }

    public Map<Enchantment, Integer> enchantmentMap() {
        Map<Enchantment, Integer> map = new HashMap<>();
        enchantmentSet().forEach(key -> {
            map.put(Enchantment.getByKey(NamespacedKey.minecraft(key)), getEnchants().getInt(key));
        });
        return map;
    }

    public List<String> getLore() {
        return getCurrentDay().getStringList("lore");
    }

    public String getName() {
        return getCurrentDay().getString("name");
    }

    public Integer getAmount() {
        return getCurrentDay().getInt("amount");
    }

    public Material getItem() {
        return Material.getMaterial(getCurrentDay().getString("item"));
    }

    public List<String> getCommands(CommandSender sender) {
        List<String> temp = new ArrayList<>();
        getCurrentDay().getStringList("commands").forEach(command -> {
            if (command.contains("%player%") && (sender instanceof Player)) {
                command.replace("%player%", sender.getName());
            }
            temp.add(command);
        });
        return temp;
    }

}
