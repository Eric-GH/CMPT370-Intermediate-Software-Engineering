package database;
/**
 * Create by Yinsheng Dong
 */
import Entities.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LocationFunctions {

    private int user_id;
    private int house_num;
    private String street;
    private String city;
    private String province;
    private String postal_code;
    private ArrayList<Location> locations;
    public String message = "";


    public void addLocations(Location location)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String query = "insert into locations(user_id,house_num,street,city,province,post_code) values (?,?,?,?,?,?)";
                PreparedStatement statement = connection.coon.prepareStatement(query);
                statement.setInt(1,location.getUser_id());
                statement.setInt(2,location.getHouse_num());
                statement.setString(3,location.getStreet());
                statement.setString(4,location.getCity());
                statement.setString(5,location.getProvince());
                statement.setString(6,location.getPost_code());
                int judge = statement.executeUpdate();
                if(judge>0)
                {
                    message = "the location information has been saved";
                    connection.coon.close();
                }
                else
                {
                    message = "the location information has not been saved";
                    connection.coon.close();
                }
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }

        else {
            message = "lost connection";
        }
    }


    public void editLocations(int location_id, Location newLocation)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String query = "update locations\n" +
                        "set house_num = ?, \n" +
                        "set street = ?, \n" +
                        "set city = ?,\n" +
                        "set province = ?,\n" +
                        "set post_code = ?\n" +
                        "where location_id = ?";
                PreparedStatement preparedStatement = connection.coon.prepareStatement(query);
                preparedStatement.setInt(1, newLocation.getHouse_num());
                preparedStatement.setString(2,newLocation.getStreet());
                preparedStatement.setString(3,newLocation.getCity());
                preparedStatement.setString(4,newLocation.getProvince());
                preparedStatement.setString(5,newLocation.getPost_code());
                preparedStatement.setInt(6,location_id);
                int check = preparedStatement.executeUpdate();
                if(check>0)
                {
                    message = "the location info has been edited";
                    connection.coon.close();
                }
                else
                {
                    message = "the location info has not been edited";
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
    }


    public int getLocationId(Location location)
    {
        int location_id = 0;
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String query = "select id from locations where user_id = ?\n" +
                        "house_num = ?\n" +
                        "street = ?\n" +
                        "city = ?\n" +
                        "province=?\n" +
                        "post_code=?";
                PreparedStatement preparedStatement = connection.coon.prepareStatement(query);
                preparedStatement.setInt(1,location.getUser_id());
                preparedStatement.setInt(2,location.getHouse_num());
                preparedStatement.setString(3,location.getStreet());
                preparedStatement.setString(4,location.getProvince());
                preparedStatement.setString(5,location.getPost_code());
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next())
                {
                    location_id = resultSet.getInt(1);
                }
                else
                {
                    message = "Did not get the location id";
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
        return location_id;
    }

    public ArrayList<Location> getLocations(int user_id) {
        GoConnection connection = new GoConnection();
        connection.connect();
        locations = new ArrayList<>();
        if(connection.coon!=null)
        {
            try {
                this.user_id = user_id;
                String query = "select house_num, street, city, province, post_code from locations where user_id =" + user_id;
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next())
                {
                    house_num = resultSet.getInt(1);
                    street = resultSet.getString(2);
                    city = resultSet.getString(3);
                    province = resultSet.getString(4);
                    postal_code = resultSet.getString(5);
                    Location temp = new Location(this.user_id,house_num,street,city,province,postal_code);
                    locations.add(temp);

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
        return locations;
    }

    public static void main(String arg[])
    {
        LocationFunctions locationFunctions = new LocationFunctions();
        System.out.println(locationFunctions.getLocations(1));
    }
}
