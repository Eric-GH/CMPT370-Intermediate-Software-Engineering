package google;

/**
 * Yuecheng  Rong
 */

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * this is the test class for "GetDistance" using JUnit 4
 */
public class GetDistanceTest {

    GetDistance getDistance = new GetDistance();

    final String origin = "265 3 Ave S Saskatoon SK";
    final String destination = "2814 8 St E Saskatoon SK";

    @Test
    public void getStringDistance() throws Exception {
        String result;
        result = getDistance.getStringDistance(origin, destination);
        assertEquals(result, "4.5 km");
    }

    @Test
    public void getFloatDistance() throws Exception {
        float result;
        result = getDistance.getFloatDistance(origin, destination);
        assertEquals(result, 4.5, 0.001);

    }

}