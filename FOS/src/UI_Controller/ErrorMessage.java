package UI_Controller;

/**
 * Yuecheng  Rong
 */

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ErrorMessage{

    /** The error message to show up*/
    private String errorMessage = "Not Found!";
    /** Main Stage */
    private Stage stage = new Stage();
    /** Main BorderPane */
    private BorderPane MainPane = new BorderPane();
    /** Main Button to close the window */
    private Button btn = new Button("OK");
    /** Main Label that will show error message */
    private Label lb = new Label(errorMessage);


    public void setErrorMessage(String input){
        this.errorMessage = input;
    }


    public void error(){
        btn.setOnAction((ActionEvent event) -> {
            stage.close();
            //Event.fireEvent(stage, new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST ));
        });
        MainPane.setPrefSize(300,250);
        MainPane.setCenter(lb);
        MainPane.setBottom(btn);
        Scene scene = new Scene(MainPane);
        stage.setScene(scene);
        stage.show();
    }

}
