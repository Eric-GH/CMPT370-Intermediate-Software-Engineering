package UI_Controller;
/**
 * Create By Hao Li at Nov. 7th
 */

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Orders {
    public Place_Order place_order = new Place_Order();
    //Item_collection item_collection = new Item_collection();
    Payment_type payment_type = new Payment_type();
    Stage go_orders = new Stage();
    public Button checkout = new Button("CHECK OUT");
    CreditCardPayment ccp = new CreditCardPayment();
    //example stacks

    public Button checkout(){
        checkout.setPrefSize(180,60);
        checkout.getStylesheets().add("css/checkout.css");
        //for check out button
        //set up Modality
        go_orders.initModality(Modality.APPLICATION_MODAL);
        return checkout;
    }
    public void orders(){
        BorderPane orderPane = new BorderPane();
        orderPane.getStylesheets().add("css/order.css");
        orderPane.setStyle("-fx-background-color: cornsilk");
        orderPane.setPrefSize(650,400);
        go_orders.setTitle("PLACE YOUR ORDER");


        orderPane.setCenter(place_order.center_view());
        orderPane.setBottom(place_order.bottom_buttons());
        /*set listeners*/
        //for place orders listeners
        place_order.quit.setOnAction(e->{
            //go_orders.initModality(Modality.NONE);
            go_orders.close();
        });
        place_order.p_order.setOnAction(e->{
            orderPane.setCenter(payment_type.payment());
            orderPane.setBottom(null);

        });
        //for payment_type listeners
        payment_type.p_now.setOnAction(e->{
            if (payment_type.cash.isSelected()||payment_type.debit.isSelected()||payment_type.credit.isSelected()){
                payment_type.credit.setSelected(false);
                payment_type.cash.setSelected(false);
                payment_type.debit.setSelected(false);
                payment_type.p_now.setDisable(false);
                payment_type.cash.setDisable(false);
                payment_type.credit.setDisable(false);
                payment_type.debit.setDisable(false);
                // if pay by credit card through project
                orderPane.setCenter(ccp.pane());
                //go_orders.close();
            }
            else{
                payment_type.error.setText("Please choose the payment\n type first");
                payment_type.error.setTextFill(Color.RED);
            }

        });
        payment_type.p_later.setOnAction(e->{
            if (payment_type.cash.isSelected()||payment_type.debit.isSelected()||payment_type.credit.isSelected()){
                payment_type.cash.setSelected(false);
                payment_type.credit.setSelected(false);
                payment_type.debit.setSelected(false);
                payment_type.p_now.setDisable(false);
                payment_type.cash.setDisable(false);
                payment_type.credit.setDisable(false);
                payment_type.debit.setDisable(false);
                go_orders.close();
            }
            else{
                payment_type.error.setText("Please choose the payment\n type first");
                payment_type.error.setTextFill(Color.RED);
            }
        });
        //if pay by cash
        payment_type.cash.setOnAction(e-> {
            payment_type.error.setText(null);
            if (payment_type.cash.isSelected()){
                payment_type.p_now.setDisable(true);
                payment_type.debit.setSelected(false);
                payment_type.credit.setSelected(false);
                payment_type.debit.setDisable(true);
                payment_type.credit.setDisable(true);
            }
            else {
                payment_type.p_now.setDisable(false);
                payment_type.debit.setDisable(false);
                payment_type.credit.setDisable(false);
            }
        });
        //if pay by credit card
        payment_type.credit.setOnAction(e -> {
            payment_type.error.setText(null);
            if (payment_type.credit.isSelected()){
                //payment_type.p_now.setDisable(true);
                payment_type.debit.setSelected(false);
                payment_type.cash.setSelected(false);
                payment_type.debit.setDisable(true);
                payment_type.cash.setDisable(true);
            }
            else {
                //payment_type.p_now.setDisable(false);
                payment_type.debit.setDisable(false);
                payment_type.cash.setDisable(false);
            }
        });
        // if pay by debit card
        payment_type.debit.setOnAction(e -> {
            payment_type.error.setText(null);
            if (payment_type.debit.isSelected()){
                payment_type.p_now.setDisable(true);
                payment_type.cash.setSelected(false);
                payment_type.credit.setSelected(false);
                payment_type.cash.setDisable(true);
                payment_type.credit.setDisable(true);
            }
            else {
                payment_type.p_now.setDisable(false);
                payment_type.cash.setDisable(false);
                payment_type.credit.setDisable(false);
            }
        });
        Scene scene = new Scene(orderPane);
        go_orders.setScene(scene);



        go_orders.show();
    }


}
