package Webassistent.services;

import Webassistent.commands.CommandFactory;
import Webassistent.commands.news.ShowTestNewsCommand;

import java.util.List;

public class NewsService extends Service {

	private String SHOW_NEWS = "Show news";

	CommandFactory commandFactory = new CommandFactory();

	public NewsService() {
		commandFactory.addCommand(SHOW_NEWS, new ShowTestNewsCommand());
	}

	@Override
	public List<String> getCommands() {
		return commandFactory.getAllCommands();
	}

	@Override
	public boolean hasCommand(String userCommand) {
		boolean rVal = false;
		for (String command : getCommands()) {
			if (command.equalsIgnoreCase(userCommand)) {
				rVal = true;
			}
		}
		return rVal;
	}

	@Override
	public Object getServiceResponse(String userCommand, List<String> parameter) {
		if (SHOW_NEWS.equalsIgnoreCase(userCommand)) {
			return commandFactory.executeCommand(SHOW_NEWS, parameter);
		} else {
			return null;
		}
	}
}
