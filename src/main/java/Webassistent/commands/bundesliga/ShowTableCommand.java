package Webassistent.commands.bundesliga;

import Webassistent.commands.ICommand;

public class ShowTableCommand implements ICommand {
    @Override
    public Object execute() {
        return "Show Table";
    }
}
