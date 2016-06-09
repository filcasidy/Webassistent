package Webassistent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JsonParser {

    public Object convertJson(JSONObject jsonObjectFromQuery, int type) {

        int points1 = -1;
        int points2 = -1;

        switch (type) {
            case 0:
                String name1 = "";
                String name2 = "";
                List<String> response = new LinkedList<String>();
                response.add("<--- Answer from Webassistent --> <br> <br> <br>");
                int id = -2;
                int counter = 0;


                return response;

            default:
                return "undefined";
        }
    }
}

