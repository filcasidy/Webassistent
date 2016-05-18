package Webassistent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JsonParser {

    public Object convertJson(JSONArray jsonArrayFromQuery, int type) {

        System.out.println("Type" + type + " size" + jsonArrayFromQuery.size());
        long points1 = -1;
        long points2 = -1;

        switch (type) {
            case 0:
                String name1 = "";
                String name2 = "";
                List<String> response= new LinkedList<String>();
                long id = -1;
                int counter = 0;

                for (; counter < jsonArrayFromQuery.size(); counter++) {

                    JSONObject actualJsonObject = (JSONObject) jsonArrayFromQuery.get(counter);

                    id = (long) actualJsonObject.get("MatchID");

                    JSONObject teamJsonObject = (JSONObject) actualJsonObject.get("Team1");
                    name1 = (String) teamJsonObject.get("TeamName");

                    JSONObject team2JsonObject = (JSONObject) actualJsonObject.get("Team2");
                    name2 = (String) team2JsonObject.get("TeamName");

                    JSONArray resultJsonObject = (JSONArray) actualJsonObject.get("MatchResults");
                    Iterator i = resultJsonObject.iterator();

                    while (i.hasNext()) {
                        JSONObject slide = (JSONObject) i.next();
                        points1 = (long) slide.get("PointsTeam1");
                        points2 = (long) slide.get("PointsTeam2");
                    }

                    response.add(name1 + " - " + name2 + "\t " + points1 + " : " + points2 + "<br>");

                    for(int z=0; z<response.size();z++) {
                        String tmp = response.get(z).replace(',', ' ');
                        tmp = tmp.replace('[' , ' ');
                        response.set(z, tmp);
                    }
                }

                return response;

            default:
                return "undefined";
        }
    }
}

