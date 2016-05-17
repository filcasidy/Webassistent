package Webassistent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonParser {

    public void convertJson(JSONArray jsonArrayFromQuery) {

        // Loop through each item
        for (Object jsonObject : jsonArrayFromQuery) {

            JSONObject actualJsonObject = (JSONObject) jsonObject;

            Long id = (Long) actualJsonObject.get("ID");
            System.out.println("Post ID : " + id);

            String title = (String) actualJsonObject.get("post_title");
            System.out.println("Post Title : " + title);

            System.out.println("\n");
        }
    }
}

