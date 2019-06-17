package UI_Controller;

/**
 * Create By Hao Li at Nov. 8th
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Place_Order{
    Button quit = new Button("QUIT");
    Button p_order = new Button("PLACE ORDER");
    ListView<Label> items;
    ListView<Label> num;
    ObservableList<Label> chose;
    ObservableList<Label> number;

    HBox bottom_buttons(){

        //Create bottom_buttons and hbox layout
        HBox button_holder = new HBox();


        // set sizes
        button_holder.setPrefSize(650,60);
        quit.setPrefSize(150,50);
        quit.setStyle("-fx-background-color: RED");
        p_order.setPrefSize(150,50);

        //set Margin
        HBox.setMargin(quit,new Insets(5,165,5,10));
        HBox.setMargin(p_order,new Insets(5,10,5,165));

        button_holder.getChildren().addAll(quit,p_order);

        return button_holder;
    }


    HBox center_view(){
        // create hbox layout and listviews
        HBox cen_holder = new HBox();
        items = new ListView<>();
        num = new ListView<>();
        chose = FXCollections.observableArrayList();
        number = FXCollections.observableArrayList();

        // set sizes
        cen_holder.setPrefSize(650,340);
        items.setPrefSize(500,320);
        num.setPrefSize(120,320);

        // set Margin
        HBox.setMargin(items, new Insets(10,0,10,15));
        HBox.setMargin(num,new Insets(10,15,10,0));

        items.setItems(chose);
        num.setItems(number);
        cen_holder.getChildren().addAll(items,num);
        return cen_holder;
    }

    
    // update listview for type of items 
    public void UpdateChose(ArrayList<Label> items){
        chose.clear();
        for (int i=items.size()-1; i>=0;i--){
            Label temp = items.get(i);
            chose.add(temp);
        }

    }
    
    // update listview for number of items
    public void Updatenumber(ArrayList<Integer> items){
        number.clear();
        for (int i=items.size()-1; i>=0;i--){
            Label temp = new Label("X "+items.get(i));
            number.add(temp);
        }

    }
}
