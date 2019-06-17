package Sort;

/**
 * Pre-version: Yuecheng Rong
 * Created By Yinsheng Dong
 */

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;

/**
 * This is the function of sort without customer log-in
 */
public class SortNotLogIn extends SortBy {

    /** This is the Query need to be executed if sort by rate */
    private String NewQuery = "select r.id, r.restaurant_name, l.house_num, l.street, l.city,l.province, r.waiting_time, rv.stars, r.avg_price \n" +
            "                        from restaurants r\n" +
            "                        left join locations l on l.user_id = r.user_id\n" +
            "                        left join reviews rv on rv.restaurant_id = r.id\n" +
            "                        WHERE l.city = ?\n" +
            "                        AND l.province = ?\n";

    String city;
    String province;

    /**
     * Constructor
     */
    public SortNotLogIn (String City, String Province) {
        super();
        this.setQuery(NewQuery);
        this.city = City;
        this.province = Province;
        this.doSort(City,Province,0);
    }


    /**
     * This is a helper function to change the statement of Query
     *
     * @param city the city of the customer lives
     * @param province the province of the customer lives
     */
    @Override
    public void inputSituation(String city, String province) {
        try {
            PreparedStatement statement = connection.coon.prepareStatement(Query);
            statement.setString(1, city);
            statement.setString(2, province);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            message = e.fillInStackTrace().toString();
        }
    }


    /**
     * The main function of sort by waiting time.
     */
    public void sortByWaitingTime() {
        String q = "select r.id, r.restaurant_name, l.house_num, l.street, l.city,l.province, r.waiting_time, rv.stars, r.avg_price \n" +
                "                        from restaurants r\n" +
                "                        left join locations l on l.user_id = r.user_id\n" +
                "                        left join reviews rv on rv.restaurant_id = r.id\n" +
                "                        WHERE l.city = ?\n" +
                "                        AND l.province = ?\n" +
                "                        GROUP BY r.id, r.restaurant_name, l.house_num, l.street, l.city,l.province, r.waiting_time, rv.stars, r.avg_price\n" +
                "                        ORDER BY r.waiting_time ASC";
        this.setQuery(q);
        this.doSort(city,province,0);
        Collections.sort(sortInfos, SortInfo.timeComparator);
    }

    /**
     * The main function of sort by rate.
     */
    public void sortByRate() {
        Collections.sort(sortInfos, SortInfo.rateComparator);

    }

    /**
     * The main function of sort by average price.
     */
    public void sortByPrice() {
        Collections.sort(sortInfos,SortInfo.priceComparator);
    }


    public static void main(String arg[])
    {
        SortNotLogIn sortNotLogIn = new SortNotLogIn("Saskatoon","Saskatchewan");
        sortNotLogIn.sortByRate();
        sortNotLogIn.printSuperArray();
        System.out.println("==========================");
        sortNotLogIn.sortByPrice();
        sortNotLogIn.printSuperArray();
        System.out.println("==========================");
        sortNotLogIn.sortByWaitingTime();
        sortNotLogIn.printSuperArray();
    }

}