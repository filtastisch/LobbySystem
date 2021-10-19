package de.filtastisch.lobbysystem;

import de.carlos17kopra.capi.spigot.GuiManager;
import de.carlos17kopra.capi.spigot.SpigotConfig;
import de.filtastisch.lobbysystem.essentialmodule.cmd.BuildCommand;
import de.filtastisch.lobbysystem.essentialmodule.listener.*;
import de.filtastisch.lobbysystem.guis.cmd.NavigatorCommand;
import de.filtastisch.lobbysystem.guis.listener.InventoryListener;
import de.filtastisch.lobbysystem.hotbar.listener.HotBarClickListener;
import de.filtastisch.lobbysystem.hotbar.listener.JoinListener;
import de.filtastisch.lobbysystem.scoreboard.ScoreboardListener;
import de.filtastisch.lobbysystem.warpmodule.utils.ShortPosition;
import de.filtastisch.lobbysystem.warpmodule.cmd.SetSpawnCommand;
import de.filtastisch.lobbysystem.warpmodule.cmd.SpawnCommand;
import de.filtastisch.lobbysystem.warpmodule.cmd.WarpCommand;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Objects;

public final class LobbySystem extends JavaPlugin {

    /*
     * TODO:
     *   - Settings - DEV
     *       -> Sounds - DEV
     *       -> Animationen - DEV
     *       -> Spawnpoint - DEV
     *           -- ob am Spawn oder an der Disconnect-Location
     *       -> Uhrzeit - DEV
     *           -- Nacht oder Tag
     *   - Scoreboard - DEV
     *   - Cookieclicker - DEV
     *   - jn'R - DEV
     *   - enterhaken - DEV
     *   - shield - DEV
     */

    @Getter
    private SpigotConfig locationConfig;

    @Getter @Setter
    private SpigotConfig playerConfig;

    @Getter
    public static LobbySystem lobby;

    @Override
    public void onEnable() {
        lobby = this;
        this.registerConfigs();
        this.setConfigDefaults();
        this.loadValues();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this,"BungeeCord");
        GuiManager.registerGUIManager(this);
        this.registerEvents();
        this.registerCommands();
        this.loadPositions();
    }

    @Override
    public void onDisable() {
        this.safePositions();
    }

    public void registerConfigs(){
        this.locationConfig = new SpigotConfig(this, "locations.yml").register();
        this.locationConfig.registerDefaults();
    }

    public void loadValues(){

    }

    public void setConfigDefaults(){

    }

    public void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryListener(), this);
        pm.registerEvents(new HotBarClickListener(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new BuildListener(), this);
        pm.registerEvents(new CancelDamageListener(), this);
        pm.registerEvents(new RegionListener(), this);
        pm.registerEvents(new CancelBlockDamageListener(), this);
        pm.registerEvents(new ScoreboardListener(),this);
        pm.registerEvents(new WeatherChangeListener(), this);
        pm.registerEvents(new FoodChangeListener(), this);
    }

    public void registerCommands(){
        Objects.requireNonNull(this.getCommand("warp")).setExecutor(new WarpCommand());
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(this.getCommand("setspawn")).setExecutor(new SetSpawnCommand());
        Objects.requireNonNull(this.getCommand("navigator")).setExecutor(new NavigatorCommand());
        Objects.requireNonNull(this.getCommand("build")).setExecutor(new BuildCommand());
    }

    public void safePositions(){
        this.locationConfig.register();
        for (ShortPosition c : ShortPosition.all) {
            this.locationConfig.setLocation(c.getPosName(), c.getLocation());
        }
        this.locationConfig.save();
    }

    public void loadPositions(){
        for (String key : this.locationConfig.getConfigurationSection("").getKeys(false)) {
            ShortPosition.add(key, this.locationConfig.getLocation(key));
        }
    }

    public void sendPlayerToServer(Player p, String server){

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out= new DataOutputStream(b);
        try{
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
        p.sendPluginMessage(this, "BungeeCord" ,b.toByteArray());
    }

}
