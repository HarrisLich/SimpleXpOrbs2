package de.harrisblog.simplexporbs2.controllers;

import de.harrisblog.simplexporbs2.SimpleXPOrbs2;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private static Plugin plugin;
    private List<String> helpMsg;
    private String on_death;
    private String on_redeem;
    private String orb_no_xp;
    private String curr_xp;
    private String on_withdraw;
    private String withdraw_zero_xp;



    public MessageManager(){
        plugin = SimpleXPOrbs2.getPlugin();
        loadMsgs();
    }

    public void loadMsgs() {
        helpMsg = plugin.getConfig().getStringList("messages.help");
        on_death = plugin.getConfig().getString("messages.on_death");
        on_redeem = plugin.getConfig().getString("messages.on_redeem");
        orb_no_xp = plugin.getConfig().getString("messages.orb_no_xp");
        curr_xp = plugin.getConfig().getString("messages.curr_xp");
        on_withdraw = plugin.getConfig().getString("messages.on_withdraw");
        withdraw_zero_xp = plugin.getConfig().getString("messages.withdraw_zero_xp");
    }

    public List<String> getHelpMsg() {
        return helpMsg;
    }

    public String getWithdraw_zero_xp() {
        return withdraw_zero_xp;
    }

    public String getOn_withdraw(){
        return on_withdraw;
    }

    public String getOn_death() {
        return on_death;
    }

    public String getOn_redeem() {
        return on_redeem;
    }

    public String getOrb_no_xp() {
        return orb_no_xp;
    }

    public String getCurr_xp() {
        return curr_xp;
    }
}
