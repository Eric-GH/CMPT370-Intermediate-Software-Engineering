package UI_Controller;

import database.DishFunctions;
import database.Menus;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class menus {
    Stage m_windows = new Stage();
    int page_number = 0;
    Menus m = new Menus();
    DishFunctions m_menus = new DishFunctions();
    Button pre = new Button("Previous");
    Button next = new Button("Next");
    VBox menus_holder;
    BorderPane main_holder;

    menus_item[] items;




    void menus(int res_id){
        Label welcome = new Label("Welcome");
        welcome.setAlignment(Pos.CENTER);
        welcome.setPrefSize(500,70);
        welcome.setStyle("-fx-background-color: khaki");
        welcome.setFont(new Font(30));
        welcome.setTextFill(Color.WHITE);
        main_holder = new BorderPane();
        menus_holder = new VBox();
        main_holder.setTop(welcome);
        main_holder.setBottom(buttonBox(res_id));
        main_holder.setCenter(setMenus(res_id));
        main_holder.setPrefSize(500,600);
        Scene scene = new Scene(main_holder);
        m_windows.setScene(scene);
        m_windows.show();
    }

    HBox buttonBox(int id){
        HBox button_holder = new HBox();
        pre.setOnAction((ActionEvent e) ->{
            if (page_number>=7){
                page_number = page_number-7;
                setMenuPage();
                main_holder.setCenter(menus_holder);
            }else {
                System.out.println("pre");
            }
        });

        next.setOnAction(e->{
            if ((page_number+7)<m_menus.getDishes(m.getMenuId(m.displayAllMenuInOneRestaurant(id).get(0))).size()){
                page_number = page_number +7;
                setMenuPage();
                main_holder.setCenter(menus_holder);
            }
            else {
                System.out.println("Next");
            }
        });

        pre.setAlignment(Pos.CENTER);
        next.setAlignment(Pos.CENTER);
        pre.setPrefSize(100,60);
        next.setPrefSize(100,60);
        HBox.setMargin(next, new Insets(0,0,0,300));


        button_holder.getStylesheets().add("/css/page.css");
        button_holder.setStyle("-fx-background-color: khaki");
        button_holder.getChildren().addAll(pre,next);
        return button_holder;
    }

    VBox setMenus(int id){

        //m_menus.displayAllMenuInOneRestaurant(id);
        items = new menus_item[m_menus.getDishes(m.getMenuId(m.displayAllMenuInOneRestaurant(id).get(0))).size()];
        for (int i = page_number; i<m_menus.getDishes(m.getMenuId(m.displayAllMenuInOneRestaurant(id).get(0))).size(); i++){
            Label temp_label = new Label();
            Button temp_button = new Button();
            //temp_hbox.setPrefSize(500,80);
            temp_label.setPrefSize(390,80);
            temp_button.setPrefSize(100,40);
            VBox.setMargin(temp_button, new Insets(20,0,0,0));
            temp_button.setText("Order");
            temp_button.setAlignment(Pos.CENTER);
        
            temp_label.setText(m_menus.getDishes(m.getMenuId(m.displayAllMenuInOneRestaurant(id).get(0))).get(i).getDish_name());
            temp_label.setAlignment(Pos.CENTER);
            menus_item item = new menus_item(temp_label,temp_button);
            items[i] = item;
        }

        for (int i = page_number;i<items.length&& menus_holder.getChildren().size()<7;i++){
            menus_holder.getChildren().add(items[i]);
        }
        return menus_holder;
    }

    void setMenuPage(){
        menus_holder.getChildren().clear();
        for (int i = page_number; i<items.length&& menus_holder.getChildren().size()<7; i++){
            menus_holder.getChildren().add(items[i]);
        }
    }

}
