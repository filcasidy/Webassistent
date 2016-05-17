package Webassistent;

import org.json.simple.JSONArray;

import static org.junit.Assert.*;

public class JsonConnectorTest {

    JsonConnector jsonConnector= new JsonConnector();

    @org.junit.Test
    public void test1() throws Exception {
        JSONArray test= jsonConnector.getJsonFromURL("http://carlofontanos.com/api/tutorials.php?data=all" );
        assertTrue("getJsonfromURL.Error", test.size()!=0);
    }
}
