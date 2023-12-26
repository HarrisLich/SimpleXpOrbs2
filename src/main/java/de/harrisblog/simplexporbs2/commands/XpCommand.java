package de.harrisblog.simplexporbs2.commands;

import de.harrisblog.simplexporbs2.SimpleXPOrbs2;
import de.harrisblog.simplexporbs2.util.Experience;
import de.harrisblog.simplexporbs2.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class XpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("xporb") && args.length == 0 && commandSender.hasPermission("xporb.xporb")){
            for(String s1 : SimpleXPOrbs2.getMessageManager().getHelpMsg()){
                commandSender.sendMessage(Util.format(s1));
            }
            return true;
        }else if(args.length == 1 && Util.isNumeric(args[0]) && commandSender instanceof Player && commandSender.hasPermission("xporb.withdraw")){
            Player p = (Player) commandSender;
            int playerXp = Experience.getExp(p);
            if(Util.isNumeric(args[0])){
                int xpToRemove = Integer.parseInt(args[0]);
                if(xpToRemove == 0){
                    p.sendMessage(Util.format(SimpleXPOrbs2.getMessageManager().getWithdraw_zero_xp()));
                    return true;
                }
                if(playerXp - xpToRemove >= 0){
                    if(xpToRemove > 0){
                        xpToRemove *= -1;
                    }

                    Experience.changeExp(p, xpToRemove);

                    if(xpToRemove < 0){
                        xpToRemove *= -1;
                    }
                    ItemStack xpOrb = new ItemStack(Material.SLIME_BALL,1);
                    ItemMeta meta = xpOrb.getItemMeta();
                    meta.setDisplayName(Util.format("&a&lExp Orb &7(Right Click)"));
                    List<String> lore = new ArrayList<>();
                    lore.add(Util.format("&6&lValue &8» " + "&7" + xpToRemove + " exp"));
                    lore.add(Util.format("&6&lSummoner &8» " + "&7" + p.getPlayerProfile().getName()));
                    meta.setLore(lore);
                    xpOrb.setItemMeta(meta);
                    p.getInventory().addItem(xpOrb);
                    //handle withdraw msg
                    if(SimpleXPOrbs2.getOptionsManager().isOn_withdraw_msg()){
                        String msg = SimpleXPOrbs2.getMessageManager().getOn_withdraw();
                        if(msg.contains("{orbXp}")) msg = msg.replace("{orbXp}", Integer.toString(xpToRemove));
                        if(msg.contains("{playerXp}")) msg = msg.replace("{playerXp}", Integer.toString(Experience.getExp(p)));
                        p.sendMessage(Util.format(msg));
                    }
                    return true;
                }else{
                    String err_msg = SimpleXPOrbs2.getMessageManager().getOrb_no_xp();
                    if(err_msg.contains("{playerXp}")) err_msg = err_msg.replace("{playerXp}", Integer.toString(Experience.getExp(p)));
                    p.sendMessage(Util.format(err_msg));
                    return true;
                }
            }
        }else if(args.length == 1 && args[0].equalsIgnoreCase("xp") && commandSender.hasPermission("xporb.xp")){
            Player p = (Player) commandSender;
            String xp_msg = SimpleXPOrbs2.getMessageManager().getCurr_xp();
            if(xp_msg.contains("{playerXp}")) xp_msg = xp_msg.replace("{playerXp}", Integer.toString(Experience.getExp(p)));
            p.sendMessage(Util.format(xp_msg));
            return true;
        }else if(args.length == 1 && args[0].equalsIgnoreCase("reload") && commandSender.hasPermission("xporb.reload")){
            SimpleXPOrbs2.getPlugin().reloadConfig();
            SimpleXPOrbs2.getMessageManager().loadMsgs();
            SimpleXPOrbs2.getOptionsManager().loadOptions();
            commandSender.sendMessage("SimpleXpOrbs2 Reloaded");
            return true;
        }else if(args.length == 1 && args[0].equalsIgnoreCase("all") && commandSender.hasPermission("xporb.withdraw_all")){
            Player p = (Player) commandSender;
            int toRemove = Experience.getExp(p);
            if(toRemove  == 0){
                p.sendMessage(Util.format(SimpleXPOrbs2.getMessageManager().getWithdraw_zero_xp()));
                return true;
            }
            if(toRemove > 0) toRemove *= -1;
            Experience.changeExp(p, toRemove);
            if(toRemove < 0) toRemove *= -1;
            ItemStack xpOrb = new ItemStack(Material.SLIME_BALL,1);
            ItemMeta meta = xpOrb.getItemMeta();
            meta.setDisplayName(Util.format("&a&lExp Orb &7(Right Click)"));
            List<String> lore = new ArrayList<>();
            lore.add(Util.format("&6&lValue &8» " + "&7" + toRemove + " exp"));
            lore.add(Util.format("&6&lSummoner &8» " + "&7" + p.getPlayerProfile().getName()));
            meta.setLore(lore);
            xpOrb.setItemMeta(meta);
            p.getInventory().addItem(xpOrb);
            //handle withdraw msg
            if(SimpleXPOrbs2.getOptionsManager().isOn_withdraw_msg()){
                String msg = SimpleXPOrbs2.getMessageManager().getOn_withdraw();
                if(msg.contains("{orbXp}")) msg = msg.replace("{orbXp}", Integer.toString(toRemove));
                if(msg.contains("{playerXp}")) msg = msg.replace("{playerXp}", Integer.toString(Experience.getExp(p)));
                p.sendMessage(Util.format(msg));
            }
            return true;
        }
        for(String s1 : SimpleXPOrbs2.getMessageManager().getHelpMsg()){
            commandSender.sendMessage(Util.format(s1));
        }
        return true;
    }
}
