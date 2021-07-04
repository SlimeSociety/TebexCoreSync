package net.slimesociety.tebexcoresync.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public final class Messages {
    private static Messages INSTANCE;

    public static Messages getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new Messages();
        }

        return INSTANCE;
    }

    public static void decideWhereToSend(String m, Player sender){
        sendMessage(sender, m);

    }

    public static void decideWhereToSend(String m, Level l){
        sendToConsole(l, m);
    }

    public static void sendMessage(Player p, String m){
        p.sendMessage(translateAlt("&7[&aTebexCoreSync&7] " + m));
    }

    public static void sendToConsole(Level l, String m){
        Bukkit.getServer().getLogger().log(l, translateAlt("&7[&aTebexCoreSync&7] " + m));
    }

    public static String translateAlt(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
