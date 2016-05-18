package Webassistent.services;

import Webassistent.commands.CommandFactory;
import Webassistent.commands.bundesliga.ShowResultsFromYesterdayCommand;
import Webassistent.commands.bundesliga.ShowTableCommand;
import Webassistent.commands.bundesliga.ShowTeamInformationCommand;
import Webassistent.services.Service;

import java.util.List;

public class BundesligaService extends Service {

    private String SHOW_TABLE= "Show actual table information";
    private String SHOW_RESULTS_FROM_YESTERDAY= "Show results from yesterday";
    private String SHOW_TEAM_INFORMATION= "Show team information";

    CommandFactory commandFactory = new CommandFactory();

    public BundesligaService (){
        commandFactory.addCommand(SHOW_TABLE, new ShowTableCommand());
        commandFactory.addCommand(SHOW_RESULTS_FROM_YESTERDAY, new ShowResultsFromYesterdayCommand());
        commandFactory.addCommand(SHOW_TEAM_INFORMATION, new ShowTeamInformationCommand());
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
    public Object getServiceResponse(String userCommand) {
        if (SHOW_RESULTS_FROM_YESTERDAY.equalsIgnoreCase(userCommand)) {
            return commandFactory.executeCommand(SHOW_RESULTS_FROM_YESTERDAY);
        }else if (SHOW_TABLE.equalsIgnoreCase(userCommand)){
            return commandFactory.executeCommand(SHOW_TABLE);
        }else if (SHOW_TEAM_INFORMATION.equalsIgnoreCase(userCommand)){
            return commandFactory.executeCommand(SHOW_TEAM_INFORMATION);
        }else {
            return null;
        }
    }
}
