package Webassistent;

import org.json.JSONObject;

import static org.junit.Assert.*;

public class JsonConnectorTest {

    JsonConnector jsonConnector= new JsonConnector();

    @org.junit.Test
    public void test1() throws Exception {
        JSONObject test= jsonConnector.readJsonFromUrl("http://carlofontanos.com/api/tutorials.php?data=all" );
        assertTrue("getJsonfromURL.Error", test.length()!=0);
    }
}
