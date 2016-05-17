package Webassistent;

import java.util.List;

public class BundesligaService implements IService{


    @Override
    public List<String> getCommands() {

        return BundesligaCommand.getAllUserCommandMessages();
    }

    @Override
    public boolean hasCommand(String userCommand) {
        boolean rVal = false;
        for (String command: getCommands()) {
            if (command.equalsIgnoreCase(userCommand)){
                rVal= true;
            }
        }
        return  rVal;
    }
}
