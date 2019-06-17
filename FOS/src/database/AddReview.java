package database;
/**
 * Create by Yinsheng Dong
 */
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddReview
{

    GoConnection connection = new GoConnection();
    public String message = "";

    public void setReviewInfo(int customer_id, int restaurant_id, int payment_id, float stars, String comment)
    {
        connection.connect();
        if(connection.coon!=null)
        {
            try
            {


                String setViewQuery = "insert into reviews (customer_id, restaurant_id, payment_id, stars, comments)" +
                                      "values (?,?,?,?,?)";
                PreparedStatement ppst = connection.coon.prepareStatement(setViewQuery);
                ppst.setInt(1, customer_id);
                ppst.setInt(2, restaurant_id);
                ppst.setInt(3, payment_id);
                ppst.setFloat(4, stars);
                ppst.setString(5, comment);
                int affected = ppst.executeUpdate();
                if(affected > 0)
                {
                    message = "The review information has been saved";
                    connection.coon.close();
                }
                else
                {
                    message = "The review information not been saved";
                }
            }
            catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
        else
        {
            message = "Lost connection";
        }
    }
    public static void main(String arg[])
    {
        AddReview addReview = new AddReview();
        addReview.setReviewInfo(1,10,3,2, "gen");
        System.out.println(addReview.message);
    }

}
