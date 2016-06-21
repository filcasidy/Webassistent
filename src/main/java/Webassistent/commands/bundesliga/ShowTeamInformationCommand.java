package Webassistent.commands.bundesliga;

import Webassistent.commands.ICommand;

import java.util.List;

public class ShowTeamInformationCommand implements ICommand {
    @Override
    public Object execute(List<String> para) {
        return "Show Team Information";
    }
}
