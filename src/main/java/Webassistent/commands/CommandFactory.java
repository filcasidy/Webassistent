package Webassistent.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandFactory {

    private final HashMap<String, ICommand> commands;

    public CommandFactory() {
        commands = new HashMap<>();
    }

    public void addCommand(String name, ICommand command) {
        commands.put(name, command);
    }

    public Object executeCommand(String name) {
        if (commands.containsKey(name)) {
            return commands.get(name).execute();
        }
        return null;
    }

    public List<String> getAllCommands() {
        List<String> rVal = new ArrayList<>();
        for (Map.Entry<String, ICommand> command : commands.entrySet()) {
            rVal.add(command.getKey());
        }
        return rVal;
    }

}
