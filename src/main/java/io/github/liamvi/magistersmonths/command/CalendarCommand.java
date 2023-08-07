package io.github.liamvi.magistersmonths.command;

import com.sun.jdi.CharType;
import io.github.liamvi.magistersmonths.MagistersMonths;
import io.github.liamvi.magistersmonths.calendar.HarptosCalendar;
import io.github.liamvi.magistersmonths.calendar.HarptosDate;
import io.github.liamvi.magistersmonths.calendar.HarptosDateFormat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CalendarCommand implements CommandExecutor {

    private MagistersMonths plugin;

    public CalendarCommand(MagistersMonths plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        HarptosDate date = new HarptosDate(plugin);
        HarptosCalendar calendar = new HarptosCalendar(date, plugin);
        HarptosDateFormat format = new HarptosDateFormat(calendar);
        commandSender.sendMessage(format.getFormattedDate());
        return false;
    }
}
