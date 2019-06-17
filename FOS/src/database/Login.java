package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Written by Yinsheng Dong (yid164)
 * The main purpose of this class is to make a log in function, help to check if the username and
 * password that user input are correct or not
 */
public class Login
{

    /**
     * new connection
     */
    GoConnection connection = new GoConnection();

    /**
     * public message to help other function know if they are correct or not
     */
    public String message = null;

    public String status = "";

    /**
     * the username
     */
    public String name = null;
    //user_account user = new user_account();


    /**
     * The login function that check the username and password
     * @param username
     * @param password
     */
    public void login(String username, String password)
    {
        // check connection first
        connection.connect();
        if(connection.coon!=null)
        {
            try
            {
                // check these variables are exist or not
                String queryString = "select user_status from users where username=? and passwords=?";
                PreparedStatement ppStmt = connection.coon.prepareStatement(queryString);
                ppStmt.setString(1,username);
                ppStmt.setString(2,password);
                ResultSet rs = ppStmt.executeQuery();
                // giving the final message
                if(!rs.next())
                {
                    message = "Wrong username or password";
                    System.out.println(message);
                    connection.coon.close();
                }
                else
                {
                    message = "true";
                    //user.loged_in();
                    name=username;
                    if(rs.getString(1).equals("r"))
                    {
                        status = "restaurant";
                    }
                    else
                    {
                        status = "customers";
                    }
                    System.out.println(message);
                    connection.coon.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String arg[])
    {
        Login login = new Login();
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the username: \n");
        String username = in.nextLine();
        System.out.println("Please enter the password: \n");
        String password = in.nextLine();
        login.login(username,password);
        System.out.println(login.status);
    }
}
