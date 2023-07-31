package io.github.liamvi.magistersmonths.tasks;

import java.time.LocalTime;
import io.github.liamvi.magistersmonths.MagistersMonths;
import io.github.liamvi.magistersmonths.calendar.HarptosCalendar;
import io.github.liamvi.magistersmonths.calendar.HarptosDate;
import io.github.liamvi.magistersmonths.calendar.HarptosDateFormat;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;


public class WorldTimeAdvancerTask extends BukkitRunnable {
    private final World world;
    private final HarptosDate date;
    private final HarptosCalendar calendar;

    // Will probably need this in run() so the date objects refresh
    public WorldTimeAdvancerTask(MagistersMonths plugin, HarptosDate date) {
        this.world = plugin.getServer().getWorld(plugin.getConfigWorld());
        this.date = date;
        this.calendar = new HarptosCalendar(date, plugin);
    }

    // Day lasts 4 hours, sunrise/sunset are roughly an hour, night is 3 hours

    @Override
    public void run() {
        LocalTime localTime = calendar.getCalendarTime();
        // Below isn't perfect, it will at max be able to represent up to the 40th minute of an hour - enough for us though.
        int time = (localTime.getHour() * 1000) + (localTime.getMinute() * 10);
        world.setTime(time);
    }
}
