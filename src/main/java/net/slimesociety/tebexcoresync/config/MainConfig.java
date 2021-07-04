package net.slimesociety.tebexcoresync.config;

import net.slimesociety.tebexcoresync.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class MainConfig {
    private final FileConfiguration config;
    private final Main plugin;

    private String url;
    private String key;

    public MainConfig(Main plugin){
        this.plugin = plugin;
        config = plugin.getConfig();

        config.addDefault("apikey","FillThisWithYourTebexSecret");
        config.addDefault("url","https://plugin.tebex.io");
        config.options().copyDefaults(true);

        plugin.saveConfig();

        key = config.getString("apikey");
        url = config.getString("url");
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    public boolean ReloadConfig(){
        try{
            plugin.reloadConfig();
            key = config.getString("apikey");
            url = config.getString("url");

            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
