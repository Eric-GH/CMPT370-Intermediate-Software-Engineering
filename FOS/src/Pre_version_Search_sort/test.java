package Pre_version_Search_sort;

import database.GoConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test
{

    GoConnection connection = new GoConnection();

    private void test1 ()
    {
        // connection
        connection.connect();

        if(connection.coon!=null)
        {
            try
            {
                // use the query to find the user id
                String checkUserIdQuery = "select c.phone_num, c.user_id from customers c where id = ?";
                PreparedStatement checkUserIdStmt = connection.coon.prepareStatement(checkUserIdQuery);
                checkUserIdStmt.setInt(1, 1);
                checkUserIdStmt.executeQuery();
                ResultSet resultSet = checkUserIdStmt.executeQuery();
                if (resultSet.next())
                {

                    // after we got the user_id, we need to get the city, province of that customer
                    int userId = resultSet.getInt("user_id");
                    System.out.println(userId);
                    String checkUserInfoQuery = "select * from locations where user_id = ? ";
                    PreparedStatement checkUserInfoStmt = connection.coon.prepareStatement(checkUserInfoQuery);
                    checkUserInfoStmt.setInt(1, userId);
                    ResultSet resultSet1 = checkUserInfoStmt.executeQuery();
                    if (resultSet1.next())
                    {
                        System.out.println(resultSet1.getInt("c.phone_num"));
                    }

                }
            }
            catch(SQLException e)
            {
                e.fillInStackTrace();
            }
        }
    }


    public static void main(String argcs[])
    {
        test T = new test();
        //T.test1();


        String a = "5.5 km";
        String b = "1 m";

        if(a.contains("km"))
        {
            String str = "[^0-9.]";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(a);
            float k = Float.parseFloat(m.replaceAll("").trim());
            k = k*1000;
            int temp = (int) k;
            System.out.println(temp);
        }
        if (b.contains("m"))
        {
            String str = "[^0-9.]";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(b);
            float k = Float.parseFloat(m.replaceAll("").trim());
            int temp = (int) k;
            System.out.println(temp);
        }



    }

}




