package Webassistent.commands.news;

import org.json.JSONObject;
import Webassistent.JsonConnector;
import Webassistent.commands.ICommand;

public class ShowTestNewsCommand implements ICommand {

	private String urlendpoint = "https://gateway-a.watsonplatform.net/calls/data/GetNews";
	private String apikey = "?apikey=8c616a12601d88cbc6db0688ea4d65bf9ae267f0";
	private String outputmode = "&outputMode=json";

	@Override
	public Object execute() {
		JsonConnector connector = new JsonConnector();
		System.out.println(getNews("Bremen"));
		JSONObject object = connector.readJsonFromUrl(getNews("Bremen"));
		System.out.println(object);
		if (object.get("status").equals("OK")) {

			JSONObject result = object.getJSONObject("result");

			org.json.JSONArray docs = result.getJSONArray("docs");
			for (int i = 0; i < docs.length(); i++) {
				JSONObject jsonObj = docs.getJSONObject(i);
				System.out.println(jsonObj);
			}

			return result;
		} else {
			if (object.get("status").equals("ERROR")) {
				return object.getString("status") + ":" + object.getString("statusInfo");
			}
		}
		System.out.println(object.get("status"));
		return object;

	}

	public String getNews(String thema) {

		String period = "&start=now-6h&end=now";
		String para = "&q.enriched.url.text=" + thema;
		String re = "&return=enriched.url.text,original.url";

		// System.out.println(urlendpoint + apikey + outputmode + period + para
		// + re);

		return urlendpoint + apikey + outputmode + period + para + re;

	}

}
