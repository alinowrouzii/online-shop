package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Product;
import model.ShoppingSystem;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    public VBox cartVBox;
    public Button backButton;
    private ShoppingSystem shoppingSystem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem = SystemInitializer.getShoppingSystem();
        //TODO: uncomment when project complete!
//        ArrayList<Product> products = shoppingSystem.getCartProduct();

        for(Product product:shoppingSystem.getCartProduct().keySet()) {
            try {
                FXMLLoader loader = new FXMLLoader(new File("src/main/java/view/ProductInfoHBox.fxml").toURI().toURL());
                HBox box = loader.load();
                int amount = shoppingSystem.getCartProduct().get(product) ;
                ProductInfoHBox controller = loader.getController() ;
                controller.deleteIcon.setOnMouseClicked(e -> {
                    cartVBox.getChildren().remove(box);
                    shoppingSystem.removeProductFromCart(product.getProductId(),amount);
//                    System.out.println("amount"+amount);
                });

                controller.setInfoOfProduct(product,amount);
                cartVBox.getChildren().add(box);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void backButtonClicked(MouseEvent mouseEvent) {
        Stage stage;
        Parent root = null;
        stage = (Stage) backButton.getScene().getWindow();
        try {
            root = FXMLLoader.load(new File("src/main/java/view/ProductsPage.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(root != null){
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sign up");
            stage.show();
        }
    }
}
