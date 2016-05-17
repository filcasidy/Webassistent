package Webassistent;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeration for the bundesliga commands.
 */
public enum BundesligaCommand {
    SHOW_TABLE("Show actual table information"),
    SHOW_RESULTS_FROM_YESTERDAY("Show results from yesterday"),
    SHOW_TEAM_INFORMATION("Show team information");


    private final String command;

    BundesligaCommand(String command) {
        this.command = command;
    }

    public String getCommand(){
        return command;
    }

    /**
     * Gives all user command messages.
     * @return list with all command messages
     */
    public static List<String> getAllUserCommandMessages(){
        List<String> listWithCommands = new ArrayList<>();
        for (BundesligaCommand value:BundesligaCommand.values()) {
            listWithCommands.add(value.getCommand());
        }
        return  listWithCommands;
    }

}
