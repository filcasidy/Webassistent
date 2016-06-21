package Webassistent.services;

import Webassistent.commands.CommandFactory;
import Webassistent.commands.weather.ShowTodayWeatherCommand;
import Webassistent.commands.weather.ShowWeatherForNextDaysCommand;

import java.util.List;

public class WeatherService implements IService {

    private String SHOW_TODAYS_WEATHER = "Show todays weather";
    private String SHOW_WEATHER_FOR_NEXT_DAYS = "Show weather for next days";


    CommandFactory commandFactory = new CommandFactory();

    public WeatherService() {
        commandFactory.addCommand(SHOW_TODAYS_WEATHER, new ShowTodayWeatherCommand());
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
        if (SHOW_TODAYS_WEATHER.equalsIgnoreCase(userCommand)) {
            return commandFactory.executeCommand(SHOW_TODAYS_WEATHER, parameter);
        } else if (SHOW_WEATHER_FOR_NEXT_DAYS.equalsIgnoreCase(userCommand)) {
            return commandFactory.executeCommand(SHOW_WEATHER_FOR_NEXT_DAYS, parameter);
        } else {
            return null;
        }
    }
}
