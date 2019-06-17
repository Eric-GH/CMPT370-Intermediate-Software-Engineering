package Entities;
import database.*;

import java.time.Duration;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 * Created by Kocur on 2017-10-24.
 * Modified by Yinsheng on 2017-12-3
 */
public class Customer implements Comparable
{
    private int user_id;
    private String first_name;
    private String last_name;
    private String phone_num;
    private String email_address;
    private String pref_food;



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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPref_food() {
        return pref_food;
    }

    public void setPref_food(String pref_food) {
        this.pref_food = pref_food;
    }

    public Customer(int user_id,String first_name, String last_name, String phone_num, String email_address, String pref_food)
    {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_num = phone_num;
        this.email_address = email_address;
        this.pref_food = pref_food;
    }

    @Override
    public String toString() {
        return user_id+": "+first_name+" "+last_name+" "+" "+email_address+" " + phone_num+" "+pref_food;
    }
}


