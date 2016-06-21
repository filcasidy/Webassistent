package Webassistent.commands;

import java.util.List;

/**
 * Methods which each command should have.
 */
public interface ICommand {

    /**
     * Executes the command.
     */
    Object execute(List<String> parameter);
}
