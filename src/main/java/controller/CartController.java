package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Product;
import model.ShoppingSystem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    public VBox cartHBox;
    private ShoppingSystem shoppingSystem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem = SystemInitializer.getShoppingSystem();
        //TODO: uncomment when project complete!
//        ArrayList<Product> products = shoppingSystem.getCartProduct();

        try {
            //Add ProductInfoHBox as a template
            //We can add ProductInfoHBox as a many time and Edit that with their controllers
            HBox box_1 = FXMLLoader.load(new File("src/main/java/view/ProductInfoHBox.fxml").toURI().toURL());
            cartHBox.getChildren().add(box_1);

            HBox box_2 = FXMLLoader.load(new File("src/main/java/view/ProductInfoHBox.fxml").toURI().toURL());
            cartHBox.getChildren().add(box_2);

            HBox box_3 = FXMLLoader.load(new File("src/main/java/view/ProductInfoHBox.fxml").toURI().toURL());
            cartHBox.getChildren().add(box_3);

            HBox box_4 = FXMLLoader.load(new File("src/main/java/view/ProductInfoHBox.fxml").toURI().toURL());
            cartHBox.getChildren().add(box_4);

            HBox box_5 = FXMLLoader.load(new File("src/main/java/view/ProductInfoHBox.fxml").toURI().toURL());
            cartHBox.getChildren().add(box_5);

            HBox box_6 = FXMLLoader.load(new File("src/main/java/view/ProductInfoHBox.fxml").toURI().toURL());
            cartHBox.getChildren().add(box_6);

            HBox box_7 = FXMLLoader.load(new File("src/main/java/view/ProductInfoHBox.fxml").toURI().toURL());
            cartHBox.getChildren().add(box_7);

            HBox box_8 = FXMLLoader.load(new File("src/main/java/view/ProductInfoHBox.fxml").toURI().toURL());
            cartHBox.getChildren().add(box_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
