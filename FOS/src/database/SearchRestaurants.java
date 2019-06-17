package database;

import Entities.Restaurant;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.sql.*;
import java.util.ArrayList;

/**
 * Pre-version: Yuecheng Rong
 * Written by Yinsheng Dong(yid164)
 * This class is using to search a restaurant by name
 */
public class SearchRestaurants
{

    /**
     * connection first
     */
    GoConnection connection = new GoConnection();

    /**
     * public message
     */
    public String message = null;

    /**
     * What the function found
     */
    //public String restaurantFound = null;

    /**
     * the restaurant_id that searched
     */



    private int restaurant_id;
    private Restaurant restaurant;
    private int user_id;
    private String restaurant_name;
    private String license_id;
    private Time open_time;
    private Time close_time;
    private String phone_num;
    private String e_mail_address;
    private Time waiting_time;
    private float avg_price;

    private String location_address;



    /**
     * the function to search a restaurant by a name that given by users
     * @param restaurantName
     * @pre-cond: the search cannot be null or empty
     */
    public void SearchRestaurant(String restaurantName)
    {
        // make sure connection first
        connection.connect();
        // the search cannot by null or empty set
        if(restaurantName.equals(" ")|| restaurantName.equals(null))
        {
            message = "Can not search the empty input";
            try
            {
                connection.coon.close();
            }
            catch (SQLException e)
            {
                e.getStackTrace();
            }
        }
        else if (connection.coon!=null)
        {
            restaurantName = "%" + restaurantName.toLowerCase() + "%";
            try
            {
                // use the search query to search the restaurant information in the db, then return the message out
                String searchQuery = "select * from restaurants where LOWER(restaurant_name) like ? ";
                PreparedStatement ppstmt = connection.coon.prepareStatement(searchQuery);
                ppstmt.setString(1,restaurantName);
                ResultSet rs = ppstmt.executeQuery();
                if (rs.next())
                {
                    user_id = rs.getInt("user_id");
                    restaurant_id = rs.getInt("id");
                    restaurant_name = rs.getString("restaurant_name");
                    open_time = rs.getTime("open_time");
                    close_time = rs.getTime("close_time");
                    phone_num = rs.getString("phone_num");
                    e_mail_address = rs.getString("e_mail_address");
                    waiting_time = rs.getTime("waiting_time");
                    avg_price = rs.getFloat("avg_price");

                    restaurant = new Restaurant(user_id,restaurant_name,license_id,open_time,close_time,phone_num,e_mail_address);
                    restaurant.setAvg_price(avg_price);
                    restaurant.setWaiting_time(waiting_time);
                    String rateSearch = "select stars from reviews where restaurant_id = "+restaurant_id;

                    Statement statement = connection.coon.createStatement();
                    ResultSet resultSet = statement.executeQuery(rateSearch);
                    if(resultSet.next())
                    {
                        restaurant.setReview_point(resultSet.getFloat(1));
                    }

                    String addressQuery = "select house_num, street, city, province, post_code from locations where user_id="+user_id;
                    Statement statement1 = connection.coon.createStatement();
                    ResultSet resultSet1 = statement1.executeQuery(addressQuery);
                    if(resultSet1.next())
                    {
                        location_address = resultSet1.getInt(1)+" "+
                                resultSet1.getString(2)+" "+
                                resultSet1.getString(3)+" "+
                                resultSet1.getString(4)+" "+
                                resultSet1.getString(5);
                    }

                    message = "Found";
                    connection.coon.close();
                }
            }
            catch (SQLException e)
            {
                e.getStackTrace();
            }
        }
    }

    public Restaurant getRestaurantFound() {
        return restaurant;
    }

    public String getLocation_address()
    {
       return location_address;
    }

    public int getRestaurant_id()
    {
        return restaurant_id;
    }

    /**
     * main function for testing
     * @param args
     */
    public static void main(String[] args)
    {
        SearchRestaurants sr = new SearchRestaurants();
        sr.SearchRestaurant("t");
        System.out.println(sr.getRestaurantFound());
        System.out.println(sr.getLocation_address());
        System.out.println(sr.getRestaurant_id());
    }
}
