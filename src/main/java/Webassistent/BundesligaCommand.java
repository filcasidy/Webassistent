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


    private final String userCommandMessage;

    BundesligaCommand(String userCommandMessage) {
        this.userCommandMessage = userCommandMessage;
    }

    public String getUserCommandMessage(){
        return userCommandMessage;
    }

    /**
     * Gives all user command messages.
     * @return list with all command messages
     */
    public static List<String> getAllUserCommandMessages(){
        List<String> listWithCommands = new ArrayList<>();
        for (BundesligaCommand value:BundesligaCommand.values()) {
            listWithCommands.add(value.getUserCommandMessage());
        }
        return  listWithCommands;
    }

}
