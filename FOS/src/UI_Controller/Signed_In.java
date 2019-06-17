package UI_Controller;

import Entities.Customer;
import database.DisplayUserInformation;
import database.GetUserId;
import database.Login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Create By Hao Li at Oct. 12th
 */

class Signed_In {
    // creation

    private Login login = new Login();
    private Stage windows = new Stage();
    CheckBox s_d = new CheckBox();
    private Registration registration = new Registration();
    account_info account_info = new account_info();
    GetUserId u_id = new GetUserId();
    DisplayUserInformation d_u = new DisplayUserInformation();
    boolean select_flag=false;
    private Customer customer;
    // access
    Signed_In(){
        // set up modality
        windows.initModality(Modality.APPLICATION_MODAL);
        //change size for info & address
        account_info.info.setPrefSize(100,20);
        account_info.address.setPrefSize(200,20);
        // call listener
        setinfo();

        account_info.q.setOnAction(e->{
            account_info.per_windows.close();
            account_info.info.setText("LOG IN");
            account_info.address.setText("SIGN UP");
            account_info.setCheck(true);
            s_d.setDisable(true);
            select_flag =false;
        });

        s_d.setDisable(true);
    }

    // info & address listeners. To change the text and function
    private void setinfo(){

        // info listener
        account_info.info.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!account_info.getCheck()){
                    if (account_info.gettype()){
                        account_info.PA();
                    }
                    else {
                        System.out.println("there");
                        account_info.RS();
                    }
                }
                else {
                    info();
                }
            }
        });

        // address listener
        account_info.address.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!account_info.getCheck()){
                    System.out.println("Address");
                }
                else {
                    registration.Reg();
                }
            }
        });
    }

    // make frame for signed in
    private void info(){
        windows.setTitle("Signed In");
        windows.setMinHeight(200);
        windows.setMinWidth(300);
        Scene scene = new Scene(access_to_signed_in());
        scene.getStylesheets().add("css/account_style.css");
        windows.setScene(scene);
        windows.show();
    }

    // method for signed in
    private GridPane access_to_signed_in(){

        // create signed in pane, button, label
        GridPane sign = new GridPane();
        Button submit = new Button("Submit");
        Label user_name = new Label("Username: ");
        Label pass_word = new Label("Password: ");
        Label error = new Label("");
        Label signed_in = new Label("Welcome to FOS");
        TextField username = new TextField();
        PasswordField password = new PasswordField();

        // listener for submit button
        submit.setOnAction((ActionEvent ActionEvent) ->{
            login.login(username.getText(),password.getText());
            if(login.message.equals("true"))
            {
                u_id.getUserId(username.getText(),password.getText());
                s_d.setDisable(false);
                select_flag = true;
                if (login.status.equals("customers")){
                    //pop();
                    account_info.user_id = u_id.getUserId(user_name.getText(),password.getText());
                    d_u.getCustomerInfo(u_id.getUserId(user_name.getText(),password.getText()));
                    account_info.u_name = d_u.customer_first_name+" "+d_u.customer_last_name;
                    account_info.u_address=d_u.customer_location;
                    account_info.u_email = d_u.customer_email;
                    account_info.phone_num = d_u.customer_phone_num;
                    account_info.u_food = d_u.customer_pref_food;
                    account_info.info.setText("Hi, "+login.name);
                    account_info.address.setText(d_u.customer_location);
                    account_info.setCheck(false);
                    account_info.setType(true);
                    setinfo();
                    customer = new Customer(account_info.user_id,d_u.customer_first_name,d_u.customer_last_name,
                            account_info.phone_num, account_info.u_email, account_info.u_food);
                }
                else if (login.status.equals("restaurant")){
                    account_info.user_id = u_id.getUserId(user_name.getText(),password.getText());
                    d_u.getCustomerInfo(u_id.getUserId(user_name.getText(),password.getText()));
                    account_info.u_name = d_u.customer_first_name+" "+d_u.customer_last_name;
                    account_info.u_address=d_u.customer_location;
                    account_info.u_email = d_u.customer_email;
                    account_info.phone_num = d_u.customer_phone_num;
                    account_info.info.setText("Hi, "+login.name);
                    account_info.address.setText(d_u.customer_location);
                    account_info.setCheck(false);
                    account_info.setType(false);
                    setinfo();

                }

                windows.close();


            }
            else {
                error.setText("Wrong username or password");
                error.setWrapText(true);
                error.setTextFill(Color.RED);
            }
        });


        // set padding
        user_name.setPadding(new Insets(15,5,15,5));
        pass_word.setPadding(new Insets(15,5,15,10));
        sign.setPadding(new Insets(30,100,15,100));
        signed_in.setFont(new Font(25));



        //Add all
        sign.add(signed_in,1,0);
        sign.add(user_name,0,1);
        sign.add(pass_word,0,2);
        sign.add(error,1,3);
        sign.add(submit,3,4);
        sign.add(username,1,1);
        sign.add(password,1,2);


        return sign;
    }

    public Customer getCustomer() {
        return customer;
    }
}
