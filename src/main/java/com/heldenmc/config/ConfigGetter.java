package com.heldenmc.config;

import com.heldenmc.utils.ProjectBase;
import com.heldenmc.utils.Utilities;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.*;

public class ConfigGetter extends ProjectBase {
    private final ConfigurationSection calendar;
    private int index = 0;

    public ConfigGetter() {
        calendar = plugin.config.getConfigurationSection("calendar");
    }

    public ConfigGetter search(int index) throws IndexOutOfBoundsException {
        this.index = index;
        return this;
    }

    protected ConfigurationSection getCalendar() {
        return calendar;
    }

    public int getCalendarSize() {
        return calendar.getKeys(false).size();
    }

    public ConfigurationSection getDay() {
        List<String> keys = new ArrayList<>(getCalendar().getKeys(false));
        return getCalendar().getConfigurationSection(keys.get(index));
    }

    public String getDated() {
        return StringUtils.split(getDay().getCurrentPath(), ".")[1];
    }

    public String getMonth() {
        return new Utilities.DateFormat(getDated()).getMonth();
    }

    public String getFormattedDate() {
        Utilities.DateFormat format = new Utilities.DateFormat(getDated());
        StringBuilder sb = new StringBuilder();
        sb.append(format.getMonth())
                .append(" ");

        switch (format.getDay()) {
            case "1":
            case "21":
            case "31":
                sb.append(format.getDay() + "st");
                break;

            case "2":
            case "22":
                sb.append(format.getDay() + "nd");
                break;

            case "3":
            case "23":
                sb.append(format.getDay() + "rd");
                break;

            default:
                sb.append(format.getDay() + "th");
                break;
        }

        return sb.toString();
    }

    public ConfigurationSection getEnchants() {
        if (!getDay().isConfigurationSection("enchants")) {
            getDay().createSection("enchants");
        }
        return getDay().getConfigurationSection("enchants");
    }

    public Set<String> enchantmentSet() {
        if (getEnchants() != null) {
            return getEnchants().getKeys(false);
        } else return new HashSet<>();
    }

    public Map<Enchantment, Integer> enchantmentMap() {
        if (enchantmentSet().isEmpty() || enchantmentSet() == null) {
            return new HashMap<>();
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

            if (command.contains("%date%")) {
                command.replace("%date%", getFormattedDate());
            }

            temp.add(command);
        });
        return temp;
    }

}
