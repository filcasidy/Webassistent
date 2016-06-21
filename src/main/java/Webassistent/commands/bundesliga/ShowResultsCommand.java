package Webassistent.commands.bundesliga;

import Webassistent.commands.ICommand;

import java.util.List;

public class ShowResultsCommand implements ICommand {

    @Override
    public Object execute(List<String> parameter) {
//        JSONArray jsonArray = JsonUtils.readJsonFromUrlToJsonArray("http://www.openligadb.de/api/getmatchdata/bl1/2015/34");
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            System.err.println(JsonUtils.getJson(new JSONObject(jsonArray.get(i).toString()),"Team1.TeamName"));
//        }
        return null;
    }

}
