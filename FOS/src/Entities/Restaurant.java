package Entities;

import java.sql.Time;
import java.util.Comparator;

/**
 * Created by Kocur on 2017-10-24.
 * Modified By Yinsheng Dong on 2017-11-27
 */
public class Restaurant implements Comparable
{

    private int user_id;
    private String restaurant_name;
    private String license_id;
    private Time open_time;
    private Time close_time;
    private String phone_num;
    private String e_mail_address;
    private Time waiting_time;
    private float avg_price;
    private float review_point;

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getReview_point()
    {
        return review_point;
    }

    public void setReview_point(float review_point) {
        this.review_point = review_point;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getLicense_id() {
        return license_id;
    }

    public void setLicense_id(String license_id) {
        this.license_id = license_id;
    }

    public Time getOpen_time() {
        return open_time;
    }

    public void setOpen_time(Time open_time) {
        this.open_time = open_time;
    }

    public Time getClose_time() {
        return close_time;
    }

    public void setClose_time(Time close_time) {
        this.close_time = close_time;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getE_mail_address() {
        return e_mail_address;
    }

    public void setE_mail_address(String e_mail_address) {
        this.e_mail_address = e_mail_address;
    }

    public Time getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(Time waiting_time) {
        this.waiting_time = waiting_time;
    }

    public float getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(float avg_price) {
        this.avg_price = avg_price;
    }

    public Restaurant(int user_id, String restaurant_name, String license_id, Time open_time, Time close_time, String phone_num, String e_mail_address)
    {
        this.user_id = user_id;
        this.restaurant_name = restaurant_name;
        this.license_id = license_id;
        this.open_time = open_time;
        this.close_time = close_time;
        this.phone_num = phone_num;
        this.e_mail_address = e_mail_address;
    }

    @Override
    public String toString()
    {
        return user_id +": "+ restaurant_name+" "+license_id+" "+open_time+" "+close_time+" "+phone_num+ " "
                + e_mail_address +" "+waiting_time+" "+ avg_price;
    }

}
