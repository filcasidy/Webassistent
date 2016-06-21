package Webassistent.commands.bundesliga;

import Webassistent.JsonConnector;
import Webassistent.commands.ICommand;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ShowResultsCommand implements ICommand {

    @Override
    public Object execute(List<String> parameter) {
        JsonConnector connector = new JsonConnector();
        JSONArray jsonArray = connector.readJsonFromUrlToJsonArray("http://www.openligadb.de/api/getmatchdata/bl1/2015/34");

        for (int i = 0; i < jsonArray.length(); i++) {
            System.err.println(connector.getJson(new JSONObject(jsonArray.get(i).toString()),"Team1.TeamName"));
        }
        return null;
    }

}
