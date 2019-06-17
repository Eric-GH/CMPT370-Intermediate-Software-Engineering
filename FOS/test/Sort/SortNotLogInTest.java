package Sort;

/**
 * Yuecheng  Rong
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class SortNotLogInTest {

    /** The test object */
    private SortNotLogIn sortNotLogIn = new SortNotLogIn("Saskatoon","Saskatchewan");

    /**
     * Test the function sort by waiting time: order by waiting time ASC
     * @throws Exception
     */
    @Test
    public void sortByWaitingTime() throws Exception {
        sortNotLogIn.sortByWaitingTime();
        assertEquals(sortNotLogIn.sortInfos.get(0).getRest_id(),1);
    }

    /**
     * Test the function sort by rate: order by rate ASC
     * @throws Exception
     */
    @Test
    public void sortByRate() throws Exception {
        sortNotLogIn.sortByRate();
        assertEquals(sortNotLogIn.sortInfos.get(2).getRest_id(),3);
    }

    /**
     * Test the function sort by price: order by average price ASC
     * @throws Exception
     */
    @Test
    public void sortByPrice() throws Exception {
        sortNotLogIn.sortByPrice();
        assertEquals(sortNotLogIn.sortInfos.get(1).getRest_id(),1);
    }

}