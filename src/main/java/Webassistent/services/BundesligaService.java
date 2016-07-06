package Webassistent.services;

import Webassistent.commands.CommandFactory;
import Webassistent.commands.bundesliga.ShowResultsCommand;

import java.util.List;

public class BundesligaService extends Service {

    private String SHOW_TABLE = "Show actual table information";
    private String SHOW_RESULTS = "Show results from";
    private String SHOW_TEAM_INFORMATION = "Show team information";

    CommandFactory commandFactory = new CommandFactory();

    public BundesligaService() {
        //commandFactory.addCommand(SHOW_TABLE, new ShowTableCommand());
        commandFactory.addCommand(SHOW_RESULTS, new ShowResultsCommand());
        //commandFactory.addCommand(SHOW_TEAM_INFORMATION, new ShowTeamInformationCommand());
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
        if (SHOW_RESULTS.equalsIgnoreCase(userCommand)) {
            return commandFactory.executeCommand(SHOW_RESULTS, parameter);
        } else if (SHOW_TABLE.equalsIgnoreCase(userCommand)) {
            return commandFactory.executeCommand(SHOW_TABLE, parameter);
        } else if (SHOW_TEAM_INFORMATION.equalsIgnoreCase(userCommand)) {
            return commandFactory.executeCommand(SHOW_TEAM_INFORMATION, parameter);
        } else {
            return null;
        }
    }
}
