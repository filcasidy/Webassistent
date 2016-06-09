package Webassistent;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

public class JsonConnector {

    /**
     * Gets the {@link JSONObject} form the given url.
     * @param url to the data (REST)
     * @return JSONObject for further work
     */
    public List<String> readJsonFromUrl(String url, List<String> keyList) {
        InputStream is = null;
        JSONObject json=null;
        Map <String, String> hashMap = new HashMap<String, String>();
        List<String> returnList = new LinkedList<String>();

        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);

            //IF the Json has [] as first and last char, delete it
            if(jsonText.charAt(0)=='[')
            {
                StringBuilder sb = new StringBuilder(jsonText);
                sb.deleteCharAt(0);
                sb.deleteCharAt((sb.length()-1));

                jsonText=sb.toString();
            }

            json = new JSONObject(jsonText);

            //Filter the JsonObject with the given Keys from the keyList

            hashMap= (HashMap<String, String>)parse(json, hashMap);

            for (String i: keyList) {
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

    private String readAll(Reader rd) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            stringBuilder.append((char) cp);
        }
        return stringBuilder.toString();
    }

    /**
     * Parses the {@link JSONObject} into a map.
     * @param json the {@link JSONObject}
     * @param out map which will be filled
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
