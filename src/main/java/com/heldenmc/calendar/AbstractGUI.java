package com.heldenmc.calendar;

import com.heldenmc.utils.ProjectBase;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class AbstractGUI extends ProjectBase implements InventoryHolder, Listener {
    private final Inventory INV;
    private final Map<Integer, GUIAction> ACTIONS;
    private final UUID uuid;
    private final List<Integer> validNumbers = new ArrayList<>(Arrays.asList(9,18,27,36,45,54));

    public static final Map<UUID, AbstractGUI> invByUUID = new HashMap<>();
    public static final Map<UUID, UUID> openInvs = new HashMap<>();

    public AbstractGUI(int size, String name) {
        uuid = UUID.randomUUID();
        if (!validNumbers.contains(size)) {
            throw new NumberFormatException("Number must be a multiple of nine!");
        }
        INV = Bukkit.createInventory(null, size, name);
        ACTIONS = new HashMap<>();
        invByUUID.put(getUUId(), this);
    }

    public UUID getUUId() {
        return uuid;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return INV;
    }

    public interface GUIAction {
        void click(Player player);
    }

    public final void setItem(int slot, ItemStack stack, GUIAction action) {
        INV.setItem(slot, stack);
        if (action != null) {
            ACTIONS.put(slot, action);
        }
    }

    public final void setItem(int slot, ItemStack stack) {
        setItem(slot, stack, null);
    }

    public final void open(@NotNull Player player) {
        player.openInventory(INV);
        openInvs.put(player.getUniqueId(), getUUId());
    }

    public final void close(@NotNull Player player) {
        player.closeInventory();
        openInvs.remove(player.getUniqueId());
    }

    public final void delete() {
        Bukkit.getOnlinePlayers().forEach(player -> {
           UUID id = openInvs.get(player.getUniqueId());
           if (id.equals(getUUId())) {
               player.closeInventory();
           }
        });
        invByUUID.remove(getUUId());
    }

    public static Map<UUID, AbstractGUI> getInvByUUID() {
        return invByUUID;
    }

    public static Map<UUID, UUID> getOpenInvs() {
        return openInvs;
    }

    public Map<Integer, GUIAction> getActions() {
        return ACTIONS;
    }

    @NotNull
    public final ItemStack newItem(Material mat, @NotNull String name, String... lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        ArrayList<String> metaLore = new ArrayList<>(Arrays.asList(lore));
        meta.setLore(metaLore);
        item.setItemMeta(meta);
        return item;
    }

    @NotNull
    public final ItemStack newItem(Material mat, @NotNull String name) {
        return newItem(mat, name, "");
    }

    @NotNull
    public final ItemStack newPlayerHead(@NotNull Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta sku = (SkullMeta) item.getItemMeta();
        assert sku != null;
        sku.setDisplayName(player.getName());
        ArrayList<String> loreComments = new ArrayList<>();
        loreComments.add(player.getName());
        loreComments.add("Click for user specific info");
        sku.setLore(loreComments);
        sku.setOwningPlayer(player);
        item.setItemMeta(sku);
        return item;
    }
}
