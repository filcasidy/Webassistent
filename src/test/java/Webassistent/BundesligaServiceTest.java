package Webassistent;

import Webassistent.services.BundesligaService;

public class BundesligaServiceTest {
    BundesligaService bundesligaService = new BundesligaService();

    @org.junit.Test
    public void test1() throws Exception {
        System.err.println(bundesligaService.getServiceResponse("Show results from yesterday", null));

    }

}