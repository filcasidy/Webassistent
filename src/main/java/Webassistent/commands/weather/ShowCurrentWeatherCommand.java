package Webassistent.commands.weather;

import Webassistent.commands.ICommand;
import Webassistent.utils.HtmlCreatorUtils;
import Webassistent.utils.JsonUtils;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Weather command to get current weather.
 */
public class ShowCurrentWeatherCommand implements ICommand {
    private JSONObject jsonObject;


    public ShowCurrentWeatherCommand() {
        setJsonObject(JsonUtils.readJsonFromUrlToJsonObject(WeatherUtils.getUrlOfGivenCity(WeatherConstants.DEFAULT_CITY_BREMEN)));
    }

    @Override
    public Object execute(List<String> parameter) {
        if (parameter != null && !parameter.isEmpty()) {
            String completeParameter = "";
            for (String value : parameter) {
                if (!value.contains("for")) {
                    completeParameter += value;
                }
            }
            setJsonObject(JsonUtils.readJsonFromUrlToJsonObject(WeatherUtils.getUrlOfGivenCity(completeParameter)));
        }

        String id = getJson("query.results.channel.item.condition.code");
        String windSpeed = getJson("query.results.channel.wind.speed");
        String conditionText = getJson("query.results.channel.item.condition.text");
        String temperature = getJson("query.results.channel.item.condition.temp");
        String pressure = getJson("query.results.channel.atmosphere.pressure");
        String cityAndCountry = getJson("query.results.channel.location.city") + ", " + getJson("query.results.channel.location.country");

        return createCompleteDocument(id, windSpeed, conditionText, temperature, pressure, cityAndCountry);
    }

    private Document createCompleteDocument(String id, String windSpeed, String condition, String temperature, String pressure, String cityAndCountry) {
        Document responseDocument = HtmlCreatorUtils.createPanel(cityAndCountry);
        Element panelBody = responseDocument.getElementById("panelBody");
        panelBody.appendElement("i")
                .addClass("wi " + WeatherUtils.getClassAttributeFrom(Integer.valueOf(id)))
                .attr("style", "padding: 10px;font-size: 60px");

        panelBody.append(HtmlCreatorUtils.createTable(
                HtmlCreatorUtils.createListWithInformationValues(
                        WeatherConstants.TEMPERATURE,
                        WeatherConstants.SPEED_OF_WIND,
                        WeatherConstants.CONDITION,
                        WeatherConstants.PRESSURE
                ),
                createTableRow(
                        temperature,
                        windSpeed,
                        condition,
                        pressure), true).toString());
        return responseDocument;
    }

    private String getJson(String path) {
        return JsonUtils.getJson(getJsonObject(), path).toString();
    }

    private List<List<String>> createTableRow(String temperature, String windSpeed, String condition, String pressure) {
        List<List<String>> rows = new ArrayList<>();
        rows.add(HtmlCreatorUtils.createListWithInformationValues(
                temperature + WeatherConstants.CELSIUS,
                windSpeed + WeatherConstants.KILOMETER_PER_HOUR,
                condition,
                pressure + WeatherConstants.MILLIBAR
        ));
        return rows;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
