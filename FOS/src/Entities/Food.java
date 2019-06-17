/**
 * Created by Josh on October 22nd
 */


package Entities;

import sun.applet.Main;

import java.time.Duration;
import java.util.IllegalFormatCodePointException;

/**
 * Created by Kocur on 2017-10-22.
 * Modified by Yinsheng on 2017-12-3
 */
public class Food
{

    private String name;
    private double price;
    private Duration prepTime;
    private final int dishesID;

    public Food(String foodName, double foodPrice, Duration expectedPrepTime, int ID )
    {
        this.name = foodName;
        this.price = foodPrice;
        this.prepTime = expectedPrepTime;
        this.dishesID = ID;
    }


    /**
     * IN: Nothing
     * OUT: Returns the dishesID for the food object
     */
    public int getDishesID()
    {
        return this.dishesID;
    }


    /**
     * IN: The new price to be added to the food item
     * OUT: Nothing, the food's price is updated
     * Throws "IllegalArgumentException if the price is less than free.
    */
    public void setPrice(double newPrice) throws IllegalArgumentException
    {
        if(newPrice < 0)
        {
            throw new IllegalArgumentException("Error:  You entered a negative price");
        }
        else
        {
            this.price = newPrice;
        }
    }

    /**
     * IN:  Nothing
     * OUT: THe price for a particular food item is returned
     */
    public double getPrice()
    {
        return this.price;
    }

    /**
     * IN: The new name of the food item
     * Out: Nothing, the database will have the new name of the food
     */
    public void setName(String newName)
    {
        this.name = newName;
    }

    /**
     * IN: Nothing
     * Out: Returns the name of a particular food item
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * IN: New prep time for a food item
     * OUT: nothing, function returns void
     */
    public void setPrepTime(Duration newPrepTime )
    {
        this.prepTime = newPrepTime;
    }

    /**
     * IN: Nothing
     * Out: Returns the prep time for a given food item
     */
    public String getPrepTime()
    {
        return this.prepTime.toString();
    }

    //Basic testing for simple "accessors" & "mutators"
    public static void main(String[] args)
    {
        Food tendies = new Food("Tendies", 6.9, Duration.ofHours(1), 7);
        System.out.print(tendies.getName()+ " "+tendies.getPrice() +" "+ tendies.getPrepTime());

        tendies.setPrice(4.20);
        tendies.setPrepTime(Duration.ZERO.withSeconds(120));
        tendies.setName("Burgers");

        System.out.println();
        System.out.println(":  Updated after mutation: " + tendies.getName()+ " "+tendies.getPrice() +" "+ tendies.getPrepTime());


        //Checking the exception
        try
        {
            tendies.setPrice(-6.9);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error: invalid price");
        }


    }


}
