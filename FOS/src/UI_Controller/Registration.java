package UI_Controller;

import Email.EmailConfirmation;
import Email.GetRandomCode;
import Entities.Customer;
import Entities.Location;
import Entities.Restaurant;
import database.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Time;
import java.util.Objects;

/**
 * Create By Hao Li at Oct. 12th
 */

class Registration {

    // create each frame
    private Stage Reg_windows = new Stage();

    private AddUser addUser = new AddUser();

    private EmailConfirmation emailConfirmation;

    Registration(){
        // set up modality
        Reg_windows.initModality(Modality.APPLICATION_MODAL);
    }

    // choose button frame
    void Reg() {

        //create buttons and hbox
        HBox check = new HBox();
        Button customer_check = new Button("Register as Customer");
        Button restaurant_check = new Button("Register as Restaurant");


        //listener for 2 buttons
        customer_check.setOnAction(e->customerReg());

        restaurant_check.setOnAction(e->restaurantReg());
        //set size
        customer_check.setMinSize(50, 200);
        restaurant_check.setMinSize(50, 200);
        Reg_windows.setTitle("Register");
        Reg_windows.setMinHeight(200);
        Reg_windows.setMinWidth(100);

        // add all
        check.getChildren().addAll(customer_check, restaurant_check);
        Scene scene = new Scene(check);
        Reg_windows.setScene(scene);
        Reg_windows.show();
    }


    // help frame to tell you status of register
    private void pop() {
        Stage pop = new Stage();
        pop.setMinWidth(200);
        VBox vBox = new VBox();
        Label messageLabel = new Label("You are successfully register");
        messageLabel.setTextFill(Color.ORANGE);
        Label quit = new Label("Click to close");
        quit.setTextFill(Color.RED);
        quit.setFont(new Font(18));
        quit.setOnMousePressed(e -> pop.close());
        vBox.getChildren().addAll(messageLabel, quit);
        Scene scene = new Scene(vBox);
        pop.setScene(scene);
        pop.show();
    }


    // register of restaurant frame
    private void restaurantReg() {
        // creation
        Stage restaurantReg = new Stage();
        GridPane res_pane = new GridPane();
        VBox box = new VBox();
        restaurantReg.setTitle("RESTAURANT REGISTER");
        String randomCode = new GetRandomCode().generatePIN();

        // Labels
        Label reg = new Label("Welcome to FOS");
        Label res_name = new Label("Restaurant Name: ");
        Label lic_num = new Label("License ID: ");
        Label open_time = new Label("Open Time: ");
        Label close_time = new Label("Close Time: ");
        Label email = new Label("Email: ");
        Label phone_num = new Label("Phone Number: ");
        Label account = new Label("Username: ");
        Label password = new Label("Password: ");
        Label city = new Label("City: ");
        Label str_number = new Label("Street Number: ");
        Label str_name = new Label("Street Name: ");
        Label province = new Label("Province: ");
        Label post_code = new Label("Post Code: ");
        Label waiting_time = new Label("Average Waiting Time: ");
        Label avg_price = new Label("Average Price: ");
        Label confirm = new Label("Confirm Password: ");
        Label email_confirm_code = new Label("Email Confirmation Code: ");
        Label error = new Label(" ");
        error.setWrapText(true);


        //TextFields
        TextField restName_field = new TextField();
        TextField licenseId_field = new TextField();
        TextField openTime_field = new TextField("HH:MM:SS");
        TextField closeTime_field = new TextField("HH:MM:SS");
        TextField email_field = new TextField();
        TextField phoneNum_field = new TextField();
        TextField account_field = new TextField();
        PasswordField password_field = new PasswordField();
        TextField province_field = new TextField();
        TextField city_field = new TextField("Saskatoon");
        city_field.setEditable(false);
        TextField strname_field = new TextField();
        TextField strNum_field = new TextField();
        TextField pcode_field = new TextField();
        TextField waiting_time_field = new TextField("HH:MM:SS");
        TextField avg_price_field = new TextField();
        PasswordField confirm_field = new PasswordField();
        TextField email_confirmation_feild = new TextField();
        Button resend = new Button("SEND");

        //Button
        Button submit = new Button("SUBMIT");

        //padding
        res_name.setPadding(new Insets(10, 5, 0, 25));
        lic_num.setPadding(new Insets(10, 5, 0, 25));
        open_time.setPadding(new Insets(10, 5, 0, 25));
        close_time.setPadding(new Insets(10, 5, 0, 25));
        city.setPadding(new Insets(10, 5, 0, 25));
        password.setPadding(new Insets(10, 5, 0, 25));
        confirm.setPadding(new Insets(10, 5, 0, 25));
        email.setPadding(new Insets(10, 5, 0, 25));
        phone_num.setPadding(new Insets(10, 5, 0, 25));
        account.setPadding(new Insets(10, 5, 0, 25));
        str_name.setPadding(new Insets(10, 5, 0, 25));
        str_number.setPadding(new Insets(10, 5, 0, 25));
        post_code.setPadding(new Insets(10, 5, 0, 25));
        province.setPadding(new Insets(10, 5, 0, 25));
        waiting_time.setPadding(new Insets(10, 5, 0, 25));
        avg_price.setPadding(new Insets(10, 5, 0, 25));
        error.setPadding(new Insets(10, 5, 0, 25));
        email_confirm_code.setPadding(new Insets(10, 5, 0, 25));


        //Margin
        GridPane.setMargin(restName_field,new Insets(0,5,0,25));
        GridPane.setMargin(licenseId_field,new Insets(0,5,0,25));
        GridPane.setMargin(account_field,new Insets(0,5,0,25));
        GridPane.setMargin(password_field,new Insets(0,5,0,25));
        GridPane.setMargin(confirm_field,new Insets(0,5,0,25));
        GridPane.setMargin(email_field,new Insets(0,5,0,25));
        GridPane.setMargin(phoneNum_field,new Insets(0,5,0,25));
        GridPane.setMargin(strname_field,new Insets(0,5,0,25));
        GridPane.setMargin(strNum_field,new Insets(0,5,0,25));
        GridPane.setMargin(city_field,new Insets(0,5,0,25));
        GridPane.setMargin(province_field,new Insets(0,5,0,25));
        GridPane.setMargin(pcode_field,new Insets(0,5,0,25));
        GridPane.setMargin(openTime_field,new Insets(0,5,0,25));
        GridPane.setMargin(closeTime_field,new Insets(0,5,0,25));
        GridPane.setMargin(waiting_time_field,new Insets(0,5,0,25));
        GridPane.setMargin(avg_price_field,new Insets(0,5,0,25));
        GridPane.setMargin(email_confirmation_feild,new Insets(0,5,0,25));





        resend.setOnAction(ActionEvent->{
            emailConfirmation = new EmailConfirmation(randomCode,email_field.getText());
        });


        // listener for submit button
        submit.setOnAction(ActionEvent -> {
            if(email_confirmation_feild.getText().equals(randomCode))
            {
                Restaurants restaurants = new Restaurants();
                LocationFunctions locationFunctions = new LocationFunctions();
                addUser.addUser(account_field.getText(), password_field.getText(), "r");
                GetUserId getUserId = new GetUserId();
                int user_id = getUserId.getUserId(account_field.getText(),password_field.getText());
                Restaurant restaurant = new Restaurant(user_id,restName_field.getText(),licenseId_field.getText(),Time.valueOf(openTime_field.getText()), Time.valueOf(closeTime_field.getText()), phoneNum_field.getText(), email_field.getText());
                restaurant.setWaiting_time(Time.valueOf(waiting_time_field.getText()));
                restaurant.setAvg_price(Float.parseFloat(avg_price_field.getText()));
                Location location = new Location(user_id,Integer.parseInt(strNum_field.getText()),strname_field.getText(),city_field.getText(),province_field.getText(),pcode_field.getText());
                locationFunctions.addLocations(location);
                restaurants.addRestaurant(restaurant);
                if (addUser.message.equals("You have successfully signed up")&& restaurants.message.equals( "The restaurant information has been saved")
                        && locationFunctions.message.equals("the location information has been saved")) {
                    restaurantReg.close();
                    Reg_windows.close();
                    pop();
                } else {
                    error.setTextFill(Color.RED);
                    error.setText(addUser.message);
                }
            }
            else
            {
                error.setTextFill(Color.RED);
                error.setText("Not correct confirmation Code");
            }
        });



        // add all
        res_pane.add(error, 1, 0);
        res_pane.add(res_name, 0, 1);
        res_pane.add(restName_field, 0, 2);
        res_pane.add(lic_num, 1, 1);
        res_pane.add(licenseId_field, 1, 2);
        res_pane.add(open_time, 0, 3);
        res_pane.add(openTime_field, 0, 4);
        res_pane.add(close_time, 1, 3);
        res_pane.add(closeTime_field, 1, 4);
        res_pane.add(email, 0, 5);
        res_pane.add(email_field, 0, 6);
        res_pane.add(phone_num, 1, 5);
        res_pane.add(phoneNum_field, 1, 6);
        res_pane.add(account, 0, 7);
        res_pane.add(account_field, 0, 8);
        res_pane.add(post_code,1,7);
        res_pane.add(pcode_field,1,8);
        res_pane.add(password, 0, 9);
        res_pane.add(password_field, 0, 10);
        res_pane.add(confirm, 1, 9);
        res_pane.add(confirm_field,1,10);
        res_pane.add(str_number,0,11);
        res_pane.add(strNum_field,0,12);
        res_pane.add(str_name,1,11);
        res_pane.add(strname_field,1,12);
        res_pane.add(city,0,13);
        res_pane.add(city_field,0,14);
        res_pane.add(province,1,13);
        res_pane.add(province_field,1,14);
        res_pane.add(waiting_time,0,15);
        res_pane.add(waiting_time_field,0,16);
        res_pane.add(avg_price,1,15);
        res_pane.add(avg_price_field,1,16);
        res_pane.add(email_confirm_code,0,17);
        res_pane.add(email_confirmation_feild,0,18);
        res_pane.add(resend,1,18);
        res_pane.add(submit,2,19);
        res_pane.setPadding(new Insets(0, 5, 0, 65));

        //size
        box.getChildren().addAll(reg,res_pane);
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(630,550);
        Scene scene = new Scene(box);
        scene.getStylesheets().add("css/register_style.css");
        restaurantReg.setScene(scene);
        restaurantReg.show();
    }


    // frame for register as customer
    private void customerReg() {

        // creation
        Stage customerReg = new Stage();
        GridPane reg_pane = new GridPane();
        VBox box = new VBox();
        customerReg.setTitle("CUSTOMER REGISTER");


        //Buttons
        Button signUp = new Button("SUBMIT ");

        //Labels
        Label reg = new Label("Welcome to FOS");
        Label account = new Label("Username: ");
        Label password = new Label("Password: ");
        Label first_name = new Label("First Name: ");
        Label last_name = new Label("Last name: ");
        Label phone_number = new Label("Phone#: ");
        Label email = new Label("E-mail: ");
        Label str_number = new Label("Street Number: ");
        Label str_name = new Label("Street Name: ");
        Label city = new Label("City: ");
        Label error = new Label(" ");
        Label post_code = new Label("Post Code: ");
        Label confirm_password = new Label("Confirm Password: ");
        Label prefer_food = new Label("Prefer Food: ");
        Label email_confirm_code = new Label("Email Confirmation Code: ");
        TextField email_confirmation_feild = new TextField();
        Button resend = new Button("SEND");
        error.setWrapText(true);
        String randomCode = new GetRandomCode().generatePIN();

        //Fields
        TextField firstName_field = new TextField();
        TextField lastName_field = new TextField();
        TextField phoneNum_field = new TextField();
        TextField email_field = new TextField();
        TextField account_field = new TextField();
        PasswordField password_field = new PasswordField();
        TextField pref_field = new TextField();
        TextField city_field = new TextField();
        TextField strname_field = new TextField();
        TextField strNum_field = new TextField();
        TextField pcode_field = new TextField();
        PasswordField confirm_field = new PasswordField();




        //padding
        //reg.setPadding(new Insets(20, 30, 25, 0));
        first_name.setPadding(new Insets(0, 5, 0, 25));
        last_name.setPadding(new Insets(0, 5, 0, 25));
        confirm_password.setPadding(new Insets(10, 5, 0, 25));
        city.setPadding(new Insets(10, 5, 0, 25));
        str_name.setPadding(new Insets(10, 5, 0, 25));
        str_number.setPadding(new Insets(10, 5, 0, 25));
        email.setPadding(new Insets(10, 5, 0, 25));
        phone_number.setPadding(new Insets(10, 5, 0, 25));
        account.setPadding(new Insets(10, 5, 0, 25));
        password.setPadding(new Insets(10, 5, 0, 25));
        prefer_food.setPadding(new Insets(10, 5, 0, 25));
        error.setPadding(new Insets(10, 5, 0, 25));
        reg_pane.setPadding(new Insets(0, 5, 0, 65));
        post_code.setPadding(new Insets(10, 5, 0, 25));
        email_confirm_code.setPadding(new Insets(10, 5, 0, 25));

        //Margin
        GridPane.setMargin(firstName_field,new Insets(0,5,0,25));
        GridPane.setMargin(lastName_field,new Insets(0,5,0,25));
        GridPane.setMargin(account_field,new Insets(0,5,0,25));
        GridPane.setMargin(password_field,new Insets(0,5,0,25));
        GridPane.setMargin(confirm_field,new Insets(0,5,0,25));
        GridPane.setMargin(email_field,new Insets(0,5,0,25));
        GridPane.setMargin(phoneNum_field,new Insets(0,5,0,25));
        GridPane.setMargin(strname_field,new Insets(0,5,0,25));
        GridPane.setMargin(strNum_field,new Insets(0,5,0,25));
        GridPane.setMargin(city_field,new Insets(0,5,0,25));
        GridPane.setMargin(pref_field,new Insets(0,5,0,25));
        GridPane.setMargin(pcode_field,new Insets(0,5,0,25));
        GridPane.setMargin(email_confirmation_feild,new Insets(0,5,0,25));


        //Font
        reg.setFont(new Font(18));
        first_name.setFont(new Font(12));
        last_name.setFont(new Font(12));
        email.setFont(new Font(12));
        phone_number.setFont(new Font(12));
        account.setFont(new Font(12));
        password.setFont(new Font(12));
        str_name.setFont(new Font(12));
        str_number.setFont(new Font(12));
        city.setFont(new Font(12));
        post_code.setFont(new Font(12));
        confirm_password.setFont(new Font(12));
        prefer_food.setFont(new Font(12));
        email_confirm_code.setFont(new Font(12));

        resend.setOnAction(ActionEvent->{
            //System.out.println(code.generatePIN());
            emailConfirmation = new EmailConfirmation(randomCode,email_field.getText());
            System.out.println(emailConfirmation.message);

        });
        signUp.setOnAction(ActionEvent -> {
            if(!email_confirmation_feild.getText().equals(randomCode))
            {
                error.setTextFill(Color.RED);
                error.setText("Not correct confirmation code");
            }
            else {
                CustomerFunctions customerFunctions = new CustomerFunctions();
                LocationFunctions locationFunctions = new LocationFunctions();
                GetUserId getUserId = new GetUserId();
                addUser.addUser(account_field.getText(), password_field.getText(), "c");
                if (Objects.equals(addUser.message, "You have successfully signed up")) {
                    int userId = getUserId.getUserId(account_field.getText(), password_field.getText());
                    Customer customer = new Customer(userId, firstName_field.getText(), lastName_field.getText(), phoneNum_field.getText(), email_field.getText(), pref_field.getText());
                    customerFunctions.setCustomerInfo(customer);

                    if (Objects.equals(customerFunctions.message, "The customer information has been saved")) {
                        int street_Num = Integer.parseInt(strNum_field.getText());
                        Location location = new Location(userId, street_Num, strname_field.getText(), city_field.getText(), "Saskatchewan", pcode_field.getText());
                        locationFunctions.addLocations(location);
                        customerReg.close();
                        Reg_windows.close();
                        pop();

                    }
                } else {
                    error.setTextFill(Color.RED);
                    error.setText(addUser.message);
                }
            }

        });

        // Add all
        reg_pane.add(error, 1, 0);
        reg_pane.add(first_name, 0, 1);
        reg_pane.add(firstName_field, 0, 2);
        reg_pane.add(last_name, 1, 1);
        reg_pane.add(lastName_field, 1, 2);
        reg_pane.add(account, 0, 3);
        reg_pane.add(account_field, 0, 4);
        reg_pane.add(prefer_food, 1, 3);
        reg_pane.add(pref_field, 1, 4);
        reg_pane.add(password,0,5);
        reg_pane.add(password_field,0,6);
        reg_pane.add(confirm_password,1,5);
        reg_pane.add(confirm_field,1,6);
        reg_pane.add(phone_number, 0, 7);
        reg_pane.add(phoneNum_field, 0, 8);
        reg_pane.add(email, 1, 7);
        reg_pane.add(email_field, 1, 8);
        reg_pane.add(str_number, 0, 9);
        reg_pane.add(strNum_field, 0, 10);
        reg_pane.add(str_name,1,9);
        reg_pane.add(strname_field,1,10);
        reg_pane.add(city,0,11);
        reg_pane.add(city_field,0,12);
        reg_pane.add(post_code,1,11);
        reg_pane.add(pcode_field,1,12);
        reg_pane.add(email_confirm_code,0,13);
        reg_pane.add(email_confirmation_feild,0,14);
        reg_pane.add(resend,1,15);
        reg_pane.add(signUp, 2, 17);

        box.setPrefSize(630,500);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(reg,reg_pane);
        Scene scene = new Scene(box);
        scene.getStylesheets().add("css/register_style.css");
        customerReg.setScene(scene);
        System.out.println(reg_pane.getWidth());
        System.out.println(reg_pane.getHeight());
        customerReg.show();
    }



}
