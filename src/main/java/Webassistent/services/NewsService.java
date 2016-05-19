package Webassistent.services;

import Webassistent.JsonConnector;

public class NewsService {
	private String urlendpoint = "https://gateway-a.watsonplatform.net/calls/data/GetNews";
	private String apikey = "?apikey=8c616a12601d88cbc6db0688ea4d65bf9ae267f0";
	
	public void News(){		
		String outputmode ="&outputMode=json";
		String period ="&start=now-1d&end=now";
		String para ="&q.enriched.url.text=baseball";
		String re ="&return=enriched.url.text,original.url";
		
		JsonConnector conector = new JsonConnector();
		System.out.println(urlendpoint + apikey + outputmode + period + para + re);
		conector.getJsonFromURL(urlendpoint + apikey + outputmode + period + para + re);
		
	}
}
