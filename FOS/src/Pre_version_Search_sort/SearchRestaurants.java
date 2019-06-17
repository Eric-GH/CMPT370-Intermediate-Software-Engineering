
package Pre_version_Search_sort;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.GoConnection;
import database.NearbyRestaurant;
import google.GetDistance;

/**
 * Create by Yuecheng Rong
 */

public class SearchRestaurants
{
    GoConnection connection = new GoConnection();
    ExecuteWithInput e = new ExecuteWithInput();
    NearbyRestaurant nearbyRestaurant = new NearbyRestaurant();
    GetDistance getDistance = new GetDistance();
    String Query;

    // Restaurant id appears at the 7th space
    ArrayList<Integer> k = new ArrayList<>();


    // The super array that contains all the information
    // first space: restaurant name
    // second space: restaurant rate. Since not added yet, use "user_id" instead
    // third space: restaurant waiting time. Since not added yet, use "phone_num" instead
    // forth space: restaurant Address
    // fifth space: restaurant distance
    // sixth space: integer restaurant distance, for sort use
    // 7th place: restaurant id
    public ArrayList<ArrayList<String>> finalResult = new ArrayList<>();

    public void Search(String search, int id)
    {
        //try{
        if(connection.coon==null)
        {
            connection.connect();
        }
        // Start new search
        allClear();
        Query = null;
        // If input no dish name, then sort cannot be done
        if(search.equals("") || search.equals(null))
        {
            /**
             * Panel should appear instead of stdout
             */
            System.out.println("Cannot search the empty input");
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

            search = "%" + search.toLowerCase() + "%";

            Query = "select * from restaurants where LOWER(restaurant_name) like ? ";
            k = e.execute(Query,connection,search,"id");

            /**
             * Generate restaurant information:
             * restaurant name, rate, waiting_time, address, distance and integer distance, respectively
             */
            nearbyRestaurant.getRestaurantAddress(k);
            nearbyRestaurant.getCustomerAddress(id);
            // Generate the distance
            for (int i=0; i < k.size(); i++)
            {
                String distance = getDistance.getStringDistance(nearbyRestaurant.customerAddress,
                                  nearbyRestaurant.finalResult.get(i).get(3));
                nearbyRestaurant.finalResult.get(i).add(distance);
                transferIntoInteger(distance);
                nearbyRestaurant.finalResult.get(i).add(String.valueOf(transferIntoInteger(distance)));
                // Add restaurant_id and complete restaurant name into finalResult
                nearbyRestaurant.finalResult.get(i).add(k.get(i).toString());
            }


            finalResult = nearbyRestaurant.finalResult;


            /**
             for (int i=0;i<searchResult.size();i++){
             System.out.print(searchResult.get(i) + " ");
             }
             System.out.println();

             for (int i=0;i<searchResult.size();i++){
             System.out.print(price.get(i) + " ");
             }
             System.out.println();
             System.out.println("-------------------------------");
             */


            for (int i=0; i<finalResult.size(); i++)
            {
                System.out.println("Restaurant name: " + finalResult.get(i).get(0));
                System.out.println("Rest_rate: " + finalResult.get(i).get(1));
                System.out.println("Rest_waiting_time: " + finalResult.get(i).get(2));
                System.out.println("Address: " + finalResult.get(i).get(3));
                System.out.println("distance: " + finalResult.get(i).get(4));
                System.out.println("integer distance: " + finalResult.get(i).get(5));
                System.out.println("restaurant id: " + finalResult.get(i).get(6));
                System.out.println();
            }
            System.out.println();


            // Problem: if more than one dish fits the result under the same restaurant, corrupt
            // E.g. KFC has oneBurger and twoBurger, and search "burger"


        }
        /*
        }catch (SQLException e)
        {
            e.getStackTrace();
        }
        */
        /*
        print();
        for (int i=0;i<restaurantName.size();i++){
            System.out.print(restaurantName.get(i) + " ");
        }
        System.out.println();
        */
    }

    /**
     * Helper function that clear all the inner ArrayLists
     */
    private void allClear()
    {
        k.clear();
        finalResult.clear();
    }

    /**
     * This function can transfer the String input from GetDistance into integer
     *
     * @param input String input from GetDistance
     */
    private int transferIntoInteger(String input)
    {
        int temp = 0;
        if(input.contains("km"))
        {
            String str = "[^0-9.]";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(input);
            float k = Float.parseFloat(m.replaceAll("").trim());
            k = k*1000;
            temp = (int) k;
        }
        else if (input.contains("m"))
        {
            String str = "[^0-9.]";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(input);
            float k = Float.parseFloat(m.replaceAll("").trim());
            temp = (int) k;
        }
        return temp;
    }


    public static void main(String[] args)
    {
        SearchRestaurants SD = new SearchRestaurants();
        SD.Search("KF",1);
    }
}
