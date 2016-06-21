package Webassistent.services;

import Webassistent.commands.CommandFactory;
import Webassistent.commands.weather.ShowTodayWeatherCommand;

import java.util.List;

public class WeatherService implements IService {

    private String SHOW_TODAYS_WEATHER = "Show todays weather";


    CommandFactory commandFactory = new CommandFactory();

    public WeatherService() {
        commandFactory.addCommand(SHOW_TODAYS_WEATHER, new ShowTodayWeatherCommand());
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
        }  else {
            return null;
        }
    }
}
