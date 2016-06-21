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
 * Weather command to get today's weather.
 */
public class ShowTodayWeatherCommand implements ICommand {
    private JsonUtils connector;
    private JSONObject jsonObject;

    private String DEFAULT_CITY = "Bremen";

    public ShowTodayWeatherCommand() {
        connector = new JsonUtils();
        setJsonObject(connector.readJsonFromUrlToJsonObject(getUrlOfGivenCity(DEFAULT_CITY)));
    }

    private String getUrlOfGivenCity(String cityName) {
        return "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"
                + cityName + "%22)%20AND%20u%3D%22c%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
    }

    @Override
    public Object execute(List<String> parameter) {
        if (!parameter.isEmpty()) {
            if (parameter.get(0).equalsIgnoreCase("for")) {
                setJsonObject(connector.readJsonFromUrlToJsonObject(getUrlOfGivenCity(parameter.get(1))));
            } else {
                setJsonObject(connector.readJsonFromUrlToJsonObject(getUrlOfGivenCity(parameter.get(0))));
            }
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

        panelBody.append(HtmlCreatorUtils.createTable(createHeadings(), createTableRows(
                temperature,
                windSpeed,
                condition,
                pressure), true).toString());
        return responseDocument;
    }

    private String getJson(String path) {
        return connector.getJson(getJsonObject(), path).toString();
    }

    private List<String> createHeadings() {
        List<String> headings = new ArrayList<>();
        headings.add("Temperature");
        headings.add("Speed of wind");
        headings.add("Condition");
        headings.add("Pressure");
        return headings;
    }

    private List<List<String>> createTableRows(String temperature, String windSpeed, String condition, String pressure) {
        List<List<String>> rows = new ArrayList<>();
        List<String> row = new ArrayList<>();
        row.add(temperature + " °C");
        row.add(windSpeed + " km/h");
        row.add(condition);
        row.add(pressure + " mb");
        rows.add(row);
        return rows;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
