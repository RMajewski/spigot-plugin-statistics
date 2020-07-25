package de.rene_majewski.mc.spigot.plugin.statistics;

import org.bukkit.plugin.PluginLoadOrder;

import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.plugin.java.annotation.plugin.author.Author;

import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.LoadOrder;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.Website;

@Plugin(name = "StatisticsServer", version = "0.1")
@Description(value = "Generate a statistic in a MySQL database.")
@LoadOrder(value = PluginLoadOrder.POSTWORLD)
@Author(value = "Thuringa")
@Website(value = "mc.rene-majewski.de")
public class StatisticsPlugin extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Konfiguration erstellen / laden
        config.addDefault("youAreAwesome", true);

        // Auf Ereignisse reagieren
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (config.getBoolean("youAreAwesome")) {
            player.sendMessage("You are awesome!");
        } else {
            player.sendMessage("You are not awesome...");
        }
    }

    
}