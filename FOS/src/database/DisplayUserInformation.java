package database;
/**
 * CREATE BY YINSHENG DONG NOV.21
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DisplayUserInformation
{
    GoConnection connection = new GoConnection();

    String userStatus = "";
    public String message = "";
    public String customer_first_name = "";
    public String customer_last_name = "";
    public String customer_email = "";
    public String customer_location = "";
    public String customer_phone_num = "";
    public String customer_pref_food = "";

    public String rest_name = "";
    public String rest_open_time = "";
    public String rest_close_time = "";
    public String rest_phone_num = "";
    public String rest_email = "";
    public String rest_location ="";

    private String getUserStatus (int user_id)
    {

        connection.connect();
        if(connection.coon!=null)
        {
            try
            {
                String query = "SELECT user_status FROM users WHERE id = "+user_id;
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet= statement.executeQuery(query);
                if(resultSet.next())
                {
                    userStatus = resultSet.getString(1);
                    connection.coon.close();
                }
                else
                {
                    message = "not find the user";
                    connection.coon.close();
                }
            }
            catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
        return userStatus;
    }

    public void getCustomerInfo(int user_id)
    {
        String s = getUserStatus(user_id);
        connection.connect();
        if(connection.coon!=null && s.equals("c"))
        {
            try
            {
                String query1 = "SELECT customer_first_name, customer_last_name, e_mail_address, phone_num, pref_food FROM customers WHERE user_id =" + user_id;
                String query2 = "SELECT house_num, street,city, province FROM locations WHERE user_id =" + user_id;
                Statement statement1 = connection.coon.createStatement();
                Statement statement2 = connection.coon.createStatement();
                ResultSet resultSet1 = statement1.executeQuery(query1);
                ResultSet resultSet2 = statement2.executeQuery(query2);
                if (resultSet1.next())
                {
                    customer_first_name = resultSet1.getString("customer_first_name");
                    customer_last_name = resultSet1.getString("customer_last_name");
                    customer_email = resultSet1.getString("e_mail_address");
                    customer_phone_num = resultSet1.getString("phone_num");
                    customer_pref_food = resultSet1.getString("pref_food");
                    message = "find the personal info";
                }
                else
                {
                    message = "NNOOO";
                }
                if (resultSet2.next())
                {
                    customer_location = resultSet2.getInt(1) + " " +
                                        resultSet2.getString(2) + " " +
                                        resultSet2.getString(3) + " " +
                                        resultSet2.getString(4);
                    message = "find the location info";
                }
                else
                {
                    message = "NOOOOO";
                }
                connection.coon.close();

            }
            catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
    }
    public void getRestaurantInfo(int user_id)
    {
        String s = getUserStatus(user_id);
        connection.connect();
        if(connection.coon!=null && s.equals("r"))
        {
            {
                try
                {
                    String query1 = "SELECT restaurant_name, open_time, close_time, phone_num, e_mail_address FROM restaurants WHERE user_id = " + user_id;
                    String query2 = "SELECT house_num, street,city, province FROM locations WHERE user_id = " + user_id;

                    Statement statement1 = connection.coon.createStatement();
                    Statement statement2 = connection.coon.createStatement();

                    ResultSet resultSet1 = statement1.executeQuery(query1);
                    ResultSet resultSet2 = statement2.executeQuery(query2);

                    if(resultSet1.next())
                    {
                        rest_name = resultSet1.getString(1);
                        rest_open_time = resultSet1.getTime(2).toString();
                        rest_close_time = resultSet1.getTime(3).toString();
                        rest_phone_num = resultSet1.getString(4);
                        rest_email = resultSet1.getString(5);
                    }
                    else
                    {
                        message = "Not found info this restaurant";
                    }
                    if(resultSet2.next())
                    {
                        rest_location = resultSet2.getInt(1) + " " +
                                        resultSet2.getString(2) + " " +
                                        resultSet2.getString(3) + " " +
                                        resultSet2.getString(4);

                    }
                    else
                    {
                        message = "Not found location info in this restaurant";
                    }
                    connection.coon.close();


                }
                catch (SQLException e)
                {
                    message = e.fillInStackTrace().toString();
                }
            }
        }
    }

    public static void main(String arg[])
    {
        DisplayUserInformation displayUserInformation = new DisplayUserInformation();
        displayUserInformation.getCustomerInfo(30);
        System.out.println(displayUserInformation.customer_first_name);
        System.out.println(displayUserInformation.rest_name+" "+
                           displayUserInformation.rest_open_time +" "+
                           displayUserInformation.rest_close_time + " "+ displayUserInformation.rest_email + " "+ displayUserInformation.rest_phone_num);


        System.out.println(displayUserInformation.message);
    }

}
