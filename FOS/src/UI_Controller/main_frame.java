package UI_Controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * Create By Hao Li at Oct. 15th
 * Refactored by Yuecheng Rong
 */

public class main_frame extends Application {

    /** Main stage */
    private Stage windows = new Stage();
    /** Main BorderPane */
    private BorderPane MainPane = new BorderPane();
    /** The Box on the left of the main stage, contains control system */
    private VBox left = new VBox();
    /** StackPane for our logo */
    private StackPane blank = new StackPane();
    /** The logo of FOS system */
    private ImageView logo = new ImageView(new Image("/pictures/log.png"));
    /** The center area */
    private main_controller center = new main_controller();
    /** The Scene */
    private Scene scene = new Scene(MainPane);






    /**
     * Main starting function
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {

        windows = primaryStage;
        // Set up
        this.setLogo();
        this.setLeft();
        this.setBlank();
        this.setMainPane();
        this.setWindows();
    }


    /** Set up the FOS Logo */

    private void setLogo() {
        logo.setFitHeight(80);
        logo.setFitWidth(160);
    }

    /** Set up the blank for the FOS logo */
    private void setBlank() {
        blank.setPrefSize(180, 80);
        blank.getChildren().add(logo);
    }

    /** Set up the Left area */
    private void setLeft() {
        left.setPrefHeight(400);
        left.setPrefWidth(180);
        left.setStyle("-fx-background-image: url(/pictures/background.jpg)");
        left.setPadding(new Insets(10, 5, 10, 5));
        // add children
        left.getChildren().addAll(blank,center.user_account,
                center.search(),center.setsort(),center.count,center.orders.checkout(),center.main_Button());
    }

    /** Set up the Main BorderPane */
    private void setMainPane() {
        MainPane.setPrefHeight(500);
        MainPane.setPrefWidth(800);
        MainPane.setLeft(left);
        MainPane.setCenter(center);
    }

    /**
     * Set up the stage
     */
    private void setWindows() {
        windows.setTitle("Food Ordering System");
        windows.setResizable(false);
        windows.setScene(scene);
        windows.show();
    }


    /**
     * Launch the stage
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
