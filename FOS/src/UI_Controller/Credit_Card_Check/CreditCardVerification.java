package UI_Controller.Credit_Card_Check;
/**
 * Created by ror716 on 2017-10-11.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a class to verify the validity of Expiry Date of the Credit Card
 * entered during payment.
 */
public class CreditCardVerification
{
    /** User's Credit Card number. */
    private String CCN_Number;

    /** User's Credit Card CVV code. */
    private String CVV_Number;

    /** Current date of day the program is being ran. */
    private Date todayDate;

    /** Users Credit Card expiry date. */
    private Date userDate;

    /** A string that carries all exception messages. */
    public String message = "Correct card message";

    /** Standard Credit Card number length, which is 16. */
    private final int CCNumberDigit = 16;

    /** Standard CVV code length, which is 3. */
    private final int CVVDigits = 3;

    /** The boolean that stores whether the input card is valid or not */
    private boolean result = true;

    /** Public function that can get result in other classes */
    public boolean getResult() {
        return result;
    }

    /**
     * Constructor
     *
     * @param number The Credit Card number entered by user
     * @param expiry The expiry date entered by the user
     * @param code The CVV code entered by user
     */
    public CreditCardVerification(String number, String expiry, String code)
    {

        this.CCN_Number = number;
        this.CVV_Number = code;
        this.todayDate = new Date();
        this.checkDateFormat(expiry);
    }

    /**
     * This function checks whether the input credit card is vaild or not.
     *
     * @param C The input credit card
     * @return true if it is valid; Else false
     */
    public boolean checkValid(CreditCardVerification C) {
        // If Expire date format is invalid, return false
        if (!result) {
            return false;
        }
        // Check the CCN number
        checkHelper(C.CCN_Number, CCNumberDigit, "Error: CCN incorrect");
        // Check the CVV code
        checkHelper(C.CVV_Number, CVVDigits, "Error: CVV number incorrect");
        // Check the Expire date
        checkDate("Error: Expire Date Invalid");
        // If passed all the tests, the input credit card is valid
        return result;
    }


    /**
     * Check the input card CCN and CVV number is valid or not
     *
     * @param firstInput the length of the CCN/CVV number
     * @param secondInput the correct length of CNN/CCV number
     * @param inputString the error message
     */
    private void checkHelper(String firstInput, int secondInput, String inputString) {

        // check whether the input number is integer/long or not.
        try {
            Long.parseLong(firstInput);
        } catch(NumberFormatException e) {
            execute(inputString);
        }
        // check whether the length of the input CVV/CCN number is valid or not.
        if (firstInput.length() != secondInput) {
            execute(inputString);
        }
    }

    /**
     * Check whether the input Expire date is correct or not
     *
     * @param inputString the error message
     */
    private void checkDate(String inputString) {
        if (todayDate.after(userDate)) {
            execute(inputString);
        }
    }

    /**
     * Check the format of the Expire date
     *
     * @param expiry the input Expire date
     */
    private void checkDateFormat(String expiry) {
        try {
            this.userDate = new SimpleDateFormat("MMyy").parse(expiry);
        } catch (ParseException e) {
            execute("EXPIRY DATE ENTERED IS EITHER INVALID OR WRONG FORMAT");
        }
    }

    /**
     * change message and result if some numbers are invalid
     */
    private void execute(String inputString) {
        message = inputString;
        result =false;
    }
}
