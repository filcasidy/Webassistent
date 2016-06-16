package Webassistent.commands.bundesliga;

import Webassistent.JsonConnector;
import Webassistent.JsonParser;
import Webassistent.commands.ICommand;

import java.util.LinkedList;
import java.util.List;

public class ShowResultsCommand implements ICommand {

    @Override
    public Object execute(List<String> para) {
        List<String> keyList = new LinkedList<String>();
        keyList.add("MatchID");
        JsonConnector connector = new JsonConnector();
        return connector.readJsonFromUrl("http://www.openligadb.de/api/getmatchdata/bl1/2015/34", keyList);
    }

}
