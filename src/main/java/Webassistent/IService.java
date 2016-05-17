package Webassistent;

import java.util.List;

/**
 * Methods which each service should have.
 */
public interface IService {

    /**
     * Gives all user command messages.
     *
     * @return list with all command messages
     */
    List<String> getCommands();

    /**
     * Checks whether the service has the given userCommand.
     * @param userCommand which will be typed in as a command.
     * @return true if the service has the given userCommand, else false
     */
    boolean hasCommand(String userCommand);


    /**
     * Depending on user command gets the service responese.
     * @param userCommand which will be typed in as a command.
     * @return an object which contains the response
     */
    Object getServiceResponse(String userCommand);
}
