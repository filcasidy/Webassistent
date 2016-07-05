package Webassistent.services;

import Webassistent.commands.CommandFactory;
import Webassistent.commands.weather.ShowCurrentWeatherCommand;
import Webassistent.commands.weather.ShowWeatherForNextDaysCommand;

import java.util.List;

/**
 * Weather service containing weather commands.
 */
public class WeatherService extends Service {

    private String SHOW_CURRENT_WEATHER = "Show current weather";
    private String SHOW_WEATHER_FOR_NEXT_DAYS = "Show weather for next days";


    CommandFactory commandFactory = new CommandFactory();

    public WeatherService() {
        commandFactory.addCommand(SHOW_CURRENT_WEATHER, new ShowCurrentWeatherCommand());
        commandFactory.addCommand(SHOW_WEATHER_FOR_NEXT_DAYS, new ShowWeatherForNextDaysCommand());
    }

    @Override
    public List<String> getCommands() {
        return commandFactory.getAllCommands();
    }

    @Override
    public boolean hasCommand(String userCommand) {
        boolean rVal = false;
        for (String command : getCommands()) {
            if (command.equalsIgnoreCase(userCommand)) {
                rVal = true;
            }
        }
        return rVal;
    }

    @Override
    public Object getServiceResponse(String userCommand, List<String> parameter) {
        if (SHOW_CURRENT_WEATHER.equalsIgnoreCase(userCommand)) {
            return commandFactory.executeCommand(SHOW_CURRENT_WEATHER, parameter);
        } else if (SHOW_WEATHER_FOR_NEXT_DAYS.equalsIgnoreCase(userCommand)) {
            return commandFactory.executeCommand(SHOW_WEATHER_FOR_NEXT_DAYS, parameter);
        } else {
            return null;
        }
    }
}
