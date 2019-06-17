package Sort;

/**
 * Yuecheng  Rong
 */

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Distance will be calculated everytime so will be slow, please be patient
 */
public class SortLoginTest {

    /** The test object */
    private SortLogin sortLogin = new SortLogin(2);

    /**
     * Test the function sort by price: order by average price ASC
     * @throws Exception
     */
     @Test
     public void sortByPrice() throws Exception {
     sortLogin.sortByPrice();
     assertEquals(sortLogin.sortInfos.get(0).getRest_id(),9);
     }

    /**
     * Test the function sort by rate: order by rate ASC
     * @throws Exception
     */
    @Test
    public void sortByRate() throws Exception {
        sortLogin.sortByRate();
        assertEquals(sortLogin.sortInfos.get(0).getRest_id(),10);
    }

    /**
     * Test the function sort by waiting time: order by waiting time ASC
     * @throws Exception
     */
    @Test
    public void sortByWaitingTime() throws Exception {
        sortLogin.sortByWaitingTime(12);
        assertEquals(sortLogin.sortInfos.get(0).getRest_id(),1);
    }

    /**
     * Test the function sort by distance: order by the distance between customer and restaurant ASC
     * Only works when customers are logged in
     * @throws Exception
     */
    @Test
    public void sortByDistance() throws Exception {
        sortLogin.sortByDistance();
        assertEquals(sortLogin.sortInfos.get(0).getRest_id(),1);
    }
}