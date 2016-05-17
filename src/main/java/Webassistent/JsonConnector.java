package Webassistent;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class JsonConnector {

    public JSONArray getJsonFromURL(String urlString) {
        JSONArray jsonArray = null;
        JSONParser parser = new JSONParser();
        String inputLine;

        try {
            URL  url = new URL(urlString); // URL to Parse
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));


            while ((inputLine = in.readLine()) != null) {
                jsonArray = (JSONArray) parser.parse(inputLine);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}
