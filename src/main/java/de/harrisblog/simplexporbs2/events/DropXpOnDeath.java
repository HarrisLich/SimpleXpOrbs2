package de.harrisblog.simplexporbs2.events;

import de.harrisblog.simplexporbs2.SimpleXPOrbs2;
import de.harrisblog.simplexporbs2.util.Experience;
import de.harrisblog.simplexporbs2.util.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DropXpOnDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        if(SimpleXPOrbs2.getOptionsManager().isDrop_on_death()){
            event.setDroppedExp(0);
            if(!(event.getEntity() instanceof Player)) return;
            Player p = (Player) event.getEntity();
            int exp = Experience.getExp(p);
            //XPORB ITEM
            if(exp != 0){
                ItemStack xpOrb = new ItemStack(Material.SLIME_BALL,1);
                ItemMeta meta = xpOrb.getItemMeta();
                meta.setDisplayName(Util.format("&a&lExp Orb &7(Right Click)"));
                List<String> lore = new ArrayList<>();
                lore.add(Util.format("&6&lValue &8» " + "&7" + exp + " exp"));
                lore.add(Util.format("&6&lSummoner &8» " + "&7" + p.getPlayerProfile().getName()));
                meta.setLore(lore);
                xpOrb.setItemMeta(meta);
                p.getWorld().dropItemNaturally(p.getLocation(), xpOrb);
                if(SimpleXPOrbs2.getOptionsManager().isOn_death_msg()){
                    String death_msg = SimpleXPOrbs2.getMessageManager().getOn_death();
                    if(death_msg.contains("{playerXp}")) death_msg = death_msg.replace("{playerXp}", Integer.toString(exp));
                    p.sendMessage(Util.format(death_msg));
                }
            }else{
                if(SimpleXPOrbs2.getOptionsManager().isOn_death_msg()){
                    String death_msg = SimpleXPOrbs2.getMessageManager().getOn_death();
                    if(death_msg.contains("{playerXp}")) death_msg = death_msg.replace("{playerXp}", Integer.toString(exp));
                    p.sendMessage(Util.format(death_msg));
                }
            }
            //handle death msg

        }
    }
}
