package de.harrisblog.simplexporbs2.util;

import org.bukkit.ChatColor;

public class Util {

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String format(String format){
        return ChatColor.translateAlternateColorCodes('&', format);
    }

}
