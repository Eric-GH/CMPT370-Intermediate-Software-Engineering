package database;

import Entities.Dish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DishFunctions {
    public String message;
    private int menu_id;
    private String dish_name;
    private float dish_prices;
    private ArrayList<Dish> dishes;
    Dish dish;

    public void addDish(Dish dish)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String query = "insert into dishes (menus_id, dish_name, dish_prices) values(?,?,?)";
                PreparedStatement preparedStatement = connection.coon.prepareStatement(query);
                preparedStatement.setInt(1, dish.getMenus_id());
                preparedStatement.setString(2,dish.getDish_name());
                preparedStatement.setFloat(3,dish.getDish_prices());
                int value = preparedStatement.executeUpdate();
                if(value>0)
                {
                    message = "The menu information has been saved";
                    connection.coon.close();
                }
                else
                {
                    message = "There no menu id to this";
                    connection.coon.close();
                }

            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
        else{
            message = "lost connection";
        }
    }

    public void editDishInfo(int dish_id,Dish newDish)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try {
                String query = "update dishes set menus_id = ?, dish_name = ?, dish_prices = ? where id = ?";
                PreparedStatement preparedStatement = connection.coon.prepareStatement(query);
                preparedStatement.setInt(1,newDish.getMenus_id());
                preparedStatement.setString(2,newDish.getDish_name());
                preparedStatement.setFloat(3,newDish.getDish_prices());
                preparedStatement.setInt(4,dish_id);
                int value = preparedStatement.executeUpdate();
                if(value>0)
                {
                    message = "the dish information has been updated";
                    connection.coon.close();
                }
                else {
                    message = "something wrong on the updating";
                    connection.coon.close();
                }
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
    }

    public ArrayList<Dish> getDishes(int menu_id) {
        GoConnection connection = new GoConnection();
        connection.connect();
        dishes = new ArrayList<>();
        if(connection.coon!=null)
        {
            try{
                String query = "select dish_name, dish_prices from dishes where menus_id="+menu_id;
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next())
                {
                    dish_name = resultSet.getString(1);
                    dish_prices = resultSet.getFloat(2);
                    this.menu_id = menu_id;
                    dish = new Dish(menu_id,dish_name, dish_prices);
                    dishes.add(dish);

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
        return dishes;
    }

    public static void main(String arg[])
    {
        Dish dish = new Dish(34,"Spicy Colonel’s Original",5);
        DishFunctions dishFunctions = new DishFunctions();
        dishFunctions.addDish(dish);
        System.out.println(dishFunctions.message);
    }

}
