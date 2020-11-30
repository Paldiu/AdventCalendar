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
    private final int index;

    public ConfigGetter(int index) {
        this.index = index;
        calendar = plugin.config.getConfigurationSection("calendar");
    }

    protected ConfigurationSection getCalendar() {
        return calendar;
    }

    public ConfigurationSection getDay() {
        List<String> keys = new ArrayList<>(getCalendar().getKeys(false));
        return getCalendar().getConfigurationSection(keys.get(index));
    }

    public ConfigurationSection getEnchants() {
        return getDay().getConfigurationSection("enchants");
    }

    public Set<String> enchantmentSet() {
        return getEnchants().getKeys(false);
    }

    public Map<Enchantment, Integer> enchantmentMap() {
        if (enchantmentSet().isEmpty() || enchantmentSet() == null) {
            return null;
        }
        Map<Enchantment, Integer> map = new HashMap<>();
        enchantmentSet().forEach(key -> {
            map.put(Enchantment.getByKey(NamespacedKey.minecraft(key)), getEnchants().getInt(key));
        });
        return map;
    }

    // This lets the list be empty without returning null. An empty list here is better than a null one! :)
    public List<String> getLore() {
        return new ArrayList<>(getDay().getStringList("lore"));
    }

    public String getName() {
        return getDay().getString("name");
    }

    public Integer getAmount() {
        return getDay().getInt("amount");
    }

    public Material getItem() {
        return Material.getMaterial(getDay().getString("item"));
    }

    public List<String> getCommands(CommandSender sender) {
        List<String> temp = new ArrayList<>();
        if (!getDay().isList("commands")) { return null; }
        getDay().getStringList("commands").forEach(command -> {
            if (command.contains("%player%") && (sender instanceof Player)) {
                command.replace("%player%", sender.getName());
            }
            temp.add(command);
        });
        return temp;
    }

}
