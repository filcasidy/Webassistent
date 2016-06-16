package Webassistent.commands.weather;

import Webassistent.JsonConnector;
import Webassistent.commands.ICommand;
import Webassistent.utils.HtmlCreatorUtils;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

import java.util.List;

public class ShowTodayWeatherCommand implements ICommand {

    private String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Bremen%22)%20AND%20u%3D%22c%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    @Override
    public Object execute(List<String> para) {
//        JsonConnector connector = new JsonConnector();
//        List<String> headings = Lists.newArrayList();
//        headings.add("Tag");
//        headings.add("Wetter");
//
//        List<String> etries1 = Lists.newArrayList();
//        etries1.add("Montag");
//        etries1.add("Sonnig");
//
//        List<String> etries2 = Lists.newArrayList();
//        etries2.add("Mittwoch");
//        etries2.add("Regen");
//        List<List<String>> rowsEntr = Lists.newArrayList();
//        rowsEntr.add(etries1);
//        rowsEntr.add(etries2);
//
//
//        return HtmlCreatorUtils.createTable(headings,rowsEntr,"Wetter für 2 Tage");

        return HtmlCreatorUtils.createPanel("TITEL","SSuper viel text","http://www.w3schools.com/html/html_links.asp");
    }
}
