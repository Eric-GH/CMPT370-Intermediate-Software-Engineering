package UI_Controller;

import Sort.SortByOrdering;
import Sort.SortInfo;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Create By Hao Li at Oct. 22th
 * Modified by Yuecheng Rong
 */



public class center_lists {
    int index = 10;
    int pic_number = 0;

    Image k;
    Image[] restaurant;
    ImageView[]  res_image;
    Label[] Address;
    Label[] Rate;
    Label[] dishName;
    Label[] dishPrice;
    Label[] waitingTime;
    int[] r_id;
    SortByOrdering sbt = new SortByOrdering("Saskatoon","Saskatchewan");



    /**
     * Put the pictures in Image[] into ImageView[]
     */
    public center_lists(){
        index = sbt.getSortByOrderWithoutLogin().size();
        getRestaurant(sbt.getSortByOrderWithoutLogin());
        getaddress(sbt.getSortByOrderWithoutLogin());
        getRate(sbt.getSortByOrderWithoutLogin());
        getWaitingTime(sbt.getSortByOrderWithoutLogin());
    }


    /**
     * Another constructor
     * Change the information showed in main_frame if restaurants are searched
    public void searchRestaurants(ArrayList<SortInfo> input){
        index = input.size();
        getRestaurant(input);
        getaddress(input);
        getRate(input);
        getWaitingTime(input);
    }
     */


    /**
     * Another constructor
     * Change the information showed in main_frame if dishes are searched
     */
    public void searchDishes(ArrayList<SortInfo> input){
        index = input.size();
        getRestaurant(input);
        getRate(input);
        getPrice(input);
        getName(input);
        getWaitingTime(input);
    }



    /**
     * Put all the pictures from folder into Image[]
     * where id = pic_id
     */
    void getRestaurant(ArrayList<SortInfo> res){

        restaurant  = new Image[index];
        res_image = new ImageView[restaurant.length];
        r_id = new int[restaurant.length];
        int temp_r = index;
        while (temp_r !=0){
            for (int i = 0; i < res.size();i++){
                r_id[i] = res.get(i).getRest_id();
                if (restaurant[i]==null){
                    k = new Image("/restaurant_pic/"+res.get(i).getRest_id()+".jpg");
                    restaurant[i] = k;
                }
                pic_number++;
            }
            temp_r--;
        }
        // Now Image[] is fulfilled with pictures
        for (Image aRestaurant : restaurant) {
            for (int n = 0; n < res_image.length; n++) {
                if (res_image[n] == null) {
                    ImageView temp = new ImageView(aRestaurant);
                    temp.setFitWidth(190);
                    temp.setFitHeight(110);
                    res_image[n] = temp;
                    break;
                }
            }
        }

    }

    void getaddress(ArrayList<SortInfo> address){
        Address = new Label[index];
        int temp_address = index;
        while (temp_address!=0){
            for (int i=0;i<address.size();i++){
                Label temp_l = new Label();
                temp_l.setText(address.get(i).getRest_address());
                temp_l.setAlignment(Pos.CENTER);
                temp_l.setStyle("-fx-background-image: url(/pictures/cornsilk.png)");
                temp_l.setPrefSize(270,110);
                Address[i] = temp_l;
            }
            temp_address--;
        }

    }

    void getRate(ArrayList<SortInfo> rate){
        Rate = new Label[index];
        int temp_rate = index;
        while (temp_rate!=0) {
            for (int i=0;i<rate.size();i++){
                Label temp_r = new Label();
                temp_r.setText(""+rate.get(i).getRest_rate());
                temp_r.setAlignment(Pos.CENTER);
                temp_r.setStyle("-fx-background-image: url(/pictures/lightcyan.png)");
                temp_r.setPrefSize(70,110);
                Rate[i]=temp_r;
            }
            temp_rate--;

        }
    }

    void getPrice(ArrayList<SortInfo> price){
        dishPrice = new Label[index];
        int temp = index;
        while (temp!=0) {
            for (int i=0;i<price.size();i++){
                Label temp_r = new Label();
                temp_r.setText(""+price.get(i).getDish_price());
                temp_r.setAlignment(Pos.CENTER);
                temp_r.setStyle("-fx-background-image: url(/pictures/cornsilk.png)");
                temp_r.setPrefSize(70,110);
                dishPrice[i]=temp_r;
            }
            temp--;

        }
    }

    void getName(ArrayList<SortInfo> name){
        dishName = new Label[index];
        int temp = index;
        while (temp!=0) {
            for (int i=0;i<name.size();i++){
                Label temp_r = new Label();
                temp_r.setText(""+name.get(i).getDish_Name());
                temp_r.setAlignment(Pos.CENTER);
                temp_r.setStyle("-fx-background-image: url(/pictures/cornsilk.png)");
                temp_r.setPrefSize(200,110);
                dishName[i]=temp_r;
            }
            temp--;

        }
    }

    void getWaitingTime(ArrayList<SortInfo> name){
        waitingTime = new Label[index];
        int temp = index;
        while (temp!=0) {
            for (int i=0;i<name.size();i++){
                Label temp_r = new Label();
                temp_r.setText(""+name.get(i).getRest_time());
                temp_r.setAlignment(Pos.CENTER);
                temp_r.setStyle("-fx-background-image: url(/pictures/cornsilk.png)");
                temp_r.setPrefSize(70,110);
                waitingTime[i]=temp_r;
            }
            temp--;

        }
    }


    /**
     * Helper function that can print the super array on the console.
     */
    public void printSuperArray() {
        for (int i = 0; i < sbt.getSortByOrderWithoutLogin().size(); i++) {
            System.out.println(sbt.getSortByOrderWithoutLogin().get(i));
        }
    }



}
