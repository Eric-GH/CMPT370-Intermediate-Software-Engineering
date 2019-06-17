package database;
/**
 * Create by Yinsheng Dong
 */
import Entities.OrderLine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderLineFunctions {

    private int order_id;
    private int dish_id;
    private int quanity;
    private float price_per_one;
    private float price_total;
    private float discount_total;
    private ArrayList<OrderLine> orderLines;
    private OrderLine orderLine;
    public String message;
    private int orderLineId;

    public void addOrderLine(OrderLine orderLine)
    {
        GoConnection connection = new GoConnection();

        //try and connect to our server, if the connection isn't null, procceed, else check exception from SQL api
        connection.connect();
        if (connection.coon != null)
        {
            try
            {
                //These lines setup SQL queries & check handle problematic cases using SQL API
                // Changed the total price that can be multiby quanity and unit price
                String addQuery = "INSERT INTO order_lines(order_id, dishes_id, quanity, price_per_one, price_total, discount_total)" +
                        "VALUES (?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.coon.prepareStatement(addQuery);
                preparedStatement.setInt(1,orderLine.getOrder_id());
                preparedStatement.setInt(2,orderLine.getDish_id());
                preparedStatement.setInt(3,orderLine.getQuanity());
                preparedStatement.setFloat(4,orderLine.getPrice_per_one());
                preparedStatement.setFloat(5,orderLine.getPrice_total());
                preparedStatement.setFloat(6,orderLine.getDiscount_total());
                int affect = preparedStatement.executeUpdate();
                if (affect > 0)
                {
                    message = "the order line has been added";
                    System.out.println(message);
                    connection.coon.close();
                }
                else
                {
                    message = "there is no order_line to add";
                    connection.coon.close();
                }
            }

            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void editOrderLine(int orderLine_id,OrderLine newOrderLine)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String query = "update order_lines set order_id = ?,\n" +
                        "dishes_id = ?,\n" +
                        "quanity = ?,\n" +
                        "price_per_one = ?,\n" +
                        "price_total = ?,\n" +
                        "discount_total = ?,\n" +
                        "where id = ?";
                PreparedStatement preparedStatement = connection.coon.prepareStatement(query);
                preparedStatement.setInt(1,newOrderLine.getOrder_id());
                preparedStatement.setInt(2,newOrderLine.getDish_id());
                preparedStatement.setInt(3,newOrderLine.getQuanity());
                preparedStatement.setFloat(4,newOrderLine.getPrice_per_one());
                preparedStatement.setFloat(5,newOrderLine.getPrice_total());
                preparedStatement.setFloat(6,newOrderLine.getDiscount_total());
                preparedStatement.setInt(7,orderLine_id);
                int value = preparedStatement.executeUpdate();
                if(value>0)
                {
                    message = "the order-line is updated";
                    connection.coon.close();
                }
                else
                {
                    message = "the order-line is not updated";
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


    public OrderLine setUpOrderLine(OrderLine orderLine)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String query = "select dish_prices from dishes where id="+ orderLine.getDish_id();
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet.next())
                {
                    float dish_price = resultSet.getFloat(1);
                    float price_total = dish_price * orderLine.getQuanity();
                    this.orderLine = new OrderLine(orderLine.getOrder_id(),orderLine.getDish_id(),orderLine.getQuanity(),orderLine.getDiscount_total());
                    this.orderLine.setPrice_per_one(dish_price);
                    this.orderLine.setPrice_total(price_total);
                    connection.coon.close();
                }
                else
                {
                    message = "Not found the dish";
                    connection.coon.close();
                }

            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
        return this.orderLine;
    }


    public int getOrderLineId(OrderLine orderLine)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try {
                String query = "select id from order_lines where order_id =? and dishes_id = ?";
                PreparedStatement preparedStatement = connection.coon.prepareStatement(query);
                preparedStatement.setInt(1,orderLine.getOrder_id());
                preparedStatement.setInt(2,orderLine.getDish_id());
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next())
                {
                    orderLineId = resultSet.getInt(1);
                    connection.coon.close();
                }
                else
                {
                    message = "not found the id from this orderline";
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
        return orderLineId;
    }


    public void deleteOrderLine(int orderLine_id)
    {
        GoConnection connection = new GoConnection();
        connection.connect();
        if(connection.coon!=null)
        {
            try{
                String query = "delete from order_lines where id = "+orderLine_id;
                Statement statement = connection.coon.createStatement();
                int value = statement.executeUpdate(query);
                if(value>0)
                {
                    message = "the order-line has been deleted";
                    connection.coon.close();
                }
                else
                {
                    message = "the order-line not been deleted";
                }
            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }
    }

    public ArrayList<OrderLine> getOrderLines(int order_id) {
        GoConnection connection = new GoConnection();
        connection.connect();
        orderLines = new ArrayList<>();
        if(connection.coon!=null)
        {
            try{
                String query = "select dishes_id, quanity, price_per_one, price_total, discount_total from order_lines where order_id="+order_id;
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next())
                {
                    this.order_id = order_id;
                    this.dish_id = resultSet.getInt(1);
                    this.quanity = resultSet.getInt(2);
                    this.price_per_one = resultSet.getFloat(3);
                    this.price_total = resultSet.getFloat(4);
                    this.discount_total = resultSet.getFloat(5);
                    orderLine = new OrderLine(this.order_id, this.dish_id,this.quanity,this.discount_total);
                    orderLine.setPrice_per_one(this.price_per_one);
                    orderLine.setPrice_total(this.price_total);
                    orderLines.add(orderLine);
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
        return orderLines;
    }

    public static void main(String arg[])
    {
        OrderLineFunctions orderLineFunctions = new OrderLineFunctions();
        System.out.println(orderLineFunctions.getOrderLines(1));
    }
}
