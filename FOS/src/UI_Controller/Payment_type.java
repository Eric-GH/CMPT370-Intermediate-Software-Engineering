package UI_Controller;
/**
 * Create By Hao Li at Nov. 8th
 */
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Payment_type {
    Button p_now = new Button("PAY NOW");
    Button p_later = new Button("PAY LATER");
    CheckBox cash = new CheckBox("CASH");
    CheckBox credit = new CheckBox("CREDIT CARD");
    CheckBox debit = new CheckBox("DEBIT CARD");
    Label error = new Label();
    
    // set Hbox for hold all payment type layouts
    HBox payment(){
        HBox payment_holder = new HBox();
        HBox.setMargin(pay_type(),new Insets(10,15,10,0));
        HBox.setMargin(pay_way(), new Insets(10,0,10,15));
        payment_holder.setPrefSize(630,380);
        payment_holder.getChildren().addAll(pay_type(),pay_way());
        return payment_holder;
    }

    VBox pay_type(){
        VBox type_holder = new VBox();
        // set sizes
        error.setPrefSize(200,45);
        cash.setPrefSize(200,85);
        credit.setPrefSize(200,85);
        debit.setPrefSize(200,85);
        type_holder.setPrefSize(300,360);

        //set Margin
        VBox.setMargin(error,new Insets(20,0,0,20));
        VBox.setMargin(credit, new Insets(10,0,0,70));
        VBox.setMargin(debit, new Insets(0,0,0,70));
        VBox.setMargin(cash, new Insets(0,0,0,70));

        type_holder.getChildren().addAll(error,credit,debit,cash);
        return type_holder;
    }

    VBox pay_way(){
        VBox way_holder = new VBox();
        // set sizes
        way_holder.setPrefSize(300,360);
        p_now.setPrefSize(260,120);
        p_later.setPrefSize(260,120);

        // set Margin
        VBox.setMargin(p_now,new Insets(50,0,30,20));
        VBox.setMargin(p_later, new Insets(30,0,0,20));

        way_holder.getChildren().addAll(p_now,p_later);
        return way_holder;
    }

}
