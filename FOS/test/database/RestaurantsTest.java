package database;

/**
 * Yuecheng  Rong
 */

import Entities.Restaurant;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import static org.junit.Assert.*;

/**
 * Add a new restaurant everytime doing the test and then delete it
 */
public class RestaurantsTest {

    /** The class that will be tested. */
    private Restaurants restaurant = new Restaurants();

    /** The connection to the database. */
    private GoConnection connection = new GoConnection();

    /** Create a new restaurant to be tested */
    private Restaurant theRestaurant = new Restaurant(100, "GreenField",
            "GF11", Time.valueOf("7:00:00"),Time.valueOf("21:00:00"), "3061234567", "greenField@gmail.com");


    /**
     * Test add restaurants.
     * After test we need to delete the restaurant we added.
     * @throws Exception
     */
    @Test
    public void addRestaurant() throws Exception {

        restaurant.addRestaurant(theRestaurant);
        assertEquals("The restaurant information has been saved",restaurant.message);

        this.delete();
    }

    /**
     * Test update the restaurant information
     * @throws Exception
     */
    @Test
    public void updateRestaurant() throws Exception {
        restaurant.addRestaurant(theRestaurant);
        restaurant.updateRestaurant(100,theRestaurant);
        assertEquals("The restaurant information has been saved", restaurant.message);
        this.delete();
    }

    /**
     * Test displaying all the restaurants in the database
     * @throws Exception
     */
    @Test
    public void displayAllRestaurants() throws Exception {
        restaurant.displayAllRestaurants();
        assertEquals("1: Subway subway123 08:00:00 20:00:00 3066534991 subway@subway.com 00:05:00 10.0",
                restaurant.displayRestaurants.get(0).toString());
    }

    /**
     * Test getting the certain restaurant
     * @throws Exception
     */
    @Test
    public void getRestaurant() throws Exception {
        restaurant.getRestaurant(1);
        assertEquals(1,restaurant.restaurant.getUser_id());
    }


    private void delete() {
        try {
            connection.connect();
            String query = "DELETE from restaurants where restaurant_name = 'GreenField'";
            Statement statement = connection.coon.createStatement();
            statement.executeQuery(query);
            connection.coon.close();
        } catch (SQLException e) {

        }
    }

}