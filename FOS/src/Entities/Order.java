package Entities;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Kocur on 2017-10-24.
 */
public class Order implements Comparable
{
    private Date order_date;
    private Time order_time;
    private int customer_id;
    private int restaurant_id;
    private float subtotal;
    private float pst_tax;
    private float gst_tax;
    private float total_discount;
    private Time estimate_preparation_time;


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Time getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Time order_time) {
        this.order_time = order_time;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getPst_tax() {
        return pst_tax;
    }

    public void setPst_tax(float pst_tax) {
        this.pst_tax = pst_tax;
    }

    public float getGst_tax() {
        return gst_tax;
    }

    public void setGst_tax(float gst_tax) {
        this.gst_tax = gst_tax;
    }



    public float getTotal_discount() {
        return total_discount;
    }

    public void setTotal_discount(float total_discount) {
        this.total_discount = total_discount;
    }

    public Time getEstimate_preparation_time() {
        return estimate_preparation_time;
    }

    public void setEstimate_preparation_time(Time estimate_preparation_time) {
        this.estimate_preparation_time = estimate_preparation_time;
    }


    public Order(Date order_date, Time order_time, int customer_id, int restaurant_id)
    {
        this.order_date = order_date;
        this.order_time = order_time;
        this.customer_id = customer_id;
        this.restaurant_id = restaurant_id;
    }

    @Override
    public String toString() {
        return order_date+" "+ order_time+" "+customer_id+" "+restaurant_id+" "+subtotal+" "+pst_tax+" "+gst_tax+" "+
                total_discount+" "+estimate_preparation_time;
    }
}
