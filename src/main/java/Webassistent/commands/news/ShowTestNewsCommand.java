package Webassistent.commands.news;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;

import Webassistent.JsonConnector;
import Webassistent.JsonParser;
import Webassistent.commands.ICommand;

public class ShowTestNewsCommand implements ICommand {

	@Override
	public Object execute() {
		JsonConnector connector = new JsonConnector();
        JsonParser parser = new JsonParser();
        return connector.readJsonFromUrl("https://gateway-a.watsonplatform.net/calls/data/GetNews?"
        		+ "apikey=8c616a12601d88cbc6db0688ea4d65bf9ae267f0&outputMode=json"
        		+ "&start=now-1d&end=now&q.enriched.url.text=baseball&return=enriched.url.text,original.url");
	}
	
	public void News() {
		String urlendpoint = "https://gateway-a.watsonplatform.net/calls/data/GetNews";
		String apikey = "?apikey=8c616a12601d88cbc6db0688ea4d65bf9ae267f0";
		
		String outputmode = "&outputMode=json";
		String period = "&start=now-1d&end=now";
		String para = "&q.enriched.url.text=baseball";
		String re = "&return=enriched.url.text,original.url";

		System.out.println(urlendpoint + apikey + outputmode + period + para + re);

		AlchemyDataNews service = new AlchemyDataNews();
		// service.setApiKey(apikey);
		//
		// Date n = new Date();
		// Long d2 = n.getTime()/1000;
		//
		// int a = n.getMonth();
		// n.setMonth(a-1);
		// Long d1 = n.getTime()/1000;
		//
		// Map<String, Object> params = new HashMap<String, Object>();
		//
		// String[] fields =
		// new String[] {"enriched.url.title", "enriched.url.url",
		// "enriched.url.author",
		// "enriched.url.publicationDate",
		// "enriched.url.enrichedTitle.entities",
		// "enriched.url.enrichedTitle.docSentiment"};
		// params.put(AlchemyDataNews.RETURN, StringUtils.join(fields, ","));
		// params.put(AlchemyDataNews.START, d1);
		// params.put(AlchemyDataNews.END, d2);
		// params.put(AlchemyDataNews.COUNT, 7);
		//
		// //Query on adjacent nested fields:
		// params.put("q.enriched.url.enrichedTitle.entities.entity",
		// "|text=IBM,type=company|");
		// //params.put("q.enriched.url.enrichedTitle.docSentiment.type",
		// "positive");
		// //params.put("q.enriched.url.enrichedTitle.taxonomy.taxonomy_.label",
		// "technology and computing");
		//
		// DocumentsResult result = service.getNewsDocuments(params).execute();
		//
		// System.out.println(result);

	}

}
