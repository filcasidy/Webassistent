package Webassistent.commands.bundesliga;

import Webassistent.commands.ICommand;

import java.util.List;

public class ShowTableCommand implements ICommand {
    @Override
    public Object execute(List<String> para) {
        return "Show Table";
    }
    //
}
