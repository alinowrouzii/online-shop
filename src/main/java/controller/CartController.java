package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Product;
import model.ShoppingSystem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
//                    int amount = Integer.parseInt(controller.productAmount.getText());
                    //use -1 number to remove all amounts of product from cart
                    shoppingSystem.removeProductFromCart(product.getProductId(),-1);
//                    System.out.println("amount"+amount);
                });

                controller.setInfoOfProduct(product,amount);
                cartVBox.getChildren().add(box);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void backButtonClicked() {
        SystemInitializer.showPage("products page");
    }
}
