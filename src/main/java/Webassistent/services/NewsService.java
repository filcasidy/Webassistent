package Webassistent.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;

import Webassistent.JsonConnector;
import Webassistent.JsonParser;
import Webassistent.commands.CommandFactory;
import Webassistent.commands.bundesliga.ShowTableCommand;
import Webassistent.commands.news.ShowTestNewsCommand;

public class NewsService extends Service {

	private String SHOW_NEWS = "Show test news";

	CommandFactory commandFactory = new CommandFactory();

	public NewsService() {
		commandFactory.addCommand(SHOW_NEWS, new ShowTestNewsCommand());
	}

	@Override
	public List<String> getCommands() {
		return commandFactory.getAllCommands();
	}

	@Override
	public boolean hasCommand(String userCommand) {
		boolean rVal = false;
		for (String command : getCommands()) {
			if (command.equalsIgnoreCase(userCommand)) {
				rVal = true;
			}
		}
		return rVal;
	}

	@Override
	public Object getServiceResponse(String userCommand) {
		
//		JsonConnector connector = new JsonConnector();
//        JsonParser parser = new JsonParser();
        
//        JSONObject obj = new JSONObject();
//        String url = "https://api.projectoxford.ai/luis/v1/application?id=592744ff-b759-43b2-9126-a55f9a39830d&subscription-key=343421dc2c054839b23529af0bb62f32&q=";
//        String para ="search%20Gothic";
//        obj = connector.readJsonFromUrl(url+para);
//        
//        System.out.println(obj.get("entity"));
		
	
		if (SHOW_NEWS.equalsIgnoreCase(userCommand)) {
			return commandFactory.executeCommand(SHOW_NEWS);
		} else {
			return null;
		}
	}
}
