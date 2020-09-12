package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Product;
import model.ShoppingSystem;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductInfoHBox implements Initializable {
    public Label productId;
    public Label productName;
    public Label productPrice;
    public Label productTotalPrice;
    public Label productAmount;
    public Button deleteIcon;
    private ShoppingSystem shoppingSystem;

    public void setInfoOfProduct(Product product,int amount) {
        productId.setText(product.getProductId());
        productName.setText(product.getProductName());
        productPrice.setText(product.getPrice().toString());
        productTotalPrice.setText(product.getPrice().multiply(new BigInteger(amount+"")).toString());
        productAmount.setText(amount+"");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem = SystemInitializer.getShoppingSystem();
    }

    public void deleteFromCart(MouseEvent mouseEvent) {

    }

    public void amountIncreasedOne(ActionEvent actionEvent) {
        String productId = this.productId.getText();
        int amount =1;
        if(shoppingSystem.addProductToCart(productId,amount)){
            int newAmount = Integer.parseInt(productAmount.getText())+1 ;
            productAmount.setText(newAmount+"");
        }

    }

    public void amountDecreasedOne(ActionEvent actionEvent) {
        String productId = this.productId.getText();
        int amount =1;
        int oldAmount =Integer.parseInt(productAmount.getText());
        if(oldAmount>1) {
            shoppingSystem.removeProductFromCart(productId,amount);
            int newAmount = oldAmount-1 ;
            productAmount.setText(newAmount+"");
        }


    }
}
