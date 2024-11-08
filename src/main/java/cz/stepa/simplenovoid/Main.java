package cz.stepa.simplenovoid;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class Main extends PluginBase implements Listener {
    Config c;
    int teleportCoord;
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getLogger().info("SimpleNoVoid enabled!");
        this.saveDefaultConfig();
        this.c = this.getConfig();
        teleportCoord = c.getInt("floorCoord");
    }

    @EventHandler
    public void onVoid(PlayerMoveEvent e) {
        if (e.getTo().getFloorY() < teleportCoord && this.c.getBoolean("enable")) {
            Level world = this.getServer().getLevelByName(this.c.getString("world"));
            Position position = this.getServer().getLevelByName(this.c.getString("world")).getSafeSpawn();
            int x = position.getFloorX();
            int y = position.getFloorY();
            int z = position.getFloorZ();
            e.getPlayer().teleport(new Position((double)x, (double)y, (double)z, world));
            if(c.getString("savedFromVoid").equals(" ")) return;
            else {
                e.getPlayer().sendMessage(this.c.getString("savedFromVoid"));
                }
            }
        }
    }