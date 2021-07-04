package net.slimesociety.tebexcoresync;

import net.slimesociety.tebexcoresync.commands.ApplyToTebex;
import net.slimesociety.tebexcoresync.config.MainConfig;
import net.slimesociety.tebexcoresync.util.Messages;
import net.slimesociety.tebexcoresync.util.Service;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;


public class Main extends JavaPlugin {
    private MainConfig configInstance;
    private Service serviceInstance;
    private boolean initComplete;


    @Override
    public void onEnable(){
        initComplete = false;
        configInstance = new MainConfig(this);
        serviceInstance = new Service(this);

        this.getCommand("applytotebex").setExecutor(new ApplyToTebex(this));

        try{
            if(serviceInstance.postTest()){
                initComplete = true;
                Messages.decideWhereToSend("&aPlugin enabled", Level.INFO);
            }else{
                Messages.decideWhereToSend("&cFailed to initialize the plugin", Level.SEVERE);
                Messages.decideWhereToSend("&cCheck if your Tebex secret is inserted into the config file.", Level.SEVERE);
            }
        } catch (IOException e) {
            Messages.decideWhereToSend("&cFailed to initialize the plugin", Level.SEVERE);
            Messages.decideWhereToSend("&cProblem with contacting TEBEX.", Level.SEVERE);
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable(){
        configInstance = null;
        serviceInstance = null;
    }

    public MainConfig getConfigInstance() {
        return configInstance;
    }
    public Service getServiceInstance() {
        return serviceInstance;
    }

    public boolean isInitComplete() {
        return initComplete;
    }
}
