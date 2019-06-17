package database;
/**
 * Create by Yinsheng Dong
 */
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;

import org.joda.time.DateTime;
/**
public class AddCreditCardPaymentMethod {

    private GoConnection connection = new GoConnection();
    public String message = "";

    public void setCreditCardInfo(int payment_id, String card_num, String card_name, String card_code, Date effective_date, Date expiry_date)
    {
        connection.connect();
        float price = 0;
        CreditCardVerification checkCard = new CreditCardVerification(card_num, card_code, "credit", effective_date.toLocalDate(), expiry_date.toLocalDate());
        DateVerification dateVerification = new DateVerification(expiry_date.toLocalDate().toString());
        if(!dateVerification.isDateValid())
        {
            message = "the expiry_date can not be past";
            return;
        }
        if(!checkCard.isCCNumberValid(checkCard))
        {
            message = "the card number is not valid";
            return;
        }

        if(!checkCard.isDateValid(checkCard))
        {
            message = "the card date is not valid";
            return;
        }

        if (!checkCard.isCCVNumberValid(checkCard))
        {
            message = "the ccv code is not valid";
            return;
        }

        if(connection.coon!=null)
        {
            try{
                String getPriceQuery = "select price from payments where id = "+payment_id;
                Statement priceStmt = connection.coon.createStatement();
                ResultSet priceResult = priceStmt.executeQuery(getPriceQuery);
                if(priceResult.next())
                {
                    price = priceResult.getFloat(1);
                }
                else
                {
                    message = "Can not get the price from payment";
                    connection.coon.close();
                }
                String addQuery = "insert into card_payment (payment_id, card_num, card_name, card_code, payment_price, success," +
                        "card_effective_date, card_expiry_date) values (?,?,?,?,?,?,?,?)";
                PreparedStatement addStmt = connection.coon.prepareStatement(addQuery);
                addStmt.setInt(1,payment_id);
                addStmt.setString(2,card_name);
                addStmt.setString(3,card_num);
                addStmt.setString(4,card_code);
                addStmt.setFloat(5,price);
                addStmt.setString(6,"YES");
                addStmt.setDate(7,effective_date);
                addStmt.setDate(8,expiry_date);
                int checkValue = addStmt.executeUpdate();
                if(checkValue>0)
                {
                    message = "the credit card payment has been finished";
                    connection.coon.close();
                }
                else
                {
                    message = "the credit card payment has not been finished";
                    connection.coon.close();
                }




            }catch (SQLException e)
            {
                message = e.fillInStackTrace().toString();
            }
        }

    }

    public static void main(String arg[])
    {
        AddCreditCardPaymentMethod addCreditCardPaymentMethod = new AddCreditCardPaymentMethod();
        Date date = Date.valueOf(LocalDate.parse("11/20/15",DateTimeFormatter.ofPattern("MM/yy/dd")));
        String date1 = "11/16"+"/01";
        String date2 = "11/20"+"/01";



        addCreditCardPaymentMethod.setCreditCardInfo(3,"4510156017856666","Yinsheng Dong","100",Date.valueOf(LocalDate.parse(date1,DateTimeFormatter.ofPattern("MM/yy/dd"))),
                Date.valueOf(LocalDate.parse(date2,DateTimeFormatter.ofPattern("MM/yy/dd"))));
        System.out.println(addCreditCardPaymentMethod.message);

        System.out.println(date.toString());
    }

    }
}
**/