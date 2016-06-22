package Webassistent;

import Webassistent.services.BundesligaService;

import java.util.LinkedList;
import java.util.List;

public class BundesligaServiceTest {
    BundesligaService bundesligaService = new BundesligaService();

    @org.junit.Test
    public void test1() throws Exception {
        List<String> test = new LinkedList<>();
        test.add("34");
        test.add("2015");
        bundesligaService.getServiceResponse("Show results from", test);

    }

}