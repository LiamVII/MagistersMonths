package io.github.liamvi.magistersmonths.tasks;

import io.github.liamvi.magistersmonths.MagistersMonths;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeWarpTask extends BukkitRunnable {

    private final World world;
    private int interval;

    public TimeWarpTask (MagistersMonths magistersMonths) {
        String worldName = magistersMonths.getConfigWorld();
        world = magistersMonths.getServer().getWorld(worldName);
        interval = 0;
    }

    // Probably better to build the calendar/time system first - this doesn't feel like the most efficient solution.
    // It would probably work, but I expect there are better ways to do it.

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
