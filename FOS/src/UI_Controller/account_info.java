package UI_Controller;

import database.ChangeUserPassword;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Create By Hao Li at Oct. 18th
 */
public class account_info {

    //Creation
    Stage per_windows = new Stage();
    Stage res_windows = new Stage();
    private Label separate = new Label("|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|");
    Button info = new Button("LOG IN");
    Button address = new Button("SIGN UP");
    private Label name = new Label();
    private Label info_address = new Label();
    private Button p_info = new Button("Personal Information");
    private Button c_password = new Button("Change Password");
    private Button f_food = new Button("Favorite");
    Button q = new Button("Log Out");
    Button c_button = new Button("Submit");
    PasswordField old_field = new PasswordField();
    PasswordField nword_field = new PasswordField();
    PasswordField confirm_field = new PasswordField();
    String u_name;
    String u_address;
    String u_email;
    String phone_num;
    String u_food;
    Label message = new Label();
    int user_id;
    ChangeUserPassword user_password = new ChangeUserPassword();


    //check for info & address listener
    private boolean log_out = true;
    private boolean type = true;


    // make frame for account_info information
    void PA(){
        // create pane, cant be static, I already tried it
        BorderPane borderPane = new BorderPane();
        HBox hleft = new HBox();
        // listener for buttons

        p_info.setOnAction(e->borderPane.setCenter(information(u_name, u_address, u_email, phone_num)));
        c_password.setOnAction(e->borderPane.setCenter(change_password()));
        f_food.setOnAction(e->borderPane.setCenter(preferfood()));

        //set change password
        c_button.setOnAction(e->{
            if (nword_field.getText().equals(confirm_field.getText())&& nword_field.getText()!=null&&confirm_field.getText()!=null){
                user_password.verifyPassword(user_id,old_field.getText());
                if (Objects.equals(user_password.verifyMessage, "Y")){
                    user_password.changePassword(user_id,nword_field.getText());
                    old_field.clear();
                    nword_field.clear();
                    confirm_field.clear();
                    message.setFont(new Font(18));
                    message.setTextFill(Color.GREEN);
                    message.setText("Password Changed");

                }
                else{
                    message.setFont(new Font(18));
                    message.setTextFill(Color.GREEN);
                    message.setText("Old Password Incorrect");
                }
            }
            else {
                message.setFont(new Font(18));
                message.setTextFill(Color.RED);
                message.setText("Confirm & New password \nshould be match");
            }
        });


        // add make up
        borderPane.setStyle("-fx-background-image: url(/pictures/rightcenter.jpg)");
        q.getStylesheets().add("css/quit.css");


        // set size
        separate.setPrefSize(10,400);
        borderPane.setPrefSize(600,400);
        hleft.setPrefSize(180,400);
        per_windows.setMinWidth(600);
        per_windows.setMinHeight(400);


        //add all
        hleft.getChildren().addAll(vBox(),separate);
        borderPane.setLeft(hleft);
        Scene scene = new Scene(borderPane);
        per_windows.setScene(scene);

        // show frame
        per_windows.setTitle("Personal Signed_In");
        per_windows.show();
    }


    // left side of main frame
    private VBox vBox(){
        VBox vleft = new VBox();
        name.setText(info.getText());
        info_address.setText(address.getText());
        name.setAlignment(Pos.BOTTOM_CENTER);
        info_address.setAlignment(Pos.CENTER);

        // set size
        vleft.setPrefSize(180,390);
        name.setPrefSize(180,80);
        info_address.setPrefSize(180,40);
        p_info.setPrefSize(180,80);
        c_password.setPrefSize(180,80);
        f_food.setPrefSize(180,80);
        q.setPrefSize(180,40);
        vleft.setPadding(new Insets(0,10,10,10));

        //set makeup and add all
        vleft.setStyle("-fx-background-image: url(/pictures/leftbackground.jpeg)");
        vleft.getStylesheets().add("css/personal.css");
        vleft.getChildren().addAll(name, info_address,p_info,c_password,f_food,q);
        return vleft;
    }


    // account_info information frame
    VBox information(String name, String address, String email, String phone_num){
        // creation
        /* hard code for information
          need change listener by database **/
        VBox vBox = new VBox();
        Label n = new Label("Name: "+name);
        Label ad = new Label("Address: "+address);
        Label ph = new Label("Phone Number: "+phone_num);
        Label em = new Label("E-mail Address: "+email);

        //set up size
        n.setPrefSize(260,90);
        ad.setPrefSize(260,90);
        ph.setPrefSize(260,90);
        em.setPrefSize(260,90);
        vBox.setPrefSize(400,400);

        // set up padding
        vBox.getChildren().addAll(n,ad,ph,em);
        vBox.setPadding(new Insets(40,70,40,70));
        vBox.getStylesheets().add("css/personal.css");
        return vBox;
    }



    void RS(){
        BorderPane res_pane = new BorderPane();

        q.getStylesheets().add("css/quit.css");
        res_pane.setStyle("-fx-background-image: url(/pictures/rightcenter.jpg)");
        res_pane.setPrefSize(600,400);
        res_pane.setLeft(res_holder());
        Scene scene = new Scene(res_pane);
        res_windows.setScene(scene);
        res_windows.setMinWidth(600);
        res_windows.setMinHeight(400);
        res_windows.show();
    }
    VBox res_holder(){
        VBox holder = new VBox();
        holder.setPrefSize(180,390);
        holder.setStyle("-fx-background-image: url(/pictures/leftbackground.jpeg)");
        holder.getStylesheets().add("css/personal.css");
        holder.getChildren().add(vBox());
        return holder;
    }


    private GridPane restaurant_info(){
        GridPane r_info = new GridPane();
        Label name = new Label("Restaurant Name: ");
        Label phone = new Label("Phone Number: ");
        Label email = new Label("E-mail Address: ");
        Label o_time = new Label("Open Time: ");
        Label c_time = new Label("Close Time: ");
        Label address = new Label("Address: ");
        Label state = new Label("STATES: ");
        Label welcome = new Label("Welcome");

        Label r_name = new Label();
        Label r_address = new Label();
        Label r_email = new Label();
        Label r_phone = new Label();
        Label r_otime = new Label();
        Label r_ctim = new Label();
        Label r_state = new Label();



        r_info.setPrefSize(400,400);
        r_info.add(welcome,1,0);
        r_info.add(name,0,1);
        r_info.add(r_name,1,1);
        r_info.add(phone,0,2);
        r_info.add(r_phone,0,3);
        r_info.add(o_time,0,4);
        r_info.add(r_otime,0,5);
        r_info.add(address,0,6);
        r_info.add(r_address,0,7);
        r_info.add(email,1,2);
        r_info.add(r_email,1,3);
        r_info.add(c_time,1,4);
        r_info.add(r_ctim,1,5);
        r_info.add(state,1,6);
        r_info.add(r_state,1,7);



        return r_info;
    }







    //change password frame
    private GridPane change_password(){
        //creation
        GridPane change = new GridPane();
        //change.setStyle("-fx-background-image: url(/pictures/background.jpg)");
        Label old = new Label("Old Password: ");
        Label nword = new Label("New Password: ");
        Label confirm = new Label("Confirm Password: ");
        //Label blank = new Label("                                     ");


        //set up size
        change.setPrefSize(400,400);
        change.add(old,0,0);
        change.add(old_field,1,0);
        change.add(nword,0,1);
        change.add(nword_field,1,1);
        change.add(confirm,0,2);
        change.add(confirm_field,1,2);
        //change.add(blank,0,3);
        change.add(c_button,1,3);
        change.add(message,1,4);

        //set up padding
        change.setAlignment(Pos.CENTER_LEFT);
        old.setPadding(new Insets(15,25,15,0));
        nword.setPadding(new Insets(15,25,15,0));
        confirm.setPadding(new Insets(15,25,15,0));
        GridPane.setMargin(c_button,new Insets(0,0,0,120));
        change.getStylesheets().add("css/personal.css");
        return change;
    }


    private BorderPane preferfood(){
        BorderPane food_pane = new BorderPane();
        food_pane.setPrefSize(400,400);
        food_pane.getStylesheets().add("css/food.css");
        Label pre_food = new Label(u_food);
        pre_food.setPrefSize(400,400);
        pre_food.setAlignment(Pos.CENTER);
        pre_food.setFont(new Font(60));
        food_pane.setCenter(pre_food);
        return food_pane;
    }

    // check access
    boolean getCheck(){
        return log_out;
    }
    void setCheck(boolean n){
        log_out=n;
    }
    boolean gettype(){
        return type;
    }
    void setType(boolean n){
        type = n;
    }

}
