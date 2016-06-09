package Webassistent.commands.bundesliga;

import Webassistent.JsonConnector;
import Webassistent.JsonParser;
import Webassistent.commands.ICommand;

public class ShowResultsCommand implements ICommand {

    @Override
    public Object execute() {
        JsonConnector connector = new JsonConnector();
        JsonParser parser = new JsonParser();
        return parser.convertJson(connector.readJsonFromUrl("http://www.openligadb.de/api/getmatchdata/bl1/2015/34"),0);
    }
}
