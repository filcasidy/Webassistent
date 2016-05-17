package Webassistent;

import java.util.List;

public class BundesligaService implements IService {

    @Override
    public List<String> getCommands() {

        return BundesligaCommand.getAllUserCommandMessages();
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
        if (BundesligaCommand.SHOW_RESULTS_FROM_YESTERDAY.getCommand().equalsIgnoreCase(userCommand)) {
            return "Show results";
        }else if (BundesligaCommand.SHOW_TABLE.getCommand().equalsIgnoreCase(userCommand)){
            return "Show table";
        }else if (BundesligaCommand.SHOW_TEAM_INFORMATION.getCommand().equalsIgnoreCase(userCommand)){
            return "Show team";
        }else {
            return null;
        }
    }
}
