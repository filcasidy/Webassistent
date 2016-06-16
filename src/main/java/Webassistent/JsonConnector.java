package Webassistent;


import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;
import org.apache.commons.beanutils.PropertyUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

public class JsonConnector {

    /**
     * Gets the {@link JSONObject} form the given url.
     *
     * @param url     to the data (REST)
     * @param keyList with desired keys which should be found
     * @return JSONObject for further work
     */
    public List<String> readJsonFromUrl(String url, List<String> keyList) {
        InputStream is = null;
        JSONObject json = null;
        Map<String, String> hashMap = new HashMap<String, String>();
        List<String> returnList = new LinkedList<String>();

        try {
            json = readJsonFromUrl(url);
            //Filter the JsonObject with the given Keys from the keyList

            hashMap = (HashMap<String, String>) parse(json, hashMap);

            for (String i : keyList) {
                returnList.add(hashMap.get(i));
            }

            is.close();
            return returnList;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                is.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Gets the {@link JSONObject} form the given url.
     *
     * @param url to the data (REST)
     * @return JSONObject for further work
     */
    public JSONObject readJsonFromUrl(String url) {
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
        return new JSONObject(jsonData);
    }


    /**
     * Gets the json object based on given path form the url.
     *
     * @param url  rest url to get the whole json
     * @param path to the wished json object for example: query.results.channel
     * @return the object found from the given path
     */
    public Object getJson(String url, String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object jsonObj = objectMapper.readValue(readJsonFromUrl(url).toString(), Object.class);
            return PropertyUtils.getProperty(jsonObj, path);
        } catch (Exception e) {
            return "Could not find given json with the query: " + path;
        }
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

    /**
     * Parses the {@link JSONObject} into a map.
     *
     * @param json the {@link JSONObject}
     * @param out  map which will be filled
     * @return a filled map parsed from the json object
     * @throws JSONException
     */
    private Map<String, String> parse(JSONObject json, Map<String, String> out) throws JSONException {
        Iterator<String> keys = json.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String val = null;
            try {
                JSONObject value = json.getJSONObject(key);
                parse(value, out);
            } catch (Exception e) {
                val = json.get(key).toString();
            }

            if (val != null) {
                out.put(key, val);
            }
        }
        return out;
    }

}
