package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ShoppingSystem;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductPageController implements Initializable {
    public ImageView productView;
    public JFXButton cartButton;
    public Button backButton;
    public ImageView productView_1;
    public ImageView productView_2;
    public ImageView productView_3;

    public Label productId;
    public Label productName;
    public Label productBrand;
    public Label productCategory;
    public Label productPrice;
    public Button addToCartButton;
    public TextField amountOfProduct;
    public TextArea descriptionTextArea;
    public Label addedLabel;
    private ShoppingSystem shoppingSystem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem = SystemInitializer.getShoppingSystem();
        addedLabel.setVisible(false);
    }

    public void cartButtonClicked() {
        SystemInitializer.showPage("cart page");
    }

    private void setImagesOfProducts(String productId){
        //TODO
        //when we access to products image directory , we can add images of product to imageView!
        Image image =new Image(new File("src/main/resources/products/"+(productId)+".png").toURI().toString());
        productView.setImage(image);
        productView_2.setImage(image);
        productView_1.setImage(new Image(new File("src/main/resources/products/"+(productId)+"_2.png").toURI().toString()));
        productView_3.setImage(new Image(new File("src/main/resources/products/"+(productId)+"_3.png").toURI().toString()));

    }
    void setInfoOfProduct(String productId,String productName,String productBrand,String productCategory,String productPrice){
        this.productId.setText(productId);
        this.productName.setText(productName);
        this.productBrand.setText(productBrand);
        this.productCategory.setText(productCategory);
        this.productPrice.setText(productPrice);
        setImagesOfProducts(productId);
    }

    public void BackButtonClicked() {
        SystemInitializer.showPage("products page");

    }

    public void firstViewSelected() {
        Image image = productView_1.getImage();
        if (image.getWidth()>0) {
            productView.setImage(image);
        }
    }
    public void secondViewSelected() {
        Image image = productView_2.getImage();
        if (image.getWidth()>0) {
            productView.setImage(image);
        }
    }
    public void thirdViewSelected() {
        Image image = productView_3.getImage();
        if (image.getWidth()>0) {
            productView.setImage(image);
        }
    }


    public void addToCartButtonSelected() {
        int amount = Integer.parseInt(this.amountOfProduct.getText());
        String productId = this.productId.getText();
        if(shoppingSystem.addProductToCart(productId, amount)){
            addedLabel.setVisible(true);
        }
    }
}
