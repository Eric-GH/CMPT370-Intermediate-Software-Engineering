package Sort;

/**
  * Pre-version: Yuecheng Rong
 * Created By Yinsheng Dong
 */

import google.GetDistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;

/**
 * This is the function of sort with customer log-in
 */
public class SortLogin extends SortBy {

    /** The distance between customer and the restaurant */
    private float distance = 0;

    /** Call the GetDistance function to generate the distance */
    private GetDistance getDistance = new GetDistance();

    /** The address of the customer */
    private String getCustomerAddress = "";


    /** This is the Query need to be executed if sort by rate */
    String NewQuery = "select r.id, \n" +
            "r.restaurant_name,\n" +
            "l.house_num, \n" +
            "l.street, \n" +
            "l.city,\n" +
            "l.province,\n" +
            "r.waiting_time,\n" +
            "rv.stars, \n" +
            "r.avg_price \n"+
            "from restaurants r\n" +
            "LEFT JOIN locations l ON  r.user_id = l.user_id\n" +
            "LEFT JOIN reviews rv ON  rv.restaurant_id = r.id";

    /**
     * Constructor
     */
    public SortLogin(int customer_id) {
        super();
        this.setQuery(NewQuery);
        this.doSort(null,null,customer_id);
    }

    /**
     * This is a helper function to change the statement of Query
     *
     * @param city not necessary here
     * @param province not necessary here
     */
    @Override
    public void inputSituation(String city, String province) {
        try {
            Statement statement = connection.coon.createStatement();
            resultSet = statement.executeQuery(Query);
        } catch (SQLException e) {
            message = e.fillInStackTrace().toString();
        }
    }

    /**
     * This is a helper function to generate the distance if customer is logged in
     *
     * @param customer_id the logged in customer's id
     */
    @Override
    public void afterLogin(int customer_id){
        try {
            String query1 = "select l.house_num, l.street, l.city, l.province from locations l, customers c\n" +
                    "where l.user_id = (select c.user_id where c.id = ?)";
            PreparedStatement ppsmt = connection.coon.prepareStatement(query1);
            ppsmt.setInt(1, customer_id);
            ResultSet resultSet1 = ppsmt.executeQuery();
            if (resultSet1.next()) {
                getCustomerAddress = resultSet1.getInt(1) +
                        resultSet1.getString(2) +
                        resultSet1.getString(3) +
                        resultSet1.getString(4);
            } else {
                message = "not found this customer";
            }
            getTheDistance();
        } catch (SQLException e) {
            message = e.fillInStackTrace().toString();
        }
    }

    /**
     * Helper function that can get the distance between customer and restaurant
     * and store the distance into the super array
     */
    private void getTheDistance() {
        for (int i = 0; i < sortInfos.size(); i++) {
            distance = getDistance.getFloatDistance(getCustomerAddress, sortInfos.get(i).getRest_address());
            sortInfos.get(i).setDistance(distance);
        }
    }

    /**
     * The main function of sort by average price.
     */
    public void sortByPrice() {
        Collections.sort(sortInfos,SortInfo.priceComparator);
    }

    /**
     * The main function of sort by Rate
     */
    public void sortByRate() {
        Collections.sort(sortInfos,SortInfo.rateComparator);
    }


    /**
     * The main function of sort by waiting time.
     */
    public void sortByWaitingTime(int customer_id) {
        String Temp = Query;
        String query = "select\n" +
                "r.id AS rest_id,\n" +
                "r.restaurant_name AS rest_name,\n" +
                "l.house_num AS rest_house_num,\n" +
                "l.street AS rest_street,\n" +
                "l.city AS rest_city,\n" +
                "l.province AS rest_province,\n"+
                "r.waiting_time AS rest_wait_time,\n" +
                "rv.stars AS rest_review,\n" +
                "r.avg_price AS rest_avg_price\n"+
                "from restaurants r\n" +
                "left join locations l on r.user_id = l.user_id\n" +
                "left join reviews rv on rv.restaurant_id = r.id\n" +
                "GROUP BY rest_id,rest_name, rest_house_num, rest_street, rest_city, rest_province, rest_wait_time,\n" +
                "rest_review, rest_avg_price\n" +
                "ORDER BY r.waiting_time ASC";
        this.setQuery(query);
        this.doSort(null, null,customer_id);
        this.setQuery(Temp);
    }

    /**
     * The main function of sort by Rate
     */
    public void sortByDistance() {
        Collections.sort(sortInfos, SortInfo.distanceComparator);
    }



/**
    public static void main(String arg[])
    {
        SortLogin sortLogIn = new SortLogin(2);
         sortLogIn.sortByWaitingTime(2);
         sortLogIn.printSuperArray();
        System.out.println("==========================");
        sortLogIn.sortByRate();
        sortLogIn.printSuperArray();
        System.out.println("==========================");
        sortLogIn.sortByPrice();
        sortLogIn.printSuperArray();
        System.out.println("==========================");
        sortLogIn.sortByDistance();
        sortLogIn.printSuperArray();
    }
 */


}
