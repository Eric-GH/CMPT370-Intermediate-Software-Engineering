package Sort;

/**
 * Yuecheng Rong
 */

import java.sql.Time;
import java.util.Comparator;

/**
 * This is the super array that will store all the necessary information of the restaruant after search.
 */
public class SortInfo implements Comparable {

    /** The initial number and string. */
    private final int InitialNumber = 0;
    private final String InitialString = "";
    private final String SPACE = " ";

    /** Distance between customer and restaurant. */
    private float distance = InitialNumber;

    /** The id of the restaurant been searched. */
    private int rest_id = InitialNumber;

    /** The name of the restaurant been searched. */
    private String rest_name = InitialString;

    /** The address of the restaurant been searched. */
    private String rest_address = InitialString;

    /** The average waiting time of the restaurant been searched. */
    private Time rest_time = null;

    /** The rate of the restaurant been searched. */
    private float rest_rate = InitialNumber;

    /** The average price of the restaurant been searched. */
    private float rest_avg_price = InitialNumber;

    /** The price of the dish been searched. */
    private int dish_price = InitialNumber;

    /** The full name of the dish been searched. */
    private String dish_name = SPACE;




    /**
     * Following are the functions that return the in-class private elements.
     */


    /** Return the distance of the restaurant. */
    public float getDistance() {
        return distance;
    }

    /** Return the id of the restaurant. */
    public int getRest_id() {
        return rest_id;
    }

    /** Return the name of the restaurant. */
    public String getRest_name() {
        return rest_name;
    }

    /** Return the address of the restaurant. */
    public String getRest_address() {
        return rest_address;
    }

    /** Return the service time of the restaurant . */
    public Time getRest_time() {
        return rest_time;
    }

    /** Return the rate of the restaurant . */
    public float getRest_rate() {
        return rest_rate;
    }

    /** Return the average price of the restaurant. */
    public float getRest_avg_price() {
        return rest_avg_price;
    }

    /** Return the price of the dish been searched. */
    public int getDish_price() { return dish_price; }

    /** Return the name of the dish been searched. */
    public String getDish_Name() { return dish_name; }


    /**
     * Following are the functions that set the in-class private elements.
     */



    /** Set the average price of the restaurant. */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /** Set the id of the restaurant. */
    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }

    /** Set the name of the restaurant. */
    public void setRest_name(String rest_name) {
        this.rest_name = rest_name;
    }

    /** Set the address of the restaurant. */
    public void setRest_address(String rest_address) {
        this.rest_address = rest_address;
    }

    /** Set the service time of the restaurant. */
    public void setRest_time(Time rest_time) {
        this.rest_time = rest_time;
    }

    /** Set the rate of the restaurant. */
    public void setRest_rate(float rest_rate) {
        this.rest_rate = rest_rate;
    }

    /** Set the average price of the restaurant. */
    public void setRest_avg_price(float rest_avg_price) {
        this.rest_avg_price = rest_avg_price;
    }

    /** Set the price of the dish been searched. */
    public void setDish_price(int dish_price) {
        this.dish_price = dish_price;
    }

    /** Set the name of the dish been searched. */
    public void setDish_Name(String dish_name) {
        this.dish_name = dish_name;
    }


    /**
     * The output of the super array.
     * @return the modified output
     */
    @Override
    public String toString()
    {
        return getRest_id() + SPACE +
                getRest_name() + SPACE +
                getRest_address() + SPACE +
                getRest_time()+ SPACE +
                getRest_rate()+ SPACE +
                getDistance()+ SPACE +
                getRest_avg_price();
    }

    /**
     * The output of the super array.
     * @return the modified output
     */
    public String dishToString()
    {
        return getRest_id() + SPACE +
                getRest_name() + SPACE +
                getRest_address() + SPACE +
                getRest_time()+ SPACE +
                getRest_rate()+ SPACE +
                getDistance()+ SPACE +
                getDish_Name() + SPACE +
                getDish_price();
    }


    /**
     * Following are the comparator override functions.
     */


    /** Override the compareTo. */
    @Override
    public int compareTo(Object o) {
        return InitialNumber;
    }

    /** Create the Distance Comparator that compares the distance between two super arrays. */
    public static Comparator<SortInfo> distanceComparator = new Comparator<SortInfo>() {
        @Override
        public int compare(SortInfo o1, SortInfo o2) {
            return doCompare(o1.getDistance(), o2.getDistance());
        }
    };

    /** Create the Distance Comparator that compares the rate between two super arrays. */
    public static Comparator<SortInfo> rateComparator = new Comparator<SortInfo>() {
        @Override
        public int compare(SortInfo o1, SortInfo o2) {
            return doCompare(o1.getRest_rate(), o2.getRest_rate());
        }
    };

    /** Create the Distance Comparator that compares the price between two super arrays. */
    public static Comparator<SortInfo> priceComparator = new Comparator<SortInfo>() {
        @Override
        public int compare(SortInfo o1, SortInfo o2) {
            return doCompare(o1.getRest_avg_price(), o2.getRest_avg_price());
        }
    };

    /** Create the Distance Comparator that compares the service time between two super arrays. */
    public static Comparator<SortInfo> timeComparator = new Comparator<SortInfo>() {
        @Override
        public int compare(SortInfo o1, SortInfo o2) {
            return doCompareTime(o1.getRest_time(), o2.getRest_time());
        }
    };

    /** Create the Distance Comparator that compares the service time between two super arrays. */
    public static Comparator<SortInfo> dishPriceComparator = new Comparator<SortInfo>() {
        @Override
        public int compare(SortInfo o1, SortInfo o2) {
            return doCompare((float)o1.getDish_price(), (float)o2.getDish_price());
        }
    };

    /** Constructor when Restaurant-search only */
    public SortInfo(int rest_id, String rest_name, String rest_address, Time rest_time, float rest_rate, float rest_avg_price)
    {
        this.setRest_id(rest_id);
        this.setRest_name(rest_name);
        this.setRest_address(rest_address);
        this.setRest_time(rest_time);
        this.setRest_rate(rest_rate);
        this.setRest_avg_price(rest_avg_price);
    }

    /** Constructor when search dish-name */
    public SortInfo(int rest_id, String rest_name, String rest_address, Time rest_time,
                    float rest_rate, float rest_avg_price, int dish_price, String dish_name)
    {
        this.setRest_id(rest_id);
        this.setRest_name(rest_name);
        this.setRest_address(rest_address);
        this.setRest_time(rest_time);
        this.setRest_rate(rest_rate);
        this.setRest_avg_price(rest_avg_price);
        this.setDish_price(dish_price);
        this.setDish_Name(dish_name);
    }

    /** Constructor */
    public SortInfo(){
    }


    /**
     * Following are the help functions.
     */


    /** doCompare between two Float numbers */
    private static int doCompare(Float o1, Float o2) {
        return o1.compareTo(o2);
    }

    /** doCompare between two Time numbers */
    private static int doCompareTime(Time o1, Time o2) {
        return o1.compareTo(o2);
    }



}
