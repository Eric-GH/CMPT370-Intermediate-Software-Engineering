package UI_Controller;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
/**
 * Create By Hao Li at Oct. 15th
 */

public class count extends GridPane {
    private Label items = new Label("Item(s):");
    private Label prices = new Label("Total Prices: ");
    Label num_items = new Label("0");
    Label num_prices = new Label("$"+"0");
    count(){
        add(items,0,0);
        add(prices,0,1);
        add(num_items,1,0);
        add(num_prices,1,1);
        getColumnConstraints().add(new ColumnConstraints(100));
        getRowConstraints().add(new RowConstraints(40));
        getColumnConstraints().add(new ColumnConstraints(60));
        getRowConstraints().add(new RowConstraints(40));
    }
}
