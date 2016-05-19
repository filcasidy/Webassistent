package Webassistent.services;

import Webassistent.services.IService;

import java.util.List;

public class WeatherService implements IService {
    @Override
    public List<String> getCommands() {
        return null;
    }

    @Override
    public boolean hasCommand(String userCommand) {
        return false;
    }

    @Override
    public Object getServiceResponse(String userCommand) {
//        if (BundesligaCommand.SHOW_RESULTS_FROM_YESTERDAY.getCommand().equalsIgnoreCase(userCommand)) {
//            return "Show results";
//        }else if (BundesligaCommand.SHOW_TABLE.getCommand().equalsIgnoreCase(userCommand)){
//            return "Show table";
//        }else if (BundesligaCommand.SHOW_TEAM_INFORMATION.getCommand().equalsIgnoreCase(userCommand)){
//            return "Show team";
//        }else {
            return null;
    }
}
