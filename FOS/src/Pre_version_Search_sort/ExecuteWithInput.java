package Pre_version_Search_sort;

import database.GoConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * This function is called when the search object is not empty.
 */
public class ExecuteWithInput
{

    public ArrayList<Integer> execute(String Query, GoConnection connection, String search, String getInt)
    {
        ArrayList<Integer> k = new ArrayList<>();

        try
        {
            // What will be returned
            PreparedStatement ppstmt = connection.coon.prepareStatement(Query);
            // The first question mark
            ppstmt.setString(1,search);
            // execute the query. rs = what is returned
            ResultSet rs = ppstmt.executeQuery();

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
     * This function generate the complete name of the food the customer searched
     *
     * @param Query Query to be executed
     * @param connection
     * @param search input from UI
     * @param getString what need to be generated
     * @return the String generated from database
     */
    public ArrayList<String> getName(String Query, GoConnection connection, String search, String getString)
    {
        ArrayList<String> k = new ArrayList<>();

        try
        {
            // What will be returned
            PreparedStatement ppstmt = connection.coon.prepareStatement(Query);
            // The first question mark
            ppstmt.setString(1,search);
            // execute the query. rs = what is returned
            ResultSet rs = ppstmt.executeQuery();

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


    /**
     *  This is only a temp function that generate java.long
     *  Complete project does not need this function
     * @param Query
     * @param connection
     * @param search
     * @return
     */

    public ArrayList<Long> getLong(String Query, GoConnection connection, String search, String getInt)
    {
        ArrayList<Long> k = new ArrayList<>();

        try
        {
            // What will be returned
            PreparedStatement ppstmt = connection.coon.prepareStatement(Query);
            // The first question mark
            ppstmt.setString(1,search);
            // execute the query. rs = what is returned
            ResultSet rs = ppstmt.executeQuery();

            if(rs.next())
            {
                k.add(rs.getLong(getInt));
                while (rs.next())
                {
                    k.add(rs.getLong(getInt));
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

}
