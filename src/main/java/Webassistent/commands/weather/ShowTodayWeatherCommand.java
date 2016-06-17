package Webassistent.commands.weather;

import Webassistent.JsonConnector;
import Webassistent.commands.ICommand;
import Webassistent.utils.HtmlCreatorUtils;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

import java.util.List;

public class ShowTodayWeatherCommand implements ICommand {

    private String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Bremen%22)%20AND%20u%3D%22c%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    @Override
    public Object execute(List<String> para) {
        JsonConnector connector = new JsonConnector();
        JSONObject jsonObject = connector.readJsonFromUrl(url);
        int id = Integer.valueOf(connector.getJson(jsonObject, "query.results.channel.item.condition.code").toString());
        Document document = HtmlCreatorUtils.createPanel("TITEL");
        document.getElementById("panelBody").appendElement("i").addClass("wi " + WeatherUtils.getClassAttributeFrom(id)).attr("style","padding-bottom: 10px;font-size: 60px");
        return document;

    }
}
