package de.rene_majewski.mc.spigot.plugin;

import org.bukkit.plugin.PluginLoadOrder;

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
public class Statistics extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}