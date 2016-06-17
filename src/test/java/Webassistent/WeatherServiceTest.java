package Webassistent;

import Webassistent.services.WeatherService;

public class WeatherServiceTest {

    WeatherService weatherService = new WeatherService();

    @org.junit.Test
    public void test1() throws Exception {
        weatherService.getServiceResponse("Show today weather", null);
    }
}
