package de.harrisblog.simplexporbs2;

import de.harrisblog.simplexporbs2.commands.XpCommand;
import de.harrisblog.simplexporbs2.commands.XpCommandTabCompleter;
import de.harrisblog.simplexporbs2.controllers.MessageManager;
import de.harrisblog.simplexporbs2.controllers.OptionsManager;
import de.harrisblog.simplexporbs2.events.OrbInteractEvent;
import de.harrisblog.simplexporbs2.events.DropXpOnDeath;
import org.bukkit.Bukkit;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleXPOrbs2 extends JavaPlugin {

    //TODO
    //Command Tab Completer

    private static Plugin plugin;
    private static MessageManager messageManager;
    private static OptionsManager optionsManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        plugin = this;
        this.getCommand("xporb").setExecutor(new XpCommand());
        this.getCommand("xporb").setTabCompleter(new XpCommandTabCompleter());
        Bukkit.getServer().getPluginManager().registerEvents(new OrbInteractEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DropXpOnDeath(), this);
        messageManager = new MessageManager();
        optionsManager = new OptionsManager();



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Plugin getPlugin() {
        return plugin;
    }
    public static MessageManager getMessageManager() { return messageManager; }

    public static OptionsManager getOptionsManager() {
        return optionsManager;
    }
}
