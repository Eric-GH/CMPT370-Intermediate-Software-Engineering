package Entities;

public class Location {
     private int user_id;
     private int house_num;
     private String street;
     private String city;
     private String province;
     private String post_code;


     public int getUser_id()
     {
         return user_id;
     }

     public int getHouse_num()
     {
         return house_num;
     }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setHouse_num(int house_num) {
        this.house_num = house_num;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public Location(int user_id, int house_num, String street, String city, String province, String post_code)
    {
        this.user_id = user_id;
        this.house_num = house_num;
        this.street = street;
        this.city = city;
        this.province = province;
        this.post_code = post_code;
    }

    public String toString() {
        return user_id +": " +house_num+" "+ street+" "+city+" "+province+ " "+ post_code;
    }
}
