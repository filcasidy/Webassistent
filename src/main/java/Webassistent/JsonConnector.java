package Webassistent;


import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;
import org.apache.commons.beanutils.PropertyUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 *
 */
public class JsonConnector {

    /**
     * Gets the {@link JSONObject} form the given url.
     *
     * @param url to the data (REST)
     * @return JSONObject for further work
     */
    public JSONObject readJsonFromUrlToJsonObject(String url) {
        return new JSONObject(readJsonFromUrlToString(url));
    }
    /**
     * Gets the {@link JSONArray} form the given url.
     *
     * @param url to the data (REST)
     * @return JSONArray for further work
     */
    public JSONArray readJsonFromUrlToJsonArray(String url) {
        return new JSONArray(readJsonFromUrlToString(url));
    }

    /**
     * Gets the String of the given url.
     *
     * @param url to the data (REST)
     * @return String for further work
     */
    public String readJsonFromUrlToString(String url) {
        String jsonData = "";
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            String line;
            inputStream = new URL(url).openStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            while ((line = bufferedReader.readLine()) != null) {
                jsonData += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return jsonData;
    }

    /**
     * Gets the json object from given json by the given path.
     *
     * @param json the whole json object
     * @param path to the wished json object for example: query.results.channel
     * @return the object found from the given path
     */
    public Object getJson(JSONObject json, String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object jsonObj = objectMapper.readValue(json.toString(), Object.class);
            return PropertyUtils.getProperty(jsonObj, path);
        } catch (Exception e) {
            return "Could not find given json with the query: " + path;
        }
    }

}
