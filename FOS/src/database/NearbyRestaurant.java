package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import google.GetDistance;

import java.sql.Statement;
import java.util.ArrayList;

/**
 *  Yinsheng Dong (yid164)
 *  Modified by Yuecheng Rong
 */
public class NearbyRestaurant
{
    /**
     * New Connection
     */
    GoConnection connection = new GoConnection();

    /**
     * This 2-d array for storing the restaurant name we've found, and address for each restaurant
     */
    ArrayList<ArrayList<String>> addressArray = new ArrayList<>();
    /**
     * customerAddress
     */
    public String customerAddress = "";

    /**
     * Helper arraylist for addressArray
     */
    ArrayList<String> oneAddress = null;

    /**
     * The final result array that contains everything
     */
    public ArrayList<ArrayList<String>> finalResult = new ArrayList<>();

    /**
     * The function simply used to get the restaurant nearby (in same city) a customer,
     * and store the restaurant we found into the 2-d array
     * @param customer_id
     */
    private void findRestaurantSameCity(int customer_id)
    {
        // connection
        connection.connect();
        GetDistance getDistance = new GetDistance();

        if(connection.coon!=null)
        {
            try
            {
                // use the query to find the user id
                String checkUserIdQuery = "select user_id from customers where id = ?";
                PreparedStatement checkUserIdStmt = connection.coon.prepareStatement(checkUserIdQuery);
                checkUserIdStmt.setInt(1, customer_id);
                checkUserIdStmt.executeQuery();
                ResultSet resultSet =checkUserIdStmt.executeQuery();
                if (resultSet.next())
                {

                    // after we got the user_id, we need to get the city, province of that customer
                    int userId = resultSet.getInt("user_id");
                    System.out.println(userId);
                    String checkUserInfoQuery = "select l.city, l.province from locations l where l.user_id = ? ";
                    PreparedStatement checkUserInfoStmt = connection.coon.prepareStatement(checkUserInfoQuery);
                    checkUserInfoStmt.setInt(1,userId);
                    ResultSet resultSet1 = checkUserInfoStmt.executeQuery();
                    if(resultSet1.next())
                    {
                        // when we got the city and provinces, there is a query to get all restaurants in same city
                        addressArray = new ArrayList<ArrayList<String>>();
                        String city = resultSet1.getString("city");
                        String province = resultSet1.getString("province");

                        String checkRestaurantQuery = "select l.id, l.user_id,l.house_num, l.street, l.city, l.province " +
                                                      "from locations l " +
                                                      "where l.user_id in (select u.id from users u where u.user_status = 'r') " +
                                                      "and l.city = ? and l.province = ? ";
                        PreparedStatement statement = connection.coon.prepareStatement(checkRestaurantQuery);
                        statement.setString(1,city);
                        statement.setString(2,province);
                        ResultSet getRestaurant = statement.executeQuery();



                        while(getRestaurant.next())
                        {
                            // the is for getting restaurant name query, then simply run it in sql
                            System.out.println(getRestaurant.getInt("user_id"));
                            String getRestNameQuery = "select * from restaurants where user_id = ?";
                            PreparedStatement restStmt = connection.coon.prepareStatement(getRestNameQuery);
                            restStmt.setInt(1,getRestaurant.getInt("user_id"));
                            ResultSet restResult = restStmt.executeQuery();
                            while(restResult.next())
                            {
                                // this is the final step that we put the name in the oneAddress array no.0 position
                                // and put the the address in the no.1 position
                                // then store them in the the 2-array named addressArray
                                oneAddress = new ArrayList<>();
                                oneAddress.add(restResult.getString("restaurant_name"));
                                oneAddress.add(getRestaurant.getInt("house_num")+" "+
                                               getRestaurant.getString("street")+" "+
                                               getRestaurant.getString("city")+" "+
                                               getRestaurant.getString("province"));
                                oneAddress.add(restResult.getString("user_id"));
                                oneAddress.add(restResult.getString("phone_num"));
                                addressArray.add(oneAddress);
                            }

                        }
                        connection.coon.close();
                    }
                    else
                    {
                        System.out.println("No000");
                        connection.coon.close();
                        return;
                    }
                }
                else
                {
                    System.out.println("No");
                    connection.coon.close();
                    return;
                }
            }
            catch (SQLException e)
            {
                e.fillInStackTrace();
            }
        }
    }

    /**
     * This function is used to find the customer address, so we can use google function to calculate the distance then
     * @param customer_id
     */
    public void getCustomerAddress(int customer_id)
    {
        if (connection.coon==null)
        {
            connection.connect();
        }
        if(connection.coon!=null)
        {
            try
            {
                // use the query to get house_num, street, city.. from sql database
                String checkCusQuery = "select l.house_num, l.street, l.city, l.province from locations l where l.user_id" +
                                       "= (select c.user_id from customers c where c.id = ?)";
                PreparedStatement statement = connection.coon.prepareStatement(checkCusQuery);
                statement.setInt(1,customer_id);
                ResultSet resultSet = statement.executeQuery();
                // if it is found, them, store them into the customerAddress string set
                if(resultSet.next())
                {
                    customerAddress = resultSet.getInt("house_num")+" "
                                      +resultSet.getString("street")+" "
                                      +resultSet.getString("city")+" "
                                      +resultSet.getString("province");
                    connection.coon.close();
                }
                else
                {
                    System.out.println("No this customer_id");
                    connection.coon.close();
                }
            }
            catch (SQLException e)
            {
                e.fillInStackTrace();
            }
        }
        else
        {
            System.out.println("lose connection");
        }
    }

    /**
     * This function is used to gather the information of restaurants shown on the screen,
     * so we can use google function to calculate the distance then
     *
     * We create an array "finalResult" that contains rate, waiting time and address, respectively
     *
     * @param restaurantID: the array that contains the id of the restaurants showed on the screen
     */
    public void getRestaurantAddress(ArrayList<Integer> restaurantID)
    {
        if (restaurantID.isEmpty())
        {
            System.err.println("Not found");
            return;
        }
        connection.connect();
        ArrayList<Integer> UserID = new ArrayList<>();
        // customerAddressList: contains rate, waiting time and address, respectively
        finalResult.clear();
        try
        {
            String Query;
            Query = "select * from restaurants where id in (";
            for (int i=0; i<restaurantID.size()-1; i++)
            {
                Query += restaurantID.get(i) + ", ";
            }
            Query += restaurantID.get(restaurantID.size()-1) + ") LIMIT " + restaurantID.size();

            try
            {
                Statement stmt = connection.coon.createStatement();
                // execute the query. rs = what is returned
                ResultSet rs = stmt.executeQuery(Query);
                if(rs.next())
                {
                    UserID.add(rs.getInt("user_id"));
                    // Add restaurant name, rate and the waiting time into TempcustomerAddressList respectively
                    ArrayList<String> TempcustomerAddressList = new ArrayList<>();
                    TempcustomerAddressList.add(rs.getString("restaurant_name"));
                    TempcustomerAddressList.add(Integer.toString(rs.getInt("user_id")));
                    TempcustomerAddressList.add(Long.toString(rs.getLong("phone_num")));
                    finalResult.add(TempcustomerAddressList);
                    while (rs.next())
                    {
                        ArrayList<String> Temp = new ArrayList<>();
                        UserID.add(rs.getInt("user_id"));
                        Temp.add(rs.getString("restaurant_name"));
                        Temp.add(Integer.toString(rs.getInt("user_id")));
                        Temp.add(rs.getString("phone_num"));
                        finalResult.add(Temp);
                    }
                }
                else
                {
                    System.out.println("Not found");
                }
            }
            catch (SQLException e)
            {
                e.getStackTrace();
            }


            if(UserID.isEmpty())
            {
                System.out.println("None user");
                return;
            }
            else
            {
                Query = "select * from locations where user_id in (";
                for (int i = 0; i < UserID.size() - 1; i++)
                {
                    Query += UserID.get(i) + ", ";
                }
                Query += UserID.get(UserID.size() - 1) + ") LIMIT " + UserID.size();
            }
            Statement stmt = connection.coon.createStatement();
            // execute the query. rs = what is returned
            ResultSet resultSet = stmt.executeQuery(Query);
            // if it is found, then, store them into the customerAddress string set
            if (resultSet.next())
            {
                int count = 0;
                customerAddress = resultSet.getInt("house_num") + " "
                                  + resultSet.getString("street") + " "
                                  + resultSet.getString("city") + " "
                                  + resultSet.getString("province");
                finalResult.get(count).add(customerAddress);
                while (resultSet.next())
                {
                    count++;
                    customerAddress = resultSet.getInt("house_num") + " "
                                      + resultSet.getString("street") + " "
                                      + resultSet.getString("city") + " "
                                      + resultSet.getString("province");
                    finalResult.get(count).add(customerAddress);
                }
            }
            else
            {
                System.out.println("No this customer_id");
            }
            System.out.println(finalResult);
        }
        catch (SQLException e)
        {
            e.fillInStackTrace();
        }

    }

    /**
     * The public function to return the restaurant name and distance for a customer by a 2-d array
     * @param customer_id
     * @return
     */
    public ArrayList<ArrayList<String>> restaurantNearBy(int customer_id)
    {
        ArrayList<ArrayList<String>> restaurantDistanceWithName= new ArrayList<ArrayList<String>>();
        getCustomerAddress(customer_id);
        //System.out.println("customerAddress: " + customerAddress);
        findRestaurantSameCity(customer_id);
        GetDistance getDistance = new GetDistance();
        if( !addressArray.isEmpty())
        {
            for (int i = 0; i < addressArray.size(); i++)
            {
                ArrayList<String> oneSet = new ArrayList();
                oneSet.add(addressArray.get(i).get(0));
                oneSet.add(addressArray.get(i).get(2));
                oneSet.add(addressArray.get(i).get(3));
                oneSet.add(addressArray.get(i).get(1));
                //System.out.println(customerAddress);
                //System.out.println(addressArray.get(i).get(1));
                String distance = getDistance.getStringDistance(customerAddress, addressArray.get(i).get(1));
                oneSet.add(distance);

                // One set: restaurant name, rate, waiting time, address, distance, respectively

                restaurantDistanceWithName.add(oneSet);
            }
        }
        finalResult = restaurantDistanceWithName;
        return restaurantDistanceWithName;
    }


    /**
     * testing main
     * @param arg
     */
    public static void main(String arg[])
    {
        NearbyRestaurant nearbyRestaurant = new NearbyRestaurant();
        System.out.println(nearbyRestaurant.restaurantNearBy(1));

        System.out.println("====================================");

        nearbyRestaurant.findRestaurantSameCity(1);
        System.out.println(nearbyRestaurant.addressArray);



        ArrayList<Integer> e = new ArrayList<>();
        e.add(1);
        e.add(4);
        e.add(3);
        e.add(7);
        nearbyRestaurant.getRestaurantAddress(e);





        /**
        nearbyRestaurant.getCustomerAddress(1);
        System.out.println(nearbyRestaurant.customerAddress);
        GetDistance getDistance = new GetDistance();
        //getDistance.getDistance(nearbyRestaurant.customerAdress,nearbyRestaurant.addressArray.get(0).get(1));
        System.out.println(getDistance.getFloatDistance(nearbyRestaurant.customerAddress,nearbyRestaurant.addressArray.get(1).get(1))+"km");
         **/
    }

}
