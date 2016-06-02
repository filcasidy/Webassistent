package Webassistent;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

public class JsonConnector {

    /**
     * Gets the {@link JSONObject} form the given url.
     *
     * @param url to the data (REST)
     * @return JSONObject for further work
     */
    public JSONObject readJsonFromUrl(String url) {
        InputStream is = null;
        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            is.close();
            return json;
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

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * Parses the {@link JSONObject} into a map.
     * @param json the {@link JSONObject}
     * @param out map which will be filled
     * @return a filled map parsed from the json object
     * @throws JSONException
     */
    public Map<String, String> parse(JSONObject json, Map<String, String> out) throws JSONException {
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
