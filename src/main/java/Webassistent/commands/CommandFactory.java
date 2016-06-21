package Webassistent.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory with all commands.
 */
public class CommandFactory {

    private final HashMap<String, ICommand> commands;

    /**
     * Initial constructor.
     */
    public CommandFactory() {
        commands = new HashMap<>();
    }

    /**
     * Adds a new command with corresponding name.
     * @param name of the command
     * @param command implementation of the command
     */
    public void addCommand(String name, ICommand command) {
        commands.put(name, command);
    }

    /**
     * Executes the command depending on the given name and parameter.
     * @param name of the command
     * @param parameter {@link List} with parameters
     * @return the response of the command or null if the command does not exist
     */
    public Object executeCommand(String name,List<String> parameter) {
        if (commands.containsKey(name)) {
            return commands.get(name).execute(parameter);
        }
        return null;
    }

    /**
     * Gets all commands.
     * @return {@link List} with all commands
     */
    public List<String> getAllCommands() {
        List<String> rVal = new ArrayList<>();
        for (Map.Entry<String, ICommand> command : commands.entrySet()) {
            rVal.add(command.getKey());
        }
        return rVal;
    }

}
