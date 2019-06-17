package Pre_version_Search_sort;

import java.util.ArrayList;

import database.GoConnection;
import database.NearbyRestaurant;

/**
 * Created by Yuecheng Rong
 */

public class Sort
{
    GoConnection connection = new GoConnection();
    NearbyRestaurant nearbyRestaurant = new NearbyRestaurant();

    ExecuteWithInput e = new ExecuteWithInput();
    String Query;

    // restaurant id
    public ArrayList<Integer> k = new ArrayList<>();
    public ArrayList<Integer> rate = new ArrayList<>();
    public ArrayList<Long> waiting_time = new ArrayList<>();
    public ArrayList<ArrayList<String>> result = new ArrayList<>();


    /**
     * Sort the result by rate
     * Generate information from table "restaurant" column "rate"
     *
     * @param inputResult: superArray
     */
    public void SortByRate(String search, boolean SearchDish, ArrayList<ArrayList<String>> inputResult)
    {
        search = "%" + search.toLowerCase() + "%";
        k.clear();
        Query = null;
        if (connection.coon == null)
        {
            connection.connect();
        }
        if (!SearchDish)
        {
            Query = "select * from restaurants where LOWER(restaurant_name) like ? ORDER BY user_id ASC";
            k = e.execute(Query,connection,search,"id");
            rate = e.execute(Query,connection,search,"user_id");
            waiting_time = e.getLong(Query,connection,search,"phone_num");
        }
        else if (SearchDish)
        {
            // if super array is null, which means nothing is showed up
            if (inputResult.isEmpty())
            {
                System.err.println("No result");
            }
            else
            {
                // will be changed to "bubble sort" after waiting time and price is added
                TempbubbleSort(inputResult, 1);
                result = inputResult;
            }
        }
    }

    /**
     * Sort the result by distance
     * Need to call google (get distance)
     *
     * @param search
     */
    public void SortByDistance(String search, ArrayList<ArrayList<String>> inputResult, int id)
    {
        search = "%" + search.toLowerCase() + "%";
        k.clear();
        Query = null;

        if (connection.coon == null)
        {
            connection.connect();
        }
        // If customer did not log in
        if (id == 0)
        {
            System.err.println("Did not log in, cannot generate address");
            return;
        }
        // If input is null, which means new research
        // Show nearby restaurants
        if(search.length() == 2)
        {
            result = nearbyRestaurant.restaurantNearBy(id);
        }
        else
        {
            // Sort by distance
            bubbleSort(inputResult, 5);
            //System.out.println(searchDishes.finalResult);
            // result = nearbyRestaurant.finalResult;
            result = inputResult;
            //System.out.println(result);
        }

        //print();
    }

    /**
     * Sort the food item by their price
     * Generate information from table "dishes" column "dish_price"
     * This funtion is the only function that can't do anything when the input is restaurant
     *
     * @param inputResult the super array need to be sorted
     * @param SearchDish whether the input is dish or not
     */
    public void SortByPrice(ArrayList<ArrayList<String>> inputResult, boolean SearchDish)
    {
        if(SearchDish == false)
        {
            System.err.println("Input is not dish, cannot sort by price");
        }
        else
        {
            bubbleSort(inputResult,7);
            result = inputResult;
        }
    }


    /**
     * Sort the result by waiting time
     * Generate information from table "restaurant" column "waiting time"
     *
     * @param search: result of search
     */

    public void SortByWaitingTime(String search, boolean SearchDish, ArrayList<ArrayList<String>> inputResult)
    {

        search = "%" + search.toLowerCase() + "%";
        k.clear();
        Query = null;
        if (connection.coon == null)
        {
            connection.connect();
        }
        if (SearchDish == false)
        {
            Query = "select * from restaurants where LOWER(restaurant_name) like ? ORDER BY phone_num ASC";
            k = e.execute(Query,connection,search,"id");
            rate = e.execute(Query,connection,search,"user_id");
            waiting_time = e.getLong(Query,connection,search,"phone_num");
        }
        else if (SearchDish == true)
        {
            // if super array is null, which means nothing is showed up
            if(inputResult.isEmpty())
            {
                System.err.println("No result");
            }
            else
            {
                // will be changed to "bubble sort" after waiting time and price is added
                TempbubbleSort(inputResult,2);
                result = inputResult;
            }
        }
        //print();
    }




    // Following functions are helper functions


    /**
     * Regular bubble sort for String "number"
     * Only work for sort by price/waiting time/rate
     *
     * @param input the super array that contains all the information can be sorted
     * @param index the column to be sorted
     */
    private void bubbleSort(ArrayList<ArrayList<String>> input, int index)
    {
        for (int i = 0; i < input.size(); i++)
        {
            for (int k = 0; k < input.size() - i - 1; k++)
            {
                if (Integer.parseInt(input.get(k).get(index)) > Integer.parseInt(input.get(k+1).get(index)))
                {
                    ArrayList<String> temp = input.get(k);
                    input.set(k,input.get(k+1));
                    input.set(k+1,temp);
                }
            }
        }
        result = input;
    }

    // this will be deleted after waiting time is added
    // Since waiting time is Long, we need a new function for now
    private void TempbubbleSort(ArrayList<ArrayList<String>> input, int index)
    {
        for (int i = 0; i < input.size(); i++)
        {
            for (int k = 0; k < input.size() - i - 1; k++)
            {
                if (Long.parseLong(input.get(k).get(index)) > Long.parseLong(input.get(k+1).get(index)))
                {
                    ArrayList<String> temp = input.get(k);
                    input.set(k,input.get(k+1));
                    input.set(k+1,temp);
                }
            }
        }
        result = input;
    }






    public static void main(String[] args)
    {


        Sort sr = new Sort();

        /*
        // If nothing taken from textfield, make the input " "
        System.out.println("SortByItems");
        sr.SortByWaitingTimeWithLogin(" ");
        System.out.println("SortByDistanceWithLogin");
        sr.SortByDistanceWithLogin(" ");
        System.out.println("SortByRate");
        sr.SortByRate(" ");

        sr.SortByDistanceWithLogin("fries",new ArrayList<>(),1);

        */
        //sr.SortByPriceWithLogin("rie");

        //System.out.println(sort.sortByWaitingTimeRestaurant);

    }

    private void print(ArrayList t)
    {
        // Test

        for (int i=0; i<t.size(); i++)
        {
            System.out.print(t.get(i) + " ");
        }
        System.out.println();

    }

    private void printSuper(ArrayList<ArrayList<String>> finalResult)
    {
        for (int i=0; i<finalResult.size(); i++)
        {
            System.out.println("next: ");
            System.out.println("Restaurant name: " + finalResult.get(i).get(0));
            System.out.println("Price: " + finalResult.get(i).get(1));
            System.out.println("Dish name: " + finalResult.get(i).get(2));
            System.out.println("rest_id: " + finalResult.get(i).get(3));
            System.out.println("rest_open_time: " + finalResult.get(i).get(4));
            System.out.println("rest_close_time: " + finalResult.get(i).get(5));
            System.out.println();
        }
        System.out.println();
    }


}
