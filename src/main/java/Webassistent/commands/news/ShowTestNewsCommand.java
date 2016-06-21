package Webassistent.commands.news;

import Webassistent.commands.ICommand;
import Webassistent.utils.HtmlCreatorUtils;
import Webassistent.utils.JsonUtils;

import java.util.List;

import org.json.JSONObject;
import org.jsoup.nodes.Document;

public class ShowTestNewsCommand implements ICommand {

	private String urlendpoint = "https://gateway-a.watsonplatform.net/calls/data/GetNews";
	private String apikey = "?apikey=8c616a12601d88cbc6db0688ea4d65bf9ae267f0";
	private String outputmode = "&outputMode=json";

	@Override
	public Object execute(List<String> parameter) {

		JsonUtils connector = new JsonUtils();
		JSONObject mainobject = connector.readJsonFromUrlToJsonObject(getNews(parameter.get(parameter.size() - 1)));
		System.out.println(getNews(parameter.get(parameter.size() - 1)));

		System.out.println(mainobject);
		if (mainobject.get("status").equals("OK")) {
			JSONObject result = mainobject.getJSONObject("result");
			String title = "Nothing found", text = null, url = null;
			Document document = null;
			if (result.has("docs")) {
				org.json.JSONArray docs = result.getJSONArray("docs");

				for (int i = 0; i < docs.length(); i++) {
					JSONObject jsonObj = docs.getJSONObject(i);
					title = connector.getJson(jsonObj, "source.enriched.url.title").toString();
					text = connector.getJson(jsonObj, "source.enriched.url.text").toString();
					url = connector.getJson(jsonObj, "source.original.url").toString();
					document = HtmlCreatorUtils.createPanel(title, text, url);
				}

			}
			return document;
		} else {
			if (mainobject.get("status").equals("ERROR")) {
				return mainobject.getString("status") + ":" + mainobject.getString("statusInfo");
			}
		}
		return mainobject;

	}

	public String getNews(String thema) {

		String period = "&start=now-6d&end=now&count=10";
		String para = "&q.enriched.url.title=" + thema;
		String re = "&return=enriched.url.text,original.url,enriched.url.title";

		return urlendpoint + apikey + outputmode + period + para + re;

	}

}
