package io.github.liamvi.magistersmonths;

import io.github.liamvi.magistersmonths.calendar.HarptosCalendar;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;


public class MagistersMonths extends JavaPlugin {

    @Override
    public void onEnable() {
        World world = this.getServer().getWorld(getConfigWorld());// will be able to simply pass the World object later
        // First we check to make sure the daylight cycle is disabled, and if not, we disable it. We need full control.
        if (world != null) {
            if (Boolean.TRUE.equals(world.getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE))) {
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                getLogger().info("Game Rule DO_DAYLIGHT_CYCLE in world " + world.getName() + " set to FALSE.");
            }
        } else {
            getLogger().info("ERROR: World defined in configuration is invalid. Day/night cycles will not work.");
        }
    }
    @Override
    public void onDisable() {

    }


    public String getConfigWorld() { // needs to be changed to return a World variable, taken from config file
        return "filler";
    }
}
