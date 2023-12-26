package de.harrisblog.simplexporbs2.controllers;

import de.harrisblog.simplexporbs2.SimpleXPOrbs2;
import org.bukkit.plugin.Plugin;

public class OptionsManager {
    private boolean on_death_msg;
    private boolean on_redeem_msg;
    private boolean drop_on_death;
    private boolean on_withdraw_msg;
    private Plugin plugin;

    public OptionsManager(){
        plugin = SimpleXPOrbs2.getPlugin();
        loadOptions();
    }

    public void loadOptions(){
        drop_on_death = Boolean.parseBoolean(plugin.getConfig().getString("xporbs.drop_on_death"));
        on_redeem_msg = Boolean.parseBoolean(plugin.getConfig().getString("xporbs.send_redeem_message"));
        on_death_msg = Boolean.parseBoolean(plugin.getConfig().getString("xporbs.send_death_message"));
        on_withdraw_msg = Boolean.parseBoolean(plugin.getConfig().getString("xporbs.send_withdraw_message"));
    }

    public boolean isOn_withdraw_msg() {
        return on_withdraw_msg;
    }

    public boolean isOn_death_msg() {
        return on_death_msg;
    }

    public boolean isOn_redeem_msg() {
        return on_redeem_msg;
    }

    public boolean isDrop_on_death() {
        return drop_on_death;
    }
}
