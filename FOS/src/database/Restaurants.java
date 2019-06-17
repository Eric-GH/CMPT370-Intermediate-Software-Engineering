package database;

/**
 * Refactored by Yuecheng Rong
 */

import Entities.Restaurant;

import java.sql.*;
import java.util.ArrayList;

public class Restaurants {

    /** The restaurant name */
        private String restaurant_name;
    /** The restaurant licence id */
        private String license_id;
    /** The restaurant opening time */
        private Time open_time;
    /** The restaurant close time */
        private Time close_time;
    /** The restaurant phone number */
        private String phone_num;
    /** The restaurant email address */
        private String e_mail_address;
    /** The restaurant waiting time */
        private Time waiting_time;
    /** The restaurant average price */
        private float avg_price;
    /** The error message */
        public String message;
    /** The Super array that contains restaurants */
        public ArrayList<Restaurant> displayRestaurants;
    /** The restaurant to be found */
        public Restaurant restaurant;
    /** The connection */
        private GoConnection connection = new GoConnection();


    /**
     * The skeleton of the addRestaurant and updateRestaurant since they are similar
     *
     * @param restaurant the restaurant to be added/updated
     * @param inputQuery the Query to be executed
     * @param which detect whether it is add or update
     * @param user_id if it is update then we need an user_id
     */
        private void restautantModify(Restaurant restaurant, String inputQuery, int which, int user_id){
            connection.connect();
            if(connection.coon!=null)
            {
                try {
                    PreparedStatement ppStmt = connection.coon.prepareStatement(inputQuery);
                    if (which == 1) {
                        this.setPrep(ppStmt, restaurant, which,1, 2, 3, 4, 5, 6, 7, 8, 9, user_id);
                    } else {
                        this.setPrep(ppStmt, restaurant, which,9, 1, 2, 3, 4, 5, 6, 7, 8, user_id);
                    }
                    int value = ppStmt.executeUpdate();
                    if(value > 0) {
                        message = "The restaurant information has been saved";
                        connection.coon.close();
                    }
                } catch (SQLException e) {
                    message = e.fillInStackTrace().toString();
                }
            } else {
                message = "lost connection";
            }
        }


    /**
     * Helper function that set the input preparation
     *
     * @param prep the input preparation
     * @param restaurant the resource
     * @param which detect whether it is add or update
     * @param first the first question mark
     * @param second the second question mark
     * @param third the third question mark
     * @param forth the forth question mark
     * @param fifth the fifth question mark
     * @param sixth the sixth question mark
     * @param seventh the seventh question mark
     * @param eighth the eighth question mark
     * @param ninth the ninth question mark
     * @param user_id if it is update then we need an user_id
     */
    private void setPrep(PreparedStatement prep, Restaurant restaurant,int which, int first, int second, int third, int forth, int fifth,
                         int sixth, int seventh, int eighth, int ninth, int user_id) {
        try {
            if (which == 1) {
                prep.setInt(first, restaurant.getUser_id());
            } else {
                prep.setInt(first, user_id);
            }
            prep.setString(second, restaurant.getRestaurant_name());
            prep.setString(third, restaurant.getLicense_id());
            prep.setTime(forth, restaurant.getOpen_time());
            prep.setTime(fifth, restaurant.getClose_time());
            prep.setString(sixth, restaurant.getPhone_num());
            prep.setString(seventh, restaurant.getE_mail_address());
            prep.setTime(eighth, restaurant.getWaiting_time());
            prep.setFloat(ninth, restaurant.getAvg_price());
        } catch (SQLException e) {
            message = e.fillInStackTrace().toString();
        }
    }


    /**
     * The skeleton of the displayAllRestaurant and getRestaurant since they are similar
     *
     * @param inputQuery the Query to be executed
     * @param which detect whether it is add or update
     * @param user_id if it is update then we need an user_id
     */
    private void RestautantGather(String inputQuery, int which, int user_id){
        connection.connect();
        if(connection.coon!=null)
        {
            try {
                Statement statement = connection.coon.createStatement();
                ResultSet resultSet = statement.executeQuery(inputQuery);

                while(resultSet.next())
                {
                    if(which == 1) {
                        setRestaurantHelper(resultSet, which, 1, 2, 3, 4, 5, 6, 7, 8, 9, user_id);
                    } else {
                        setRestaurantHelper(resultSet, which, 0, 1, 2, 3, 4, 5, 6, 7, 8, user_id);
                    }
                }
                connection.coon.close();

            }catch (SQLException e) {
                message = e.fillInStackTrace().toString();
            }
        }


    }


    /**
     * Helper function that set the Restaurant
     *
     * @param resultSet the input resultSet
     * @param which detect whether it is add or update
     * @param first the first question mark
     * @param second the second question mark
     * @param third the third question mark
     * @param forth the forth question mark
     * @param fifth the fifth question mark
     * @param sixth the sixth question mark
     * @param seventh the seventh question mark
     * @param eighth the eighth question mark
     * @param ninth the ninth question mark
     * @param user_id if it is update then we need an user_id
     */
    private void setRestaurantHelper(ResultSet resultSet, int which, int first, int second, int third, int forth, int fifth,
                         int sixth, int seventh, int eighth, int ninth, int user_id) {
        try {
            if (which == 1) {
                user_id = resultSet.getInt(first);
            }

            restaurant_name = resultSet.getString(second);
            license_id = resultSet.getString(third);
            open_time = resultSet.getTime(forth);
            close_time = resultSet.getTime(fifth);
            phone_num = resultSet.getString(sixth);
            e_mail_address = resultSet.getString(seventh);
            waiting_time = resultSet.getTime(eighth);
            avg_price = resultSet.getFloat(ninth);

            Restaurant temp = new Restaurant(user_id,restaurant_name,license_id,open_time,close_time,phone_num,e_mail_address);
            temp.setWaiting_time(waiting_time);
            temp.setAvg_price(avg_price);

            if (which == 1) {
                displayRestaurants.add(temp);
            } else {
                restaurant = temp;
            }

        } catch (SQLException e) {
            message = e.fillInStackTrace().toString();
        }
    }









    /**
     * Add a new restaurant into the database
     *
     * @param restaurant the new restaurant will be added into the database
     */
    public void addRestaurant(Restaurant restaurant) {
        String inputQuery = "insert into restaurants (user_id, restaurant_name, license_id, open_time, close_time,phone_num, e_mail_address, waiting_time, avg_price) values (?,?,?,?,?,?,?,?,?)";
        restautantModify(restaurant, inputQuery,1, 0);
    }

    /**
     * Update the information of an exist restaurant
     *
     * @param user_id the user_id of the restaurant
     * @param restaurant the restaurant to be modified
     */
    public void updateRestaurant(int user_id, Restaurant restaurant) {
        String inputQuery = "UPDATE restaurants set\n" +
                "restaurant_name = ?\n" +
                ",license_id = ?\n" +
                ",open_time = ?\n" +
                ",close_time = ?\n" +
                ",phone_num = ?\n" +
                ",e_mail_address=?\n" +
                ",waiting_time = ?\n" +
                ",avg_price = ?\n" +
                "WHERE user_id = ?";
        restautantModify(restaurant, inputQuery,2, user_id);
    }



    /**
     * Create an super array that contains all the restaurants
     * @return the super array
     */
    public ArrayList<Restaurant> displayAllRestaurants() {
        displayRestaurants = new ArrayList<>();
        String inputQuery = "select user_id, restaurant_name, license_id, open_time, close_time, phone_num, e_mail_address, waiting_time, avg_price \n" +
                "from restaurants";
        RestautantGather(inputQuery,1,0);
        return displayRestaurants;
    }

    /**
     * Get the restaurant information after restaurant manager log in
     * @param user_id the id of the manager
     * @return
     */
    public Restaurant getRestaurant(int user_id) {
        String inputQuery = "select restaurant_name, license_id, open_time, close_time, phone_num, e_mail_address, waiting_time, avg_price \n" +
                "from restaurants WHERE user_id = " + user_id;
        RestautantGather(inputQuery,2,user_id);
        return restaurant;
    }

    /**
     * Helper function that will print the Restaurant super array
     * @param input the Restaurant super array
     */
    private void printArray(ArrayList<Restaurant> input){
        for (int i=0; i<input.size(); i++){
            System.out.println(input.get(i));
        }
    }


}

