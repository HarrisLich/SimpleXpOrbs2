package de.harrisblog.simplexporbs2.events;

import de.harrisblog.simplexporbs2.SimpleXPOrbs2;
import de.harrisblog.simplexporbs2.util.Experience;
import de.harrisblog.simplexporbs2.util.Util;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class OrbInteractEvent implements Listener {
    @EventHandler
    public void onOrbInteract(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player p = event.getPlayer();
            ItemStack item = p.getItemInHand();
            if(item != null && item.hasItemMeta()){
                if(item.getItemMeta().hasLore()){
                    if(item.getItemMeta().getLore().toString().contains(Util.format("&6&lValue &8» " + "&7"))){
                        //Item is a xp orb
                        List<String> lore = item.getItemMeta().getLore();
                        int xp = 0;
                        for(String s : lore){
                            if(s.contains(Util.format("&6&lValue &8» " + "&7"))){
                                String[] strs = s.split(" ");
                                for(String str : strs){
                                    String c = str.substring(2);
                                    if(Util.isNumeric(c)){
                                        xp = Integer.parseInt(c);
                                        Experience.changeExp(p, xp);
                                        if(item.getAmount() > 1){
                                            item.setAmount(item.getAmount() - 1);
                                        }else{
                                            p.getInventory().clear(p.getInventory().getHeldItemSlot());
                                        }
                                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 10);
                                        //handle redeem msg
                                        if(SimpleXPOrbs2.getOptionsManager().isOn_redeem_msg()){
                                            String msg = SimpleXPOrbs2.getMessageManager().getOn_redeem();
                                            if(msg.contains("{orbXp}")){
                                                msg = msg.replace("{orbXp}", Integer.toString(xp));
                                            }
                                            p.sendMessage(Util.format(msg));
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
