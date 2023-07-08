package io.github.liamvi.magistersmonths.time;

import io.github.liamvi.magistersmonths.MagistersMonths;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeWarpTask extends BukkitRunnable {

    private MagistersMonths plugin;
    int interval = 0;
    World world = plugin.getServer().getWorld(plugin.getConfigWorld());

    @Override
    public void run() {
        interval = interval + 6;
        if (world.getTime() == 24000) {
            world.setTime(0);
        }
        else if (world.getTime() <= 12000) {
            if (interval == 30) {
                world.setTime(world.getTime() + 1);
                interval = 0;
            }
        }
        else if (world.getTime() > 12000) {
            if (interval == 18) {
                world.setTime(world.getTime() + 1);
                interval = 0;
            }
        }
    }
}
