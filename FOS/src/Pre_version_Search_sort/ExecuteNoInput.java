package Pre_version_Search_sort;

import database.GoConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This function is called when the search object is null
 */
public class ExecuteNoInput
{

    /**
     * This function will return a list of ids
     *
     * @param input, search Query
     * @param connection
     * @param getInt, the column be generated
     * @return the result int list
     */
    public ArrayList<Integer> execute(String input, GoConnection connection, String getInt)
    {
        ArrayList<Integer> k = new ArrayList<>();

        try
        {
            // the SQL Query that will be executed
            String Query = input;
            Statement stmt = connection.coon.createStatement();
            // execute the query. rs = what is returned
            ResultSet rs = stmt.executeQuery(Query);
            if(rs.next())
            {
                k.add(rs.getInt(getInt));
                while (rs.next())
                {
                    k.add(rs.getInt(getInt));
                }
            }
            else
            {
                /**
                 * Should be: panel: Not found
                 */
                System.out.println("Not found");
            }
            return k;
        }
        catch (SQLException e)
        {
            e.getStackTrace();
        }
        // ???
        return k;
    }

    /**
     * Now this function is called when the output is a list of String
     *
     * @param input, search Query
     * @param connection
     * @param getString, the column be generated
     * @return the result string list
     */
    public ArrayList<String> executeName(String input, GoConnection connection, String getString)
    {
        ArrayList<String> k = new ArrayList<>();
        try
        {
            if (connection.coon == null)
            {
                connection.connect();
            }
            // the SQL Query that will be executed
            String Query = input;
            Statement stmt = connection.coon.createStatement();
            // execute the query. rs = what is returned
            ResultSet rs = stmt.executeQuery(Query);
            if(rs.next())
            {
                k.add(rs.getString(getString));
                while (rs.next())
                {
                    k.add(rs.getString(getString));
                }
            }
            else
            {
                /**
                 * Should be: panel: Not found
                 */
                System.out.println("Not found");
            }
            return k;
        }
        catch (SQLException e)
        {
            e.getStackTrace();
        }
        // ???
        return k;
    }


    public static void main(String[] args)
    {
        ExecuteNoInput eni = new ExecuteNoInput();
        GoConnection connection = new GoConnection();
        connection.connect();
        eni.execute("select restaurant_id from menus ORDER BY case menu_id when 13 then 1 end LIMIT 1",
                    connection,"restaurant_id");

    }



}
