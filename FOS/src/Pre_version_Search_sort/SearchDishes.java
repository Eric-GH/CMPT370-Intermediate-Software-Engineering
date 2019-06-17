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

public class SearchDishes
{
    GoConnection connection = new GoConnection();
    ExecuteNoInput eni = new ExecuteNoInput();
    ExecuteWithInput e = new ExecuteWithInput();
    NearbyRestaurant nearbyRestaurant = new NearbyRestaurant();
    GetDistance getDistance = new GetDistance();
    String Query;

    // Restaurant id appears at the 7th space
    ArrayList<Integer> k = new ArrayList<>();
    // Dish price appears at the 8th space
    ArrayList<Integer> price = new ArrayList<>();
    // Complete dish name appears at the 9th space
    ArrayList<String> searchResult = new ArrayList<>();


    // The super array that contains all the information
    // first space: restaurant name
    // second space: restaurant rate. Since not added yet, use "user_id" instead
    // third space: restaurant waiting time. Since not added yet, use "phone_num" instead
    // forth space: restaurant Address
    // fifth space: restaurant distance
    // sixth space: integer restaurant distance, for sort use
    // 7th place: restaurant id
    // 8th place: dish price
    // 9th place: complete dish name
    public ArrayList<ArrayList<String>> finalResult = new ArrayList<>();

    public void Search(String search, int id)
    {
        if (connection.coon == null)
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

            Query = "select menus_id from dishes where LOWER(dish_name) like ? ";
            k = e.execute(Query,connection,search,"menus_id");

            /**
             * generate the restaurant_id
             */
            if(k.size() > 0)
            {
                Query = "select restaurant_id from menus where menu_id in (";
                for (int i = 0; i < k.size() - 1; i++)
                {
                    Query += k.get(i) + ", ";
                }
                Query += k.get(k.size()-1) + ") LIMIT " + k.size();
                k.clear();
            }
            else        // no result
            {
                System.out.println("Nothing can be found");
                return;
            }

            k = eni.execute(Query,connection,"restaurant_id");

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
            }


            finalResult = nearbyRestaurant.finalResult;

            /**
             * generate the price and the complete dish name
             */
            Query = "select * from dishes where LOWER(dish_name) like ?";
            searchResult = e.getName(Query,connection,search,"dish_name");
            price = e.execute(Query,connection,search,"dish_prices");
            // add restaurant id, price and complete dish name respectively into list "result"
            for (int i=0; i<price.size(); i++)
            {
                finalResult.get(i).add(k.get(i).toString());
                finalResult.get(i).add(price.get(i).toString());
                finalResult.get(i).add(searchResult.get(i));
            }


            /**
            for (int i=0;i<searchResult.size();i++){
                System.out.print(searchResult.get(i) + " ");
            }
            System.out.println();

            for (int i=0;i<searchResult.size();i++){
                System.out.print(price.get(i) + " ");
            }
            System.out.println();
            */

            System.out.println("-------------------------------");

            for (int i=0; i<finalResult.size(); i++)
            {
                System.out.println("Restaurant name: " + finalResult.get(i).get(0));
                System.out.println("Rest_rate: " + finalResult.get(i).get(1));
                System.out.println("Rest_waiting_time: " + finalResult.get(i).get(2));
                System.out.println("Address: " + finalResult.get(i).get(3));
                System.out.println("distance: " + finalResult.get(i).get(4));
                System.out.println("integer distance: " + finalResult.get(i).get(5));
                System.out.println("restaurant id: " + finalResult.get(i).get(6));
                System.out.println("dish_price: " + finalResult.get(i).get(7));
                System.out.println("dish complete name: " + finalResult.get(i).get(8));
                System.out.println();
            }
            System.out.println();


            // Problem: if more than one dish fits the result under the same restaurant, corrupt
            // E.g. KFC has oneBurger and twoBurger, and search "burger"


        }
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
        price.clear();
        searchResult.clear();
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
        SearchDishes SD = new SearchDishes();
        SD.Search("rie",1);
    }
}
