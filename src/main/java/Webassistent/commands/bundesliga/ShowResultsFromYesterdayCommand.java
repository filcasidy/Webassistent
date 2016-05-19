package Webassistent.commands.bundesliga;

import Webassistent.JsonConnector;
import Webassistent.JsonParser;
import Webassistent.commands.ICommand;

public class ShowResultsFromYesterdayCommand implements ICommand {

    @Override
    public Object execute() {
        JsonConnector connector = new JsonConnector();
        JsonParser parser = new JsonParser();
        return parser.convertJson(connector.getJsonFromURL("http://www.openligadb.de/api/getmatchdata/bl1/2015/34"), 0);
    }
}
