package Webassistent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonParser {

    public void convertJson(JSONArray jsonArrayFromQuery) {

        // Loop through each item
        for (Object jsonObject : jsonArrayFromQuery) {

            JSONObject actualJsonObject = (JSONObject) jsonObject;

            int id = (int) actualJsonObject.get("id");
            System.out.println("Post ID : " + id);

            String message = (String) actualJsonObject.get("message");
            System.out.println("Message : " + message);

            System.out.println("\n");
        }
    }
}

