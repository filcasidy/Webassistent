package Webassistent;

import org.json.JSONArray;
import org.json.JSONObject;

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

        JSONObject list = new JSONObject();
        list.put("key" , "1");
        String tmp= (String)jsonParser.convertJson(list,9999);
        assertTrue("Undefined Command", tmp.equals("undefined"));
    }
}
