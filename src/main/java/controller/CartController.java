package controller;

import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import model.Product;
import model.ShoppingSystem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    private ShoppingSystem shoppingSystem;
    public VBox cartBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem = SystemInitializer.getShoppingSystem();
        //TODO: uncomment when project complete!
//        ArrayList<Product> products = shoppingSystem.getCartProduct();

    }
}
