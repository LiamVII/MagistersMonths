package io.github.liamvi.magistersmonths;

import io.github.liamvi.magistersmonths.calendar.HarptosCalendar;
import io.github.liamvi.magistersmonths.command.CalendarCommand;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;
import java.util.Objects;


public class MagistersMonths extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        World world = this.getServer().getWorld(getConfigWorld());
        // First we check to make sure the daylight cycle is disabled, and if not, we disable it. We need full control.
        if (world != null) {
            if (Boolean.TRUE.equals(world.getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE))) {
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                getLogger().info("Game Rule DO_DAYLIGHT_CYCLE in world " + world.getName() + " set to FALSE.");
            }
        } else {
            getLogger().info("ERROR: World defined in configuration is invalid. Day/night cycles will not work.");
        }
        // Checking if epoch date exists, and if not, auto-generating it as the current time in milliseconds.
        if (Objects.equals(this.getConfig().getString("epoch-time"), "blank")) { // better way to do this?
            Date today = new Date();
            this.getConfig().set("epoch-time", Long.toString(today.getTime()));
        }
        getCommand("calendar").setExecutor(new CalendarCommand(this));
    }
    @Override
    public void onDisable() {

    }

    public String getConfigWorld() {
        return this.getConfig().getString("world");
    }

    public long getConfigEpoch() {
        String epochTime = this.getConfig().getString("epoch-time");
        if (epochTime != null) {
            return Long.parseLong(epochTime);
        } else { // I will probably delete this once the epoch-time auto-generation is done.
            getLogger().info("You have not defined an epoch-time in the config.yml!");
            throw new NullPointerException();
        }
    }
}
