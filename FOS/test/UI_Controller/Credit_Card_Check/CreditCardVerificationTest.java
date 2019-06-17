package UI_Controller.Credit_Card_Check;

/**
 * Yuecheng  Rong
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class CreditCardVerificationTest {
    @Test
    public void checkValid() throws Exception {
        CreditCardVerification CCard = new CreditCardVerification("4234123412341234", "0218","123");
        CCard.checkValid(CCard);
        assertEquals(CCard.getResult(), true);
    }

}