package Webassistent;

import org.json.simple.JSONArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static org.junit.Assert.*;

public class JsonParserTest {

    JsonParser jsonParser = new JsonParser();

    @org.junit.Test
    public void test1() throws Exception {

        JSONObject obj1 = new JSONObject();
        obj1.put("id", 1);
        obj1.put("message" , "myMessage");

        JSONObject obj2 = new JSONObject();
        obj2.put("id", 2);
        obj2.put("message" , "mySecondMessage");

        JSONArray list = new JSONArray();
        list.add(obj1);
        list.add(obj2);
        jsonParser.convertJson(list);

    }
}
