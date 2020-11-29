package com.heldenmc.config;

import com.heldenmc.utils.ProjectBase;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;

import java.util.Map;

public class ConfigGetter extends ProjectBase {
    private final ConfigurationSection calendar;
    private final ConfigurationSection currentDay;
    private final ConfigurationSection enchants;

    public ConfigGetter(String day) {
        calendar = plugin.config.getConfigurationSection("calendar");
        currentDay = calendar.getConfigurationSection(day);
        enchants = currentDay.getConfigurationSection("enchantments");
    }

    public ConfigurationSection getCalendar() {
        return calendar;
    }

    public ConfigurationSection getCurrentDay() {
        return currentDay;
    }

    public ConfigurationSection getEnchants() {
        return enchants;
    }

    public ConfigurationSection getEnchantment(String enchantment) {
        return getEnchants().getConfigurationSection(enchantment);
    }

    public Integer getAmount() {
        return getCurrentDay().getInt("amount");
    }

    public Material getItem() {
        return Material.getMaterial(getCurrentDay().getString("item"));
    }

}
