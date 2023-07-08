package io.github.liamvi.magistersmonths.time;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;

public class DayNightHandler {

    public void modifyDayNightCycle(String worldName) {
        World world = Bukkit.getServer().getWorld(worldName);
        if (world != null) {
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false); // disable DN cycle so we can control it at will
        } else {
            return;
        }



    }

}
