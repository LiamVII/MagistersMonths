package io.github.liamvi.magistersmonths;

import io.github.liamvi.magistersmonths.time.TimeWarpTask;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class MagistersMonths extends JavaPlugin {

    @Override
    public void onEnable() {
        World world = this.getServer().getWorld(getConfigWorld());// will be able to simply pass the World object later
        if (world != null) {
            if (Boolean.TRUE.equals(world.getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE))) {
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                getLogger().info("Game Rule DO_DAYLIGHT_CYCLE in world " + world.getName() + " set to FALSE.");
            }
            TimeWarpTask task = new TimeWarpTask();
            task.runTaskTimer(this, 0, 6);
        } else {
            getLogger().info("ERROR: World defined in configuration is invalid. Day/night cycles will not work.");
        }






    }
    @Override
    public void onDisable() {

    }


    public String getConfigWorld() { // needs to be changed to return a World variable, taken from config file
        String worldName = "filler for now";
        return worldName;
    }
}
