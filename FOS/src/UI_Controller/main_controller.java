package UI_Controller;


import Entities.Customer;
import Sort.SearchRestaurants;
import Sort.SearchDishes;
import Sort.SortInfo;
import database.CustomerFunctions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Random;

/**
 * Create By Hao Li at Oct. 22th
 * Refactor By Hao Li at Nov.28th
 * Modified by Yuecheng Rong
 */

public class main_controller extends HBox {

    //SortNotLogIn sortNotLogIn = new SortNotLogIn("Saskatoon","Saskatchewan");

    private SearchRestaurants searchRestaurants = new SearchRestaurants();
    private SearchDishes searchDishes = new SearchDishes();
    //ErrorMessage errorMessage = new ErrorMessage();
    private menus menus = new menus();
    public Orders orders = new Orders();
    public user_account user_account = new user_account();
    public count count = new count();
    private  int count_item=0;
    float prices = 0;
    // for search use
    private TextField search_field = new TextField();
    // Label blank = new Label("                           ");
    private Button reset = new Button("Reset");
    private Button searchDish = new Button("Dish");
    private Button searchRestaurant = new Button("Rest");

    private CheckBox s_w = new CheckBox();
    private CheckBox s_a = new CheckBox();

    private CheckBox s_p = new CheckBox();



    private center_lists res_center_lists = new center_lists();
    private Button pre = new Button("Previous");
    private Button next = new Button("Next");
    private Label name = new Label("Restaurants");
    private Label rate = new Label("Rate");
    private Label address = new Label("Address");
    private Label dishName = new Label("Dish Name");
    private Label dishPrice = new Label("Dish Price");
    private Label waiting_time = new Label("WT");


    private VBox restaurant_list;
    private VBox rate_list;
    private VBox address_list;
    private VBox dish_name_list;
    private VBox dish_price_list;
    private VBox waiting_time_list;
    private int page_number = 0;
    private boolean check_flag = true;
    private boolean searchRestaurants_flag = true;
    private boolean restaurantPressed = false;
    private boolean DishPressed = false;

    private ArrayList<Label> ordered_food = new ArrayList<>();
    private ArrayList<Integer> ordered_num = new ArrayList<>();
    private boolean duplication_check = false;

    // main accessing for all main list
    public main_controller(){
        setPrefSize(620,450);
        getChildren().addAll(res_name(),waiting_time(),res_rate(),res_address());
        setPadding(new Insets(10,10,10,10));
        setResListener();
        orders.checkout.setOnAction(e->{
            orders.orders();
            orders.place_order.UpdateChose(ordered_food);
            orders.place_order.Updatenumber(ordered_num);
        });
        setStyle("-fx-background-image: url(/pictures/background.jpg)");
    }

    /**
     * Following three functions are the three vertical center_lists appear at the right of the main_frame
     * @return
     */
    // main layout for restaurant name list
    private VBox res_name(){
        restaurant_list = new VBox();
        restaurant_list.setPrefSize(190,450);
        name.setPrefSize(190,30);
        name.setAlignment(Pos.CENTER);
        name.setStyle("-fx-background-color: lightsteelblue");
        restaurant_list.getChildren().add(name);
        for (int i = page_number; i< res_center_lists.res_image.length; i++){
            if (res_center_lists.res_image[i]!=null&&restaurant_list.getChildren().size()<5){
                restaurant_list.getChildren().add(res_center_lists.res_image[i]);
            }
        }

        return restaurant_list;
    }


    // main layout for restaurant rate list
    private VBox res_rate(){
        rate_list = new VBox();
        rate_list.setPrefSize(70,450);

        rate.setPrefSize(70,30);
        rate.setAlignment(Pos.CENTER);
        rate.setStyle("-fx-background-color: lightsteelblue");


        rate_list.getChildren().addAll(rate);
        for (int i = 0; i< res_center_lists.Rate.length; i++){
            if (res_center_lists.Rate[i]!=null && rate_list.getChildren().size()<5){
                rate_list.getChildren().add(res_center_lists.Rate[i]);
            }
        }
        return rate_list;
    }



    // main layout for restaurant address list
    private VBox res_address(){
        address_list = new VBox();
        address_list.setPrefSize(270,450);

        address.setPrefSize(270,30);
        address.setAlignment(Pos.CENTER);
        address.setStyle("-fx-background-color: lightsteelblue");
       // address_list.setStyle("-fx-background-color: deepskyblue");

        address_list.getChildren().add(address);
        for (int i = 0; i< res_center_lists.Address.length; i++){
            if(res_center_lists.Address[i]!=null && address_list.getChildren().size()<5){
                address_list.getChildren().add(res_center_lists.Address[i]);
            }
        }
        return address_list;
    }


    private VBox dish_name(){
        dish_name_list = new VBox();
        dish_name_list.setPrefSize(200,450);

        dishName.setPrefSize(200,30);
        dishName.setAlignment(Pos.CENTER);
        dishName.setStyle("-fx-background-color: lightsteelblue");
        dish_name_list.getChildren().add(dishName);
        for (int i = 0; i< res_center_lists.dishName.length; i++){
            if(res_center_lists.dishName[i]!=null && dish_name_list.getChildren().size()<5){
                dish_name_list.getChildren().add(res_center_lists.dishName[i]);
            }
        }
        return dish_name_list;
    }



    private VBox dish_price(){
        dish_price_list = new VBox();
        dish_price_list.setPrefSize(70,450);

        dishPrice.setPrefSize(70,30);
        dishPrice.setAlignment(Pos.CENTER);
        dishPrice.setStyle("-fx-background-color: lightsteelblue");
        dish_price_list.getChildren().add(dishPrice);
        for (int i = 0; i< res_center_lists.dishPrice.length; i++){
            if(res_center_lists.dishPrice[i]!=null && dish_price_list.getChildren().size()<5){
                dish_price_list.getChildren().add(res_center_lists.dishPrice[i]);
            }
        }
        return dish_price_list;
    }

    private VBox waiting_time(){
        waiting_time_list = new VBox();
        waiting_time_list.setPrefSize(70,450);

        waiting_time.setPrefSize(70,30);
        waiting_time.setAlignment(Pos.CENTER);
        waiting_time.setStyle("-fx-background-color: lightsteelblue");
        waiting_time_list.getChildren().add(waiting_time);
        for (int i = 0; i< res_center_lists.waitingTime.length; i++){
            if(res_center_lists.waitingTime[i]!=null && waiting_time_list.getChildren().size()<5){
                waiting_time_list.getChildren().add(res_center_lists.waitingTime[i]);
            }
        }
        return waiting_time_list;
    }

















    /**
     * Search Function
     *
     * @return
     */
    public VBox search(){
        VBox s_box = new VBox();
        setPrefSize(200,60);
        s_box.getChildren().addAll(search_field,s_holder());
        return s_box;
    }

    private HBox s_holder(){
        HBox holder = new HBox();
        //listener for search button and search field
        searchRestaurant.setOnAction(e->{
            page_number=0;
            restaurantPressed = true;
            DishPressed = false;
            if (!searchRestaurants_flag) {
                searchRestaurants_flag = true;
                this.getChildren().clear();
                this.getChildren().addAll(res_name(), waiting_time(), res_rate(), res_address());
            }
            if(!search_field.getText().isEmpty()){
                searchRestaurants.Search(search_field.getText());
                if (searchRestaurants.message.equals("Found")){



                    for (int i = 0; i < searchRestaurants.sortInfos.size(); i++) {
                        System.out.println(searchRestaurants.sortInfos.get(i));
                    }



                    setRestaurantsearch(searchRestaurants.sortInfos);
                }
            }
        });
        searchDish.setOnAction(e->{
            page_number=0;
            restaurantPressed = false;
            DishPressed = true;
            if (searchRestaurants_flag) {
                searchRestaurants_flag = false;
            }
                if(search_field.getText().isEmpty()){
                   System.out.println("Error: no input");
                   return;
                } else {
                    searchDishes.Search(search_field.getText());



                    for (int i = 0; i < searchDishes.sortInfos.size(); i++) {
                        System.out.println(searchDishes.sortInfos.get(i).dishToString());
                    }



                    if (searchDishes.message.equals("Found")){

                        res_center_lists.searchDishes(searchDishes.sortInfos);
                        this.getChildren().clear();
                        this.getChildren().addAll(res_name(), waiting_time(), res_rate(), dish_name(), dish_price());
                        setDishessearch(searchDishes.sortInfos);
                    }
                }

        });
        reset.setOnAction(e->{
            page_number=0;
            restaurantPressed = true;
            DishPressed = false;
            restaurantPressed = false;
            res_center_lists = new center_lists();
            setLists();
            if (!searchRestaurants_flag) {
                searchRestaurants_flag = true;
                this.getChildren().clear();
                this.getChildren().addAll(res_name(), waiting_time(), res_rate(), res_address());
            }
            search_field.clear();
        });
        holder.getChildren().addAll(reset,searchDish,searchRestaurant);
        return holder;
    }





    /**
     * Preview/Next Button
     *
     * @return
     */
    public HBox main_Button(){
        HBox button_box = new HBox();
        button_box.getStylesheets().add("css/page.css");
        set_button();
        button_box.getChildren().addAll(pre,next);
        return button_box;
    }
    // Edited: input has been changed
    private void set_button(){

        pre.setPrefSize(90,30);
        next.setPrefSize(90,30);
        pre.setOnAction(e->{
            if (page_number>=4){
                page_number=page_number-4;
                if ( DishPressed && !restaurantPressed ) {
                    setDishessearch(searchDishes.sortInfos);
                } else if(restaurantPressed) {
                    setRestaurantsearch(searchRestaurants.sortInfos);
                } else {
                    setLists();
                }
            }
            else {
                System.out.println("pre");
            }
        });
        next.setOnAction(e->{
            int size;
            if (DishPressed && !restaurantPressed) {
                size = searchDishes.sortInfos.size()-4;
            } else if (restaurantPressed) {
                size = searchRestaurants.sortInfos.size()-4;
            } else {
                size = restaurant_list.getChildren().size();
            }
            if (page_number < size) {
                page_number = page_number + 4;
                if (DishPressed && !restaurantPressed) {
                    setDishessearch(searchDishes.sortInfos);
                } else if (restaurantPressed) {
                    setRestaurantsearch(searchRestaurants.sortInfos);
                } else {
                    setLists();
                }
            } else {
                System.out.println("next");
            }
        });

    }


    /**
     * Helper functions
     */

    private void setLists(){


        restaurant_list.getChildren().clear();

        restaurant_list.getChildren().add(name);
        for (int i = page_number; i< res_center_lists.res_image.length; i++){
            if (res_center_lists.res_image[i]!=null&&restaurant_list.getChildren().size()<5){
                restaurant_list.getChildren().add(res_center_lists.res_image[i]);
            }
        }

        setListsHelper(address_list,address,res_center_lists.Address);
        setListsHelper(rate_list,rate,res_center_lists.Rate);
        setListsHelper(waiting_time_list,waiting_time,res_center_lists.waitingTime);
        setResListener();

    }

    void setListsHelper(VBox list,Label label, Label[] labels){
        list.getChildren().clear();
        list.getChildren().add(label);
        for (int i = page_number; i< res_center_lists.waitingTime.length; i++){
            if (labels[i]!=null && list.getChildren().size()<5){
                list.getChildren().add(labels[i]);
            }
        }
    }



    public VBox setsort(){
        VBox sort_hold = new VBox();
        s_a.setText("Sort By Rating");
        user_account.signedIn.s_d.setText("Sort By Distance");
        s_p.setText("Sort By Price");
        s_w.setText("Sort By Waiting Time");
        s_a.getStylesheets().add("css/checkbox.css");
        s_w.getStylesheets().add("css/checkbox.css");
        user_account.signedIn.s_d.getStylesheets().add("css/checkbox.css");
        s_p.getStylesheets().add("css/checkbox.css");

        s_p.setOnAction(e->{
            if (s_p.isSelected()) {
                s_a.setSelected(false);
                s_w.setSelected(false);
                user_account.signedIn.s_d.setSelected(false);
                page_number=0;

                if(searchRestaurants_flag){
                    searchRestaurants.sortByPrice();
                    setRestaurantsearch(searchRestaurants.sortInfos);
                } else {
                    searchDishes.sortByPrice();
                    res_center_lists.searchDishes(searchDishes.sortInfos);
                    setDishessearch(searchDishes.sortInfos);
                }
                /*
                sortNotLogIn.sortByPrice();
                System.out.println(sortNotLogIn.sortInfos);
                res_center_lists.getaddress(sortNotLogIn.sortInfos);
                res_center_lists.getRate(sortNotLogIn.sortInfos);
                res_center_lists.getRestaurant(sortNotLogIn.sortInfos);


                setLists();
                */
                setResListener();
            }else {
                s_p.setSelected(false);
            }
        });

        s_w.setOnAction(e->{
            if (s_w.isSelected()) {
                s_a.setSelected(false);
                s_p.setSelected(false);
                user_account.signedIn.s_d.setSelected(false);
                page_number=0;

                if(searchRestaurants_flag){
                    searchRestaurants.sortByTime();
                    setRestaurantsearch(searchRestaurants.sortInfos);
                } else {
                    searchDishes.sortByTime();
                    res_center_lists.searchDishes(searchDishes.sortInfos);
                    setDishessearch(searchDishes.sortInfos);
                }
                /*
                sortNotLogIn.sortByWaitingTime();
                System.out.println(sortNotLogIn.sortInfos);
                res_center_lists.getaddress(sortNotLogIn.sortInfos);
                res_center_lists.getRate(sortNotLogIn.sortInfos);
                res_center_lists.getRestaurant(sortNotLogIn.sortInfos);

                setLists();
                */
                setResListener();
            }else {
                s_w.setSelected(false);
            }
        });

        s_a.setOnAction(e->{
            if (s_a.isSelected()){
                s_w.setSelected(false);
                s_p.setSelected(false);
                user_account.signedIn.s_d.setSelected(false);
                page_number=0;

                if(searchRestaurants_flag){
                    searchRestaurants.sortByRate();
                    searchRestaurants.printSuperArray();
                    setRestaurantsearch(searchRestaurants.sortInfos);
                } else {
                    searchDishes.sortByRate();
                    res_center_lists.searchDishes(searchDishes.sortInfos);
                    setDishessearch(searchDishes.sortInfos);
                }

                /*
                sortNotLogIn.sortByRate();
                System.out.println(sortNotLogIn.sortInfos);
                res_center_lists.getaddress(sortNotLogIn.sortInfos);
                res_center_lists.getRate(sortNotLogIn.sortInfos);
                res_center_lists.getRestaurant(sortNotLogIn.sortInfos);

                setLists();
                */
                setResListener();
            }
            else{
                s_a.setSelected(false);
            }

        });

        user_account.signedIn.s_d.setOnAction(e->{
            if (user_account.signedIn.s_d.isSelected()){
                s_w.setSelected(false);
                s_p.setSelected(false);
                s_a.setSelected(false);
                page_number=0;

                Customer customer = user_account.signedIn.getCustomer();
                CustomerFunctions customerFunctions = new CustomerFunctions();
                int cus_id = customerFunctions.getCustomerId(customer);

                if(searchRestaurants_flag){
                    searchRestaurants.sortByDistance(cus_id);
                    searchRestaurants.printSuperArray();
                    setRestaurantsearch(searchRestaurants.sortInfos);
                 } else {
                    searchDishes.sortByDistance(cus_id);
                    res_center_lists.searchDishes(searchDishes.sortInfos);
                    setDishessearch(searchDishes.sortInfos);
                }
                setResListener();
            }

            /*
            if (user_account.signedIn.s_d.isSelected()){
                s_w.setSelected(false);
                s_p.setSelected(false);
                s_a.setSelected(false);
                Customer customer = user_account.signedIn.getCustomer();
                CustomerFunctions customerFunctions = new CustomerFunctions();
                int cus_id = customerFunctions.getCustomerId(customer);
                SortByDistanceWithLogin sbd = new SortByDistanceWithLogin(cus_id,"Saskatoon","Saskatchewan");


                res_center_lists.getaddress(sbd.getDistanceSort());
                res_center_lists.getRate(sbd.getDistanceSort());
                res_center_lists.getRestaurant(sbd.getDistanceSort());



                setLists();
                setResListener();

            }
            */
            else {
                user_account.signedIn.s_d.setSelected(false);
            }
        });

        //restaurant_list.getChildren().add(res_center_lists.res_image[n]);
        sort_hold.setPrefSize(100,70);
        sort_hold.getChildren().addAll(s_a,s_p,user_account.signedIn.s_d,s_w);
        return sort_hold;
    }









    /**
     * Need id only
     * @param input
     */
    private void setRestaurantsearch(ArrayList<SortInfo> input){
        Label temp_add;
        Label temp_rate;
        Label temp_waiting;

        restaurant_list.getChildren().clear();
        waiting_time_list.getChildren().clear();
        rate_list.getChildren().clear();
        address_list.getChildren().clear();

        restaurant_list.getChildren().add(name);
        waiting_time_list.getChildren().add(waiting_time);
        address_list.getChildren().add(address);
        rate_list.getChildren().add(rate);

        for(int i=page_number; i<input.size(); i++) {

            int k = input.get(i).getRest_id();
            ImageView temp_res = new ImageView(new Image("/restaurant_pic/"+k+".jpg"));
            temp_res.setFitWidth(190);
            temp_res.setFitHeight(110);

            temp_waiting = res_center_lists.waitingTime[k-1];
            temp_rate = res_center_lists.Rate[k-1];
            temp_add = res_center_lists.Address[k-1];

            if (restaurant_list.getChildren().size()<5) {
                restaurant_list.getChildren().add(temp_res);
                waiting_time_list.getChildren().add(temp_waiting);
                address_list.getChildren().add(temp_add);
                rate_list.getChildren().add(temp_rate);
            }
            temp_res.setOnMousePressed(event -> {
                menus.page_number = 0;
                menus.menus(k);
                set();
            });

        }
    }


    /**
     * Need id only
     * @param input
     */
    private void setDishessearch(ArrayList<SortInfo> input){

        Label temp_rate;
        Label temp_name;
        Label temp_price;
        Label temp_waiting;

        restaurant_list.getChildren().clear();
        waiting_time_list.getChildren().clear();
        rate_list.getChildren().clear();
        dish_price_list.getChildren().clear();
        dish_name_list.getChildren().clear();

        restaurant_list.getChildren().add(name);
        waiting_time_list.getChildren().add(waiting_time);
        rate_list.getChildren().add(rate);
        dish_price_list.getChildren().add(dishPrice);
        dish_name_list.getChildren().add(dishName);

        for(int i=page_number; i<input.size(); i++) {
            int k = input.get(i).getRest_id();
            ImageView temp_res = new ImageView(new Image("/restaurant_pic/"+k+".jpg"));
            temp_res.setFitWidth(190);
            temp_res.setFitHeight(110);

            temp_waiting = res_center_lists.waitingTime[i];
            temp_rate = res_center_lists.Rate[i];
            temp_name = res_center_lists.dishName[i];
            temp_price = res_center_lists.dishPrice[i];


            if (restaurant_list.getChildren().size()<5) {
                restaurant_list.getChildren().add(temp_res);
                waiting_time_list.getChildren().add(temp_waiting);
                rate_list.getChildren().add(temp_rate);
                dish_name_list.getChildren().add(temp_name);
                dish_price_list.getChildren().add(temp_price);
            }
            temp_res.setOnMousePressed(event -> {
                menus.page_number = 0;
                menus.menus(k);
                set();
            });

        }
    }


















    private void setResListener(){
        for (int i = 0; i< res_center_lists.res_image.length; i++){
            int finalI = i;
            res_center_lists.res_image[i].setOnMousePressed((MouseEvent e) -> {
                menus.menus(res_center_lists.r_id[finalI]);
                set();
            });

        }
    }

    private void set(){
        for (int i=0;i<menus.items.length;i++){
            int finalI = i;
            check_flag = user_account.signedIn.select_flag;
            if (check_flag){
                menus.items[i].getButton().setOnAction(e->{
                    count_item++;
                    Random random = new Random();
                    prices = prices+(random.nextFloat()%(19-4+1) + 4);
                    prices = (float)(Math.round(prices*10))/10;
                    check_number(menus.items[finalI].getLabel());
                    if (!duplication_check){
                        Label temp = new Label(menus.items[finalI].getLabel().getText());
                        ordered_food.add(temp);
                        ordered_num.add(1);
                    }
                    count.num_items.setText(""+count_item);
                    count.num_prices.setText("$ "+prices);
                });
            }
        }
    }

    private void check_number(Label label){
        for (int i=0;i<ordered_food.size();i++){
            if (label.getText().equals(ordered_food.get(i).getText())){
                int temp  = ordered_num.get(i)+1;
                ordered_num.remove(i);
                ordered_num.add(i,temp);
                duplication_check = true;
            }
            else {
                duplication_check = false;
            }
        }
    }

}
