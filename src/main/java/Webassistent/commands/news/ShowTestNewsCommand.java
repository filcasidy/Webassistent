package Webassistent.commands.news;

import Webassistent.commands.ICommand;
import Webassistent.utils.HtmlCreatorUtils;
import Webassistent.utils.JsonUtils;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Search News to a theme.
 */

public class ShowTestNewsCommand implements ICommand {

	private String urlendpoint = "https://gateway-a.watsonplatform.net/calls/data/GetNews";
	private String apikey = "?apikey=8c616a12601d88cbc6db0688ea4d65bf9ae267f0";
	private String outputmode = "&outputMode=json";

	@Override
	public Object execute(List<String> parameter) {
		Document document = null;
		if (parameter.size() > 0) {
			String thema = parameter.get(0);
			int newscount = 3;
			if (parameter.size() > 1) {
				newscount = Integer.parseInt(parameter.get(1));
			}
			JSONObject mainobject = JsonUtils.readJsonFromUrlToJsonObject(getNews(thema, newscount));

			System.out.println(mainobject);
			if (mainobject.get("status").equals("OK")) {
				JSONObject result = mainobject.getJSONObject("result");

				if (result.has("docs")) {
					org.json.JSONArray docs = result.getJSONArray("docs");
					document = searchdocs(docs);

				} else {
					document = HtmlCreatorUtils.createPanel("No news found");
				}
				return document;
			} else {
				if (mainobject.get("status").equals("ERROR")) {
					return mainobject.getString("status") + ":" + mainobject.getString("statusInfo");
				}
			}
			return mainobject;
		}
		return document = HtmlCreatorUtils.createPanel("No search criteria found");

	}

	/**
	 * request to get the news to theme and the count of the news
	 */
	private String getNews(String thema, int count) {
		String period = "&start=now-6d&end=now&count=" + count;
		String para = "&q.enriched.url.title=" + thema;
		String re = "&return=enriched.url.text,original.url,enriched.url.title";
		return urlendpoint + apikey + outputmode + period + para + re;
	}

	private Document searchdocs(org.json.JSONArray docs) {
		String title = "Nothing found", text = null, url = null;
		Document document = null;
		for (int i = 0; i < docs.length(); i++) {
			JSONObject jsonObj = docs.getJSONObject(i);
			title = JsonUtils.getJson(jsonObj, "source.enriched.url.title").toString();
			text = JsonUtils.getJson(jsonObj, "source.enriched.url.text").toString();
			url = JsonUtils.getJson(jsonObj, "source.original.url").toString();
			if (document == null) {
				document = HtmlCreatorUtils.createPanel(title, text, url);
			} else {
				document.append(HtmlCreatorUtils.createPanel(title, text, url).toString());
			}
		}
		return document;
	}

}
