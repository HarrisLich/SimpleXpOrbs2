package de.harrisblog.simplexporbs2.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XpCommandTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
        if(commandSender instanceof Player){
            if(command.getName().equalsIgnoreCase("xporb")){
                if(args.length == 1){
                    //xporb _____
                    list.add("all");
                    list.add("[amount]");
                    Collections.sort(list);
                    return list;
                }else if(args.length == 2){
                    return list;
                }
            }
        }
        return null;
    }
}
