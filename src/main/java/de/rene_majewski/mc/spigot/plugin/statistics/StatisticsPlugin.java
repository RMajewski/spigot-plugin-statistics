package de.rene_majewski.mc.spigot.plugin.statistics;

import org.bukkit.plugin.PluginLoadOrder;

import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.plugin.java.annotation.plugin.author.Author;

import de.rene_majewski.mc.spigot.plugin.statistics.database.Database;

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
        config.addDefault("db.host", "");
        config.addDefault("db.port", 3306);
        config.addDefault("db.db_name", "");
        config.addDefault("db.user", "");
        config.addDefault("db.password", "");
        config.addDefault("db.table_prefix", "statistics_");

        // Konfiguration speichern
        config.options().copyDefaults(true);
        saveConfig();

        // Auf Ereignisse reagieren
        getServer().getPluginManager().registerEvents(this, this);

        // Zugriff auf Datenbank vorbereiten
        getLogger().info("Prepare access to database");
        Database db = Database.getInstance();
        db.open(
            config.getString("db.host"),
            config.getInt("db.port"),
            config.getString("db.db_name"),
            config.getString("db.user"),
            config.getString("db.password")
        );
        db.createDatabase();
    }

    @Override
    public void onDisable() {
        // Verbindung zur Datenbank beenden
        Database.getInstance().close();
        getLogger().info("Close connection to database");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
    }

    
}