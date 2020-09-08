package sample;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.ProductBox;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(new File("src/main/java/view/MainMenuPage.fxml").toURI().toURL());
//        Parent root = FXMLLoader.load(new File("src/main/java/view/signUpPage.fxml").toURI().toURL());

        Scene scene = new Scene(root, 1000, 600);

        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("Happy shop");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}