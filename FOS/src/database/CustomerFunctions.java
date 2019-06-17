package database;
/**
 * Create by Yinsheng Dong
 */
import Entities.Customer;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerFunctions {
    private int user_id;
    private String first_name;
    private String last_name;
    private String phone_num;
    private String email_address;
    private String pref_food;
    private Customer customer;
    private int customer_id;
    public String message;

    public void setCustomerInfo(Customer customer)
    {
        /**
         * connection
         */
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon != null)
        {
            try
            {

                String addQuery = "insert into customers (user_id, customer_first_name, customer_last_name, phone_num, e_mail_address, pref_food) values (?,?,?,?,?,?)";
                PreparedStatement ppStmt = connection.coon.prepareStatement(addQuery);
                ppStmt.setInt(1,customer.getUser_id());
                ppStmt.setString(2,customer.getFirst_name());
                ppStmt.setString(3,customer.getLast_name());
                ppStmt.setString(4,customer.getPhone_num());
                ppStmt.setString(5,customer.getEmail_address());
                ppStmt.setString(6,customer.getPref_food());
                int affected = ppStmt.executeUpdate();
                // If it is executed, then the message to "saved"
                if(affected > 0)
                {
                    message = "The customer information has been saved";
                    System.out.println(message);
                    connection.coon.close();
                }
                else
                {
                    message = "the customer information has not been saved";
                }

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            message = "lost connection";
        }
    }

    public void editCustomerInfo(int user_id, Customer newCustomer)
    {
        GoConnection goConnection = new GoConnection();
        goConnection.connect();
        if(goConnection.coon!=null)
        {
            try{
                String query = "update customers \n" +
                        "set customer_first_name = ?\n " +
                        "customer_last_name = ? \n" +
                        "customer_phone_num = ? \n" +
                        "e_mail_address = ? \n" +
                        "pref_food = ? \n" +
                        "where user_id = ?";
                PreparedStatement statement = goConnection.coon.prepareStatement(query);
                statement.setString(1,newCustomer.getFirst_name());
                statement.setString(2,newCustomer.getLast_name());
                statement.setString(3,newCustomer.getPhone_num());
                statement.setString(4,newCustomer.getEmail_address());
                statement.setString(5,newCustomer.getPref_food());
                statement.setInt(6,user_id);

                int value = statement.executeUpdate();
                if(value>0)
                {
                    message = "the customer information has been updated";
                    goConnection.coon.close();
                }
                else
                {
                    message = "the customer information not updated";
                    goConnection.connect().close();
                }
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
        else
        {
            message = "lost connection";
        }
    }

    public int getCustomerId(Customer customer)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String query = "select id from customers where user_id ="+customer.getUser_id();
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet.next())
                {
                    customer_id = resultSet.getInt(1);
                    connection.coon.close();
                }
                else
                {
                    message = "Not found the customer id from the customer";
                    connection.coon.close();
                }

            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
        else
        {
            message = "lost connection";
        }
        return customer_id;
    }

    public Customer getCustomer(int customer_id) {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String query = "select user_id, customer_first_name, customer_last_name, phone_num, e_mail_address, pref_food\n" +
                        "from customers where id=" + customer_id;
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet.next())
                {
                    user_id = resultSet.getInt(1);
                    first_name = resultSet.getString(2);
                    last_name = resultSet.getString(3);
                    phone_num = resultSet.getString(4);
                    email_address = resultSet.getString(5);
                    pref_food = resultSet.getString(6);
                    customer = new Customer(user_id,first_name,last_name,phone_num,email_address,pref_food);
                }
                else
                {
                    message = "not found this customers";
                }
                connection.coon.close();
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
        else
        {
            message = "lost connection";
        }
        return customer;
    }

    public static void main(String arg[])
    {
        CustomerFunctions customerFunctions = new CustomerFunctions();
        Customer customer = new Customer(12,"bbb","ssss","123456","jdslfjsdl","chi");
        System.out.println(customerFunctions.getCustomerId(customer));
    }


}
