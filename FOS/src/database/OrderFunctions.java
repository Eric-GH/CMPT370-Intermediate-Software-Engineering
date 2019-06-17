package database;
/**
 * Create by Yinsheng Dong
 */

import Entities.Order;
import java.sql.*;
import java.util.ArrayList;

public class OrderFunctions {
    private Date order_date;
    private Time order_time;
    private int customer_id;
    private int restaurant_id;
    private float subtotal;
    private float pst_tax;
    private float gst_tax;
    private float total_discount;
    private Time estimate_preparation_time;
    private int order_id;
    public String message;
    private Order history_order;
    private ArrayList<Order> historyOrders;

    public void addOrder(Order order) {
        GoConnection connection = new GoConnection();
        connection.connect();
        if (connection.coon != null) {
            try {
                String query = "insert into orders (order_date, order_time, customer_id, restaurant_id, subtotal, pst_tax,\n" +
                        "gst_tax, total_discount, estimate_preparation_time) values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.coon.prepareStatement(query);
                preparedStatement.setDate(1, order.getOrder_date());
                preparedStatement.setTime(2, order.getOrder_time());
                preparedStatement.setInt(3, order.getCustomer_id());
                preparedStatement.setInt(4, order.getRestaurant_id());
                preparedStatement.setFloat(5, order.getSubtotal());
                preparedStatement.setFloat(6, order.getPst_tax());
                preparedStatement.setFloat(7, order.getGst_tax());
                preparedStatement.setFloat(8, order.getTotal_discount());
                preparedStatement.setTime(9, order.getEstimate_preparation_time());

                int value = preparedStatement.executeUpdate();
                if (value > 0) {
                    message = "the order has been added";
                    connection.coon.close();
                } else {
                    message = "the order has not been added";
                    connection.coon.close();
                }
            } catch (SQLException e) {
                message = e.fillInStackTrace().toString();
            }
        } else {
            message = "lost connection";
        }
    }

    public void updateOrder(int order_id, Order newOrder) {
        GoConnection connection = new GoConnection();
        connection.connect();
        if (connection.coon != null) {
            try {
                String query = "update orders set order_date = ?, \n" +
                        "order_time = ?,\n " +
                        "customer_id = ?,\n" +
                        "restaurant_id = ?,\n" +
                        "subtotal = ?,\n" +
                        "pst_tax = ?, \n" +
                        "total_discount = ?,\n" +
                        "estimate_preparation_time = ?\n" +
                        "where id = ?";
                PreparedStatement preparedStatement = connection.coon.prepareStatement(query);
                preparedStatement.setDate(1, newOrder.getOrder_date());
                preparedStatement.setTime(2, newOrder.getOrder_time());
                preparedStatement.setInt(3, newOrder.getCustomer_id());
                preparedStatement.setInt(4, newOrder.getRestaurant_id());
                preparedStatement.setFloat(5, newOrder.getSubtotal());
                preparedStatement.setFloat(6, newOrder.getPst_tax());
                preparedStatement.setFloat(7, newOrder.getGst_tax());
                preparedStatement.setTime(8, newOrder.getEstimate_preparation_time());
                preparedStatement.setInt(9, order_id);
                int value = preparedStatement.executeUpdate();
                if (value > 0) {
                    message = "the order info has been updated";
                } else {
                    message = "the order info has not been updated";
                }
                connection.coon.close();
            } catch (SQLException e) {
                message = e.fillInStackTrace().toString();
            }
        } else {
            message = "lost connection";
        }
    }

    public int getOrderId(Order order) {
        GoConnection connection = new GoConnection();
        connection.connect();
        if (connection.coon != null) {
            try {
                String query = "select id from orders where order_date = ?\n" +
                        "and order_time = ?\n" +
                        "and customer_id = ? \n" +
                        "and restaurant_id = ?";
                PreparedStatement preparedStatement = connection.coon.prepareStatement(query);
                preparedStatement.setDate(1, order.getOrder_date());
                preparedStatement.setTime(2, order.getOrder_time());
                preparedStatement.setInt(3, order.getCustomer_id());
                preparedStatement.setInt(4, order.getRestaurant_id());

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    order_id = resultSet.getInt(1);
                    connection.coon.close();
                } else {
                    message = "not found the order id in this order";
                    connection.coon.close();
                }
            } catch (SQLException e) {
                message = e.fillInStackTrace().toString();
            }
        } else {
            message = "lost connection";
        }
        return order_id;
    }


    public void deleteOrder(int order_id) {
        GoConnection connection = new GoConnection();
        connection.connect();
        if (connection.coon != null) {
            try {
                String query = "delete from orders where id =" + order_id;
                Statement statement = connection.coon.createStatement();
                int value = statement.executeUpdate(query);
                if (value > 0) {
                    message = "the order has been deleted";
                    connection.coon.close();
                } else {
                    message = "the order has not been deleted";
                    connection.coon.close();
                }
            } catch (SQLException e) {
                message = e.fillInStackTrace().toString();
            }
        } else {
            message = "lost connection";
        }
    }

    public ArrayList<Order> getHistoryOrdersFromCustomers(int customer_id) {

        GoConnection connection = new GoConnection();
        connection.connect();
        historyOrders = new ArrayList<>();
        if (connection.coon != null) {
            try {
                String query = "select order_date, order_time, restaurant_id, subtotal, pst_tax, gst_tax, total_discount, estimate_preparation_time\n" +
                        "from orders where customer_id=" + customer_id;
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    order_date = resultSet.getDate(1);
                    order_time = resultSet.getTime(2);
                    restaurant_id = resultSet.getInt(3);
                    subtotal = resultSet.getFloat(4);
                    pst_tax = resultSet.getFloat(5);
                    gst_tax = resultSet.getFloat(6);
                    total_discount = resultSet.getFloat(7);
                    estimate_preparation_time = resultSet.getTime(8);
                    history_order = new Order(order_date, order_time, customer_id, restaurant_id);
                    history_order.setSubtotal(subtotal);
                    history_order.setPst_tax(pst_tax);
                    history_order.setGst_tax(gst_tax);
                    history_order.setTotal_discount(total_discount);
                    history_order.setEstimate_preparation_time(estimate_preparation_time);

                    historyOrders.add(history_order);
                }

                connection.coon.close();
            } catch (SQLException e) {
                message = e.fillInStackTrace().toString();
            }
        }
        return historyOrders;
    }


    public ArrayList<Order> getHistoryOrdersFromRestaurants(int restaurant_id) {

        GoConnection connection = new GoConnection();
        connection.connect();
        historyOrders = new ArrayList<>();
        if (connection.coon != null) {
            try {
                String query = "select order_date, order_time, customer_id, subtotal, pst_tax, gst_tax, total_discount, estimate_preparation_time\n" +
                        "from orders where restaruant_id=" + restaurant_id;
                Statement statement = connection.coon.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    order_date = rs.getDate(1);
                    order_time = rs.getTime(2);
                    customer_id = rs.getInt(3);
                    subtotal = rs.getFloat(4);
                    pst_tax = rs.getFloat(5);
                    gst_tax = rs.getFloat(6);
                    total_discount = rs.getFloat(7);
                    estimate_preparation_time = rs.getTime(8);
                    history_order = new Order(order_date, order_time, customer_id, restaurant_id);
                    history_order.setSubtotal(subtotal);
                    history_order.setPst_tax(pst_tax);
                    history_order.setGst_tax(gst_tax);
                    history_order.setTotal_discount(total_discount);
                    history_order.setEstimate_preparation_time(estimate_preparation_time);

                    historyOrders.add(history_order);
                }

                connection.coon.close();
            } catch (SQLException e) {
                message = e.fillInStackTrace().toString();
            }
        }
        return historyOrders;
    }

    public static void main(String arg[])
    {
        OrderFunctions orderFunctions = new OrderFunctions();
       System.out.println(orderFunctions.getHistoryOrdersFromRestaurants(7));
    }
}
