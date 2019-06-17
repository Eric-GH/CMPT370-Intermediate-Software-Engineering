package database;
/**
 * Create by Yinsheng Dong
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create by Yinsheng Dong (yid164) at Nov 21
 */
public class ChangeUserPassword
{

    public String message = "";
    public String verifyMessage ="";
    public void changePassword(int user_id, String new_password)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null) {
            try {
                String query = "UPDATE users SET passwords = ? WHERE id = ?";
                PreparedStatement ppstmt = connection.coon.prepareStatement(query);
                ppstmt.setString(1, new_password);
                ppstmt.setInt(2, user_id);
                int value = ppstmt.executeUpdate();
                if (value > 0) {
                    this.changeText(1,"The user password has been changed");
                } else {
                    this.changeText(1,"The password has been not been changed");
                }
                connection.coon.close();
            } catch (SQLException e) {
                this.changeText(1,e.fillInStackTrace().toString());
            }
        } else {
            this.changeText(1,"loose connection");
        }
    }

    public void verifyPassword(int user_id, String old_password)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        String op;
        if(connection.coon != null) {
            try {
                String query = "SELECT passwords FROM users WHERE id="+user_id;
                Statement stmt = connection.coon.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);
                if(resultSet.next()) {
                    op = resultSet.getString(1);
                    if(op.equals(old_password)) {
                        this.changeText(2,"Y");
                    } else {
                        this.changeText(2,"N");
                    }
                } else {
                    this.changeText(1,"can not find this user");
                }
                connection.coon.close();
            } catch (SQLException e) {
                this.changeText(1,e.fillInStackTrace().toString());
            }
        }
    }

    /**
     * Helper function to change the information and verify message
     * @param which what is the object
     * @param input the new text
     */
    private void changeText(int which, String input){
        // If which = 1, then it is the message need to be changed
        if (which == 1){
            this.message = input;
        } else {
            // else, it is the verify message need to be changed
            this.verifyMessage = input;
        }
    }


    public static void main(String arg[])
    {
        ChangeUserPassword changeUserPassword = new ChangeUserPassword();
        /**
        changeUserPassword.verifyPassword(1,"ayden");
         **/
        changeUserPassword.changePassword(1,"12345");
        System.out.println(changeUserPassword.verifyMessage);
        System.out.println(changeUserPassword.message);
    }
}
