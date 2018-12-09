package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/fxml/Item.fxml"));
        */
        URL url = new File("src/main/resources/fxml/Item.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Inventory");
        primaryStage.setScene(new Scene(root, 900, 450));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
