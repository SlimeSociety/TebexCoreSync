package net.slimesociety.tebexcoresync.commands;

import net.slimesociety.tebexcoresync.Main;
import net.slimesociety.tebexcoresync.util.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class ApplyToTebex implements CommandExecutor {
    private final Main plugin;

    public ApplyToTebex(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final boolean isPlayer;

        isPlayer = sender instanceof Player;

        if(!plugin.isInitComplete()){
            if(isPlayer){
                Messages.decideWhereToSend("&cFailed to initialize the plugin", (Player) sender);
                Messages.decideWhereToSend("&cCheck if your Tebex secret is inserted into the config file.", (Player) sender);
            }else{
                Messages.decideWhereToSend("&cFailed to initialize the plugin", Level.SEVERE);
                Messages.decideWhereToSend("&cCheck if your Tebex secret is inserted into the config file.", Level.SEVERE);
            }
            return true;
        }

        if (isPlayer && sender.hasPermission("TebexCoreSync.use")) {
            Messages.decideWhereToSend("&cYou don't have permission to use this command!", (Player) sender);
            return true;
        }

        if(args.length==1 && args[0].equalsIgnoreCase("reload")){
            if(plugin.getConfigInstance().ReloadConfig()){
                if(isPlayer)
                    Messages.decideWhereToSend("&aPlugin reloaded", (Player) sender);
                Messages.decideWhereToSend("&aPlugin reloaded!", Level.INFO);
            }else{
                if(isPlayer)
                    Messages.decideWhereToSend("&cError on reloading", (Player) sender);
                Messages.decideWhereToSend("&cError on reloading!", Level.SEVERE);
            }
            return true;
        }

        if(args.length != 2){
            if(isPlayer)
                Messages.decideWhereToSend("&aUsage /applytotebex <rankid> <username>", (Player) sender);
            else
                Messages.decideWhereToSend("&aUsage /applytotebex <rankid> <username>!", Level.INFO);
            return true;
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                boolean post = false;

                try{
                    post = plugin.getServiceInstance().post( args[1], Integer.parseInt(args[0]) );

                }catch (Exception e){
                    if(isPlayer)
                        Messages.decideWhereToSend("&cThere was an error with applying the rank to "+args[1], (Player) sender);
                    Messages.decideWhereToSend( "&cThere was an error with applying the rank to "+args[1], Level.SEVERE);
                    e.printStackTrace();
                }
                if(post){
                    if(isPlayer)
                        Messages.decideWhereToSend("&aSuccessfully added a rank to "+ args[1] +"!", (Player) sender);
                    Messages.decideWhereToSend("&aSuccessfully added a rank to "+ args[1] +"!", Level.INFO);
                }else{
                    if(isPlayer)
                        Messages.decideWhereToSend("&cFailed to add the rank!", (Player) sender);
                    Messages.decideWhereToSend("&cFailed to add a rank " + args[0] + " to player " + args[1] + ". Please check if the api key is set correctly. The api key is the same as the secret in Buycraft config. &aOr the player already has this rank.", Level.SEVERE);
                }
            }

        }.runTaskAsynchronously(plugin);

        return true;
    }
}

