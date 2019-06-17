package google;

/**
 * Refactored by Yuecheng Rong
 * Yinsheng Dong
 */

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;

import java.util.Arrays;
// import java.util.Scanner;

/**
 * Created by Yinsheng Dong(yid164).
 * This class is using Google API to get distance between 2 positions.
 **/
public class GetDistance {

    /**
     * Context for connecting google api key.
     */
    private GeoApiContext context = new GeoApiContext.Builder().apiKey(
            "AIzaSyCbZwJ0Y8Qw01YObRvoNXCasw2mLimHMZw").build();

    /**
     * String used to save distance in string form "followed by 'km'".
     */
    private String getDistance = null;

    /**
     * Float used to save distance in float form.
     */
    private Float distanceFloat;

    /**
     *  The first element of a list.
     */
    private final int FIRST_ELEMENT = 0;

    /**
     * When Call this function,
     * you will get the String of distance between origin and destination.
     * @ParameterNames origin
     * @ParameterNames destination
     * @return the string that contains the distance between two inputs
     */
    public String getStringDistance(final String origin, final String destination) {

        if (context != null) {
            Distance distance = new Distance();
            String[] orig = {origin};
            String[] dest = {destination};
            DirectionsResult result = DirectionsApi.getDirections(
                    context, getFirstElementOfStringList(orig), getFirstElementOfStringList(dest)).awaitIgnoreError();
            distance.getClass().getResource(Arrays.toString(result.routes.clone()));
            DistanceMatrix distanceMatrix = DistanceMatrixApi.getDistanceMatrix(context, orig, dest).awaitIgnoreError();
            getDistance = getElement(distanceMatrix).distance.humanReadable;
        }
        return getDistance;

    }

    /**
     * getFloatDistance function so will get the float of distance between origin and destination.
     * @ParameterNames origin, destination
     * @return a float
     */
    public float getFloatDistance(final String origin, final String destination) {
        String result = "";
        String toConvert = getStringDistance(origin, destination);
        for (int c = 0; c < toConvert.length(); c++) {
            Character character = toConvert.charAt(c);
            if (!Character.isLetter(character)) {
                result = result + character;
            }
        }
        distanceFloat = Float.valueOf(result);
        System.out.println(distanceFloat);
        return distanceFloat;
    }


    /**
     * Helper function that generate the first element from a String list.
     *
     * @param input the input String list
     * @return
     */
    private String getFirstElementOfStringList(final String[] input) {
        return input[FIRST_ELEMENT];
    }

    /**
     * Helper function that generate the first element from a DistanceMatrix.
     *
     * @param input the input DistanceMatrix
     * @return
     */
    private DistanceMatrixElement getElement(final DistanceMatrix input) {
        return input.rows[FIRST_ELEMENT].elements[FIRST_ELEMENT];
    }

}
