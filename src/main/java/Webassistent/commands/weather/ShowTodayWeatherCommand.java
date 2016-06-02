package Webassistent.commands.weather;

import Webassistent.JsonConnector;
import Webassistent.commands.ICommand;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ShowTodayWeatherCommand implements ICommand{

    private String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Bremen%22)%20AND%20u%3D%22c%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    public Document createHtmlObject(){
        Document doc =  Document.createShell("");

        Element headline = doc.body().appendElement("h1").text("Wetter für Heute");
        Element pTag = doc.body().appendElement("p").text("some text ...");
        Element span = pTag.prependElement("span").text("That's");
        return doc;

    }

    @Override
    public Object execute() {
        JsonConnector connector = new JsonConnector();
        return createHtmlObject();

    }
}
