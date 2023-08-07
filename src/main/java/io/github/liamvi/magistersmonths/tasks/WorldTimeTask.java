package io.github.liamvi.magistersmonths.tasks;

import java.time.LocalTime;
import java.util.HashMap;

import io.github.liamvi.magistersmonths.MagistersMonths;
import io.github.liamvi.magistersmonths.calendar.HarptosCalendar;
import io.github.liamvi.magistersmonths.calendar.HarptosDate;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;


public class WorldTimeTask extends BukkitRunnable {
    private World world;
    private HarptosDate date;
    private HarptosCalendar calendar;
    private MagistersMonths plugin;

    // Map the hours of the day to the tick value in-game.
    private static final HashMap<Integer, Integer> hourTicks = new HashMap<Integer, Integer>() {{
        put(0, 18000);
        put(1, 19000);
        put(2, 20000);
        put(3, 21000);
        put(4, 22000);
        put(5, 23000);
        put(6, 24000);
        put(7, 1000);
        put(8, 2000);
        put(9, 3000);
        put(10, 4000);
        put(11, 5000);
        put(12, 6000);
        put(13, 7000);
        put(14, 8000);
        put(15, 9000);
        put(16, 10000);
        put(17, 11000);
        put(18, 12000);
        put(19, 13000);
        put(20, 14000);
        put(21, 15000);
        put(22, 16000);
        put(23, 17000);
    }};

    public WorldTimeTask(MagistersMonths plugin) {
        this.plugin = plugin;
    }

    // Day lasts 4 hours, sunrise/sunset are roughly an hour, night is 3 hours

    @Override
    public void run() {
        this.world = plugin.getServer().getWorld(plugin.getConfigWorld());
        this.date = new HarptosDate(plugin);
        this.calendar = new HarptosCalendar(date, plugin);
        LocalTime localTime = calendar.getCalendarTime();
        // Below isn't perfect, it will at max be able to represent up to the 40th minute of an hour - enough for us though.
        int time = hourTicks.get(localTime.getHour()) + (localTime.getMinute() * 10);
        world.setTime(time);
    }
}
