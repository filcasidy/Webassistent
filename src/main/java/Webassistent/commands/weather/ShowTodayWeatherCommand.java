package Webassistent.commands.weather;

import Webassistent.commands.ICommand;
import Webassistent.utils.HtmlCreatorUtils;
import Webassistent.utils.JsonUtils;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class ShowTodayWeatherCommand implements ICommand {

    private String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Bremen%22)%20AND%20u%3D%22c%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    @Override
    public Object execute(List<String> parameter) {
        JsonUtils connector = new JsonUtils();
        JSONObject jsonObject = connector.readJsonFromUrlToJsonObject(url);
        int id = Integer.valueOf(connector.getJson(jsonObject, "query.results.channel.item.condition.code").toString());
        String conditionText = connector.getJson(jsonObject, "query.results.channel.item.condition.text").toString();
        String temperature = connector.getJson(jsonObject, "query.results.channel.item.condition.temp").toString();

        Document document = HtmlCreatorUtils.createPanel(conditionText);
        Element panelBody = document.getElementById("panelBody");
        panelBody.appendElement("i")
                .addClass("wi " + WeatherUtils.getClassAttributeFrom(id))
                .attr("style", "padding-bottom: 10px;font-size: 60px");
        Element firstRowClass = panelBody.appendElement("div").addClass("row");
        firstRowClass.appendElement("div").addClass("col-md-4").appendText("Temperature");
        firstRowClass.appendElement("div").addClass("col-md-4").appendText("zweite");
        firstRowClass.appendElement("div").addClass("col-md-4").appendText("dritte");
        Element secondRowClass = panelBody.appendElement("div").addClass("row");
        secondRowClass.appendElement("div").addClass("col-md-4").appendText(temperature + " °C");
        secondRowClass.appendElement("div").addClass("col-md-4").appendText("2");
        secondRowClass.appendElement("div").addClass("col-md-4").appendText("3");

        return document;

    }
}
