package Webassistent.commands.weather;

import Webassistent.commands.ICommand;
import Webassistent.utils.HtmlCreatorUtils;
import Webassistent.utils.JsonUtils;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Command to show weather for the next days.
 */
public class ShowWeatherForNextDaysCommand implements ICommand {
    private ArrayList forecast;
    private JSONObject completeJsonObject;

    private int NUMBER_OF_ROWS = 5;

    public ShowWeatherForNextDaysCommand() {
        setJsonObjectAndForecast(WeatherConstants.DEFAULT_CITY_BREMEN);
    }

    private void setJsonObjectAndForecast(String city) {
        setCompleteJsonObject(JsonUtils.readJsonFromUrlToJsonObject(WeatherUtils.getUrlOfGivenCity(city)));
        setForecast((ArrayList) JsonUtils.getJson(getCompleteJsonObject(), "query.results.channel.item.forecast"));
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
            setJsonObjectAndForecast(completeParameter);
        }

        List<List<String>> tableRows = new ArrayList<>();
        List<String> headings = Lists.newArrayList();
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            tableRows.add(Lists.<String>newArrayList());
        }
        Document responseDocument = HtmlCreatorUtils.createPanel(getCityAndCountry());
        Element panelBody = responseDocument.getElementById("panelBody");
        fillHeadingsAndRows(headings,tableRows);
        panelBody.append(HtmlCreatorUtils.createTable(headings, tableRows, false).toString());

        return responseDocument;
    }

    private String getCityAndCountry() {
        return JsonUtils.getJson(getCompleteJsonObject(), "query.results.channel.location.city")
                + ", " + JsonUtils.getJson(getCompleteJsonObject(), "query.results.channel.location.country");
    }

    private void fillHeadingsAndRows(List<String> headings, List<List<String>> tableRows) {
        Document weatherImage = Document.createShell("");
        for (int i = 0; i < getForecast().size(); i++) {
            headings.add(getForecastValueBy(i, "day"));
            tableRows.get(0).add(getForecastValueBy(i, "date"));
            tableRows.get(1).add(getForecastValueBy(i, "text"));
            tableRows.get(2).add(weatherImage.appendElement("i")
                    .addClass("wi " + WeatherUtils.getClassAttributeFrom(Integer.valueOf(getForecastValueBy(i, "code"))))
                    .attr("style", "padding: 5px;font-size: 30px").toString());
            tableRows.get(3).add(getForecastValueBy(i, "high") + WeatherConstants.CELSIUS + " max");
            tableRows.get(4).add(getForecastValueBy(i, "low") + WeatherConstants.CELSIUS + " min");
        }
    }

    private String getForecastValueBy(int index, String key) {
        return ((LinkedHashMap) getForecast().get(index)).get(key).toString();
    }

    public JSONObject getCompleteJsonObject() {
        return completeJsonObject;
    }

    public void setCompleteJsonObject(JSONObject jsonObject) {
        this.completeJsonObject = jsonObject;
    }

    public ArrayList getForecast() {
        return forecast;
    }

    public void setForecast(ArrayList forecast) {
        this.forecast = forecast;
    }
}
