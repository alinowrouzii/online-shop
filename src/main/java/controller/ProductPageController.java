package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ProductPageController {
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

    public void cartButtonClicked(MouseEvent mouseEvent) {
    }


    private void setImagesOfProducts(String productId){
        //TODO
        //when we access to products image directory , we can add images of product to imageView!
        productView.setImage(new Image(new File("src/main/resources/products/"+(productId)+".png").toURI().toString()));
    }
    public void setInfoOfProduct(String productId,String productName,String productBrand,String productCategory,String productPrice){
        this.productId.setText(productId);
        this.productName.setText(productName);
        this.productBrand.setText(productBrand);
        this.productCategory.setText(productCategory);
        this.productPrice.setText(productPrice);
        setImagesOfProducts(productId);
    }

    public void BackButtonClicked(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(new File("src/main/java/view/ProductsPage.fxml").toURI().toURL());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login page");
        stage.show();
    }
}
