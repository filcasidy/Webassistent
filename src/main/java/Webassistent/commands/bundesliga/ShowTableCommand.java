package Webassistent.commands.bundesliga;

import java.util.List;

import Webassistent.commands.ICommand;

public class ShowTableCommand implements ICommand {
    @Override
    public Object execute(List<String> para) {
        return "Show Table";
    }
}
