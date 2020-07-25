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

/**
 * Hauptklasse des Plugins.
 *
 * @author René Majewski
 * @since 0.1
 */
@Plugin(name = "StatisticsServer", version = "0.1")
@Description(value = "Generate a statistic in a MySQL database.")
@LoadOrder(value = PluginLoadOrder.POSTWORLD)
@Author(value = "Thuringa")
@Website(value = "mc.rene-majewski.de")
public class StatisticsPlugin extends JavaPlugin implements Listener {
    /**
     * Speichert die Haupt-Konfiguration.
     */
    private FileConfiguration mConfig = getConfig();

    /**
     * Wird aufgerufen, wenn das Plugin aktiviert wird.
     */
    @Override
    public void onEnable() {
        // Konfiguration erstellen / laden
        mConfig.addDefault("db.host", "");
        mConfig.addDefault("db.port", Database.STANDARD_PORT);
        mConfig.addDefault("db.db_name", "");
        mConfig.addDefault("db.user", "");
        mConfig.addDefault("db.password", "");
        mConfig.addDefault("db.table_prefix", "statistics_");

        // Konfiguration speichern
        mConfig.options().copyDefaults(true);
        saveConfig();

        // Auf Ereignisse reagieren
        getServer().getPluginManager().registerEvents(this, this);

        // Zugriff auf Datenbank vorbereiten
        getLogger().info("Prepare access to database");
        Database db = Database.getInstance();
        db.open(
            mConfig.getString("db.host"),
            mConfig.getInt("db.port"),
            mConfig.getString("db.db_name"),
            mConfig.getString("db.user"),
            mConfig.getString("db.password")
        );
        db.createDatabase();
    }

    /**
     * Wird aufgerufen, wenn das Plugin deaktiviert wird.
     */
    @Override
    public void onDisable() {
        // Verbindung zur Datenbank beenden
        Database.getInstance().close();
        getLogger().info("Close connection to database");
    }

    /**
     * Wird aufgerufen, wenn ein Benutzer den Server beitritt.
     *
     * @param event Daten des Ereignisses.
     */
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();
    }
}
