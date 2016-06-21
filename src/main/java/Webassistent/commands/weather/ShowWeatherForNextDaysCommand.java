package Webassistent.commands.weather;

import Webassistent.commands.ICommand;
import Webassistent.utils.JsonUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Command to show weather for the next days.
 */
public class ShowWeatherForNextDaysCommand implements ICommand {
    private JSONArray jsonArray;
    private JSONObject jsonObject;

    public ShowWeatherForNextDaysCommand() {
        JSONObject completeJsonObject = JsonUtils.readJsonFromUrlToJsonObject(WeatherUtils.getUrlOfGivenCity(WeatherConstants.DEFAULT_CITY_BREMEN));
        System.err.println(((ArrayList) JsonUtils.getJson(completeJsonObject, "query.results.channel.item.forecast")).size());
    }

    @Override
    public Object execute(List<String> parameter) {
        if (!parameter.isEmpty()) {
            if (parameter.get(0).equalsIgnoreCase("for")) {
                JsonUtils.readJsonFromUrlToJsonObject(WeatherUtils.getUrlOfGivenCity(parameter.get(1)));
            } else {
                JsonUtils.readJsonFromUrlToJsonObject(WeatherUtils.getUrlOfGivenCity(parameter.get(0)));
            }
        }

//        JsonUtils.getJson(getJsonObject(), path).toString();
//        for (int i = 0; i < jsonArray.length(); i++) {
//            System.err.println(jsonArray.get(i));
//        }
        return null;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }
}
