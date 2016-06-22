package Webassistent.commands.bundesliga;

import Webassistent.commands.ICommand;
import Webassistent.commands.weather.WeatherUtils;
import Webassistent.utils.HtmlCreatorUtils;
import Webassistent.utils.JsonUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class ShowResultsCommand implements ICommand {
    private JSONArray jsonArray;
    List<List<String>> rows;


    @Override
    public Object execute(List<String> parameter) {
        rows= new ArrayList<>();
        int matchDay=1;
        int season=2015;
        String home = "";
        String away = "";
        String resultObject = "";


        if (parameter.size()>=2) {
            matchDay=Integer.valueOf(parameter.get(0));
            season=Integer.valueOf(parameter.get(1));
        }

        jsonArray = JsonUtils.readJsonFromUrlToJsonArray("http://www.openligadb.de/api/getmatchdata/bl1/"+ season + "/" + matchDay);

        for (int i = 0; i < jsonArray.length(); i++) {

            home=JsonUtils.getJson(new JSONObject(jsonArray.get(i).toString()), "Team1.TeamName").toString();
            away=JsonUtils.getJson(new JSONObject(jsonArray.get(i).toString()), "Team2.TeamName").toString();

            resultObject=JsonUtils.getJson(new JSONObject(jsonArray.get(i).toString()), "MatchResults").toString();

            rows.add(HtmlCreatorUtils.createListWithInformationValues(home, away, getResult(resultObject)));
        }

        return createReturnDocument(matchDay, season);
    }


    /**
     * This method get a String and return the extracted points for the both teams
     * @param resultObject
     * @return example: 0:0
     */
    private String getResult(String resultObject)
    {
        int rt1= resultObject.indexOf('}');         //Zweiter Eintrag = Endergebnis
        resultObject=resultObject.substring(rt1);
        String r1= resultObject.substring(16);      //16 Stellen entfernen
        String r2= resultObject.substring(31);      //31 Stellen entfernen
        return r2.substring(0,1) +" : "+r1.substring(0,1);
    }

    /**
     * This method get the matchday and the Season creates a Document and returns it
     * @param matchday
     * @param season
     * @return
     */
    private Document createReturnDocument(int matchday, int season)
    {
        Document responseDocument=HtmlCreatorUtils.createPanel("Bundesliga results from: matchday " +matchday
                                                                                        + " / season " + season );
        Element panelBody= responseDocument.getElementById("panelBody");

        panelBody.append(HtmlCreatorUtils.createTable(
                HtmlCreatorUtils.createListWithInformationValues(
                        BundesligaConstants.HOMETEAM,
                        BundesligaConstants.AWAYTEAM,
                        BundesligaConstants.RESULT
                ),rows,true).toString());

        return responseDocument;
    }

}
